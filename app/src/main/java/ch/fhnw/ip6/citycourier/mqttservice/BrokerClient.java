package ch.fhnw.ip6.citycourier.mqttservice;

import android.content.Context;
import android.util.Log;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.nio.charset.StandardCharsets;

import ch.fhnw.ip6.citycourier.data.CourierRepository;
import ch.fhnw.ip6.citycourier.data.OrdersRepository;
import ch.fhnw.ip6.citycourier.data.TaskRequestsRepository;
import ch.fhnw.ip6.citycourier.model.TaskRequest;
import ch.fhnw.ip6.citycourier.mqttservice.util.ModelObjectsConverter;
import kotlinx.coroutines.GlobalScope;

public class BrokerClient implements RequestReplyEventListener, OrderGetEventListener, TaskCompletedEventListener{
    private static final String HIVEMQ_ANDROID_CLIENT_USER_NAME = "mqtt-android";
   private static final String HIVEMQ_ANDROID_CLIENT_PASSWORD = "groupwareandroid";

  // Other options
    public static final  int BROKER_CONNECTION_TIMEOUT = 7;
    public static final  int BROKER_CONNECTION_KEEP_ALIVE_INTERVAL = 1800;
    public static final  boolean BROKER_CONNECTION_CLEAN_SESSION = false;
    public static final boolean BROKER_CONNECTION_RECONNECT = true;
    private static final String TAG = "AndroidBrokerClient";

    static final String CURRENT_COURIER_ID = "C106";
    private static final String  requestTopic = "orders/"+CURRENT_COURIER_ID+"/+/request";
    private static final String  timeoutTopic = "orders/"+CURRENT_COURIER_ID+"/+/timeout";
    private static final String  publishRequestReplyTopicOK = "orders/"+CURRENT_COURIER_ID+"/+/accept";
    private static final String  publishRequestReplyTopicNO = "orders/"+CURRENT_COURIER_ID+"/+/deny";
    private static final String  publishRequestCompletedTopic = "orders/"+CURRENT_COURIER_ID+"/+/completed";

    public static final String IDENTIFIER_REQUEST_CLIENT = "Android_Request_Subscriber";
    public static final String IDENTIFIER_COURIER_CLIENT = "Android_Courier_InfoSubscriber";
    public static final String IDENTIFIER_ORDER_SUBSCRIBER = "Android_OrderSubscriber";
    static final String HIVEMQ_MQTT_HOST = "tcp://192.168.0.108:1883";
    MqttAndroidClient clientRequestSubscriber ;
    MqttAndroidClient clientCourierSubscriber;
    MqttAndroidClient clientOrderSubscriber;

    public BrokerClient() {
    }

    public void createClients(Context context, TaskRequestsRepository taskRequestsRepository,
                              CourierRepository courierRepository, OrdersRepository orderRepository){
            clientRequestSubscriber = new MqttAndroidClient(context, HIVEMQ_MQTT_HOST, IDENTIFIER_REQUEST_CLIENT);
            //Set callback handler
            clientRequestSubscriber.setCallback(new RequestMqttCallbackHandler(taskRequestsRepository));
            clientCourierSubscriber = new MqttAndroidClient( context,HIVEMQ_MQTT_HOST, IDENTIFIER_COURIER_CLIENT);
            clientCourierSubscriber.setCallback(new CourierInfoMqttCallbackHandler(courierRepository, CURRENT_COURIER_ID));
            clientOrderSubscriber = new MqttAndroidClient( context,HIVEMQ_MQTT_HOST, IDENTIFIER_ORDER_SUBSCRIBER);
            clientOrderSubscriber.setCallback(new OrderMqttCallbackHandler(orderRepository));
    }

    private MqttConnectOptions setUpConnectionOptions(int keepAliveInt, boolean cleanSession){
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(HIVEMQ_ANDROID_CLIENT_USER_NAME);
        options.setPassword(HIVEMQ_ANDROID_CLIENT_PASSWORD.toCharArray());
        //The MqttAndroidClient will connect with MQTT 3.1.1 by default
        options.setAutomaticReconnect(BROKER_CONNECTION_RECONNECT);
        options.setCleanSession(cleanSession);
        options.setConnectionTimeout(BROKER_CONNECTION_TIMEOUT);
        options.setKeepAliveInterval(keepAliveInt);
        return options;
    }

    private void connectToBrokerClientRequestSubscriber(){
         try {
            MqttConnectOptions options = setUpConnectionOptions(BROKER_CONNECTION_KEEP_ALIVE_INTERVAL, BROKER_CONNECTION_CLEAN_SESSION);
            IMqttToken token = clientRequestSubscriber.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    DisconnectedBufferOptions disconnectedBufferOpts = new DisconnectedBufferOptions();
                    disconnectedBufferOpts.setBufferEnabled(true);
                    disconnectedBufferOpts.setBufferSize(100);
                    disconnectedBufferOpts.setPersistBuffer(false);
                    disconnectedBufferOpts.setDeleteOldestMessages(false);
                    clientRequestSubscriber.setBufferOpts(disconnectedBufferOpts);
                    Log.d(TAG, clientRequestSubscriber.getClientId()+" onSuccess-Connected");

                    subscribeToTopic(clientRequestSubscriber, requestTopic, 2);
                    subscribeToTopic(clientRequestSubscriber, timeoutTopic,2);
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Log.d(TAG, clientRequestSubscriber.getClientId() +" onFailure -Something went wrong"+exception.getLocalizedMessage());
                }
            });
        } catch ( MqttException e) {
            e.printStackTrace();
        }
    }
    private void connectToBrokerClientCourierSubscriber(){
        try {
            MqttConnectOptions options = setUpConnectionOptions(100, BROKER_CONNECTION_CLEAN_SESSION);
            IMqttToken token = clientCourierSubscriber.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.d(TAG, clientCourierSubscriber.getClientId()+" onSuccess-Connected");
                    DisconnectedBufferOptions disconnectedBufferOpts = new DisconnectedBufferOptions();
                    disconnectedBufferOpts.setBufferEnabled(true);
                    disconnectedBufferOpts.setBufferSize(100);
                    disconnectedBufferOpts.setPersistBuffer(false);
                    disconnectedBufferOpts.setDeleteOldestMessages(false);
                    clientCourierSubscriber.setBufferOpts(disconnectedBufferOpts);

                    String courierInfoTopic = "couriers/info/get/"+CURRENT_COURIER_ID;
                    publishToTopic(clientCourierSubscriber,courierInfoTopic,null, true,2);
                    String courierInfoResponseTopic = "couriers/info/get/"+CURRENT_COURIER_ID+"/response";
                    subscribeToTopic(clientCourierSubscriber, courierInfoResponseTopic, 2);
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Log.d(TAG, clientCourierSubscriber.getClientId() +" onFailure -Something went wrong"+exception.getLocalizedMessage());

                }
            });
        } catch ( MqttException e) {
            e.printStackTrace();
        }
    }

    private void disconnectFromBroker(MqttAndroidClient client){
        try {
            client.unregisterResources();
            IMqttToken disconToken = client.disconnect();

            disconToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // we are now successfully disconnected
                    Log.d(TAG, asyncActionToken.getClient().getClientId() +" successfully disconnected");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken,
                                      Throwable exception) {
                    // something went wrong, but probably we are disconnected anyway
                    Log.d(TAG, asyncActionToken.getClient().getClientId() +" onFailure -Something went wrong"+exception.getLocalizedMessage());
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }



    public void disconnectMQttClients() {

        if(this.clientRequestSubscriber.getClientId() != null && (this.clientRequestSubscriber.isConnected())){
            disconnectFromBroker(this.clientRequestSubscriber);
        }
        if(this.clientCourierSubscriber.getClientId() != null && (this.clientRequestSubscriber.isConnected())){
            disconnectFromBroker(this.clientCourierSubscriber);
        }
        if(this.clientOrderSubscriber.getClientId() != null && (this.clientOrderSubscriber.isConnected())){
            disconnectFromBroker(this.clientOrderSubscriber);
        }
    }



    private void publishToTopic(MqttAndroidClient client, String topic, String payload, boolean retained, int qos){
        byte[] encodedPayload = new byte[0];
        try {
            if(payload!=null){
                encodedPayload = payload.getBytes(StandardCharsets.UTF_8);
            }
            MqttMessage message = new MqttMessage(encodedPayload);
            message.setQos(qos);
            message.setRetained(retained);
            client.publish(topic, message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void subscribeToTopic( MqttAndroidClient client, String topic, int qos){
        try {
            if(!topic.isEmpty()){
                if (!client.isConnected()) {
                    if (client.getClientId().equals(clientRequestSubscriber.getClientId())){
                        connectToBrokerClientRequestSubscriber();
                    }else if(client.getClientId().equals(clientCourierSubscriber.getClientId())){
                        connectToBrokerClientCourierSubscriber();
                    }else if(client.getClientId().equals(clientOrderSubscriber.getClientId())){
                      Log.d("Debug","clientOrderSubscriber should be connected already but it is not");
                    }

                }
                IMqttToken subToken = client.subscribe(topic, qos);
                subToken.setActionCallback(new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        // The message was published
                        Log.d(TAG, client.getClientId() +" successfully subscribed to topic "+topic);

                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken,
                                          Throwable exception) {
                        // The subscription could not be performed
                        Log.d(TAG, client.getClientId() +"could not subscribe to topic"+topic);
                    }
                });
            }else{
                Log.d(TAG, client.getClientId() +"is not connected.Topic is empty");

            }

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void connectToBrokerClientOrderSubscriber(String orderId) {
        try {
            MqttConnectOptions options = setUpConnectionOptions(60, true);
            IMqttToken token = clientOrderSubscriber.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.d(TAG, clientOrderSubscriber.getClientId()+" onSuccess-Connected");
                    DisconnectedBufferOptions disconnectedBufferOpts = new DisconnectedBufferOptions();
                    disconnectedBufferOpts.setBufferEnabled(true);
                    disconnectedBufferOpts.setBufferSize(100);
                    disconnectedBufferOpts.setPersistBuffer(false);
                    disconnectedBufferOpts.setDeleteOldestMessages(false);
                    clientOrderSubscriber.setBufferOpts(disconnectedBufferOpts);
                    String topic="orders/"+clientOrderSubscriber.getClientId()+"/all_info/get/"+orderId;
                    publishToTopic(clientOrderSubscriber,topic, null,  false,2);

                    String responseTopic = "orders/"+clientOrderSubscriber.getClientId()+"/all_info/get/+/response";
                    subscribeToTopic(clientOrderSubscriber, responseTopic, 2);
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Log.d(TAG, clientOrderSubscriber.getClientId() +" onFailure -Something went wrong"+exception.getLocalizedMessage());

                }
            });
        } catch ( MqttException e) {
            e.printStackTrace();
        }
    }


    public void connectMqttClients(){
        connectToBrokerClientRequestSubscriber();
        connectToBrokerClientCourierSubscriber();
        //order subscriber connects for when order info is needed
    }


    @Override
    public void handleAcceptTask(TaskRequest taskRequest) {
        String acceptRequestTopic="orders/"+ CURRENT_COURIER_ID+"/"+taskRequest.getTaskId()+"/accept";
        if(clientRequestSubscriber.isConnected()){
            publishToTopic(clientRequestSubscriber,acceptRequestTopic, null,  true,2);
        }/*else{
            reconnectAndPublishReply(taskRequest);
        }*/
    }

/*    private void reconnectAndPublishReply(TaskRequest taskRequest) {
        try {
            Log.w(TAG, clientRequestSubscriber.getClientId()+" reconnectAndPublishReply() related to taksRequestId:"+taskRequest.getTaskId());
            String acceptRequestTopic="orders/"+ CURRENT_COURIER_ID+"/"+taskRequest.getTaskId()+"/accept";
            MqttConnectOptions options = setUpConnectionOptions(BROKER_CONNECTION_KEEP_ALIVE_INTERVAL,BROKER_CONNECTION_CLEAN_SESSION);
            IMqttToken token = clientRequestSubscriber.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.w(TAG, clientRequestSubscriber.getClientId()+" onSuccess-Connected");
                        //subscribeToTopic(clientRequestSubscriber, requestTopic, 2);
                        subscribeToTopic(clientRequestSubscriber, timeoutTopic,2);
                        publishToTopic(clientRequestSubscriber,acceptRequestTopic, null,  true,2);
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Log.w(TAG, clientRequestSubscriber.getClientId() +" onFailure -Something went wrong"+exception.getLocalizedMessage());

                }
            });
        } catch ( MqttException e) {
            e.printStackTrace();
        }
    }*/

    @Override
    public void handleDenyTask(TaskRequest taskRequest) {
        String denyRequestTopic="orders/"+ CURRENT_COURIER_ID+"/"+taskRequest.getTaskId()+"/deny";
        if(clientRequestSubscriber.isConnected()){
            publishToTopic(clientRequestSubscriber,denyRequestTopic, null,  true,2);
        }

    }

    @Override
    public void handleGetOrder(String orderId) {
        connectToBrokerClientOrderSubscriber(orderId);
    }

    @Override
    public void handleTaskCompleted(TaskRequest taskRequest) {
        String taskCompletedTopic="orders/"+ CURRENT_COURIER_ID+"/"+taskRequest.getTaskId()+"/completed";
        if(clientCourierSubscriber.isConnected()){
            publishToTopic(clientCourierSubscriber,taskCompletedTopic, ModelObjectsConverter.convertToJSON(taskRequest),  true,2);
        }else{
            reconnectAndPublishTaskCompleted(taskCompletedTopic, taskRequest);
        }
    }

    private void reconnectAndPublishTaskCompleted(String topic, TaskRequest taskRequest) {
        try {
            MqttConnectOptions options = setUpConnectionOptions(60, BROKER_CONNECTION_CLEAN_SESSION);
            IMqttToken token = clientCourierSubscriber.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.d(TAG, clientCourierSubscriber.getClientId()+" onSuccess-Connected");

                    publishToTopic(clientCourierSubscriber,topic,ModelObjectsConverter.convertToJSON(taskRequest), true,2);

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Log.d(TAG, clientCourierSubscriber.getClientId() +" onFailure -Something went wrong"+exception.getLocalizedMessage());

                }
            });
        } catch ( MqttException e) {
            e.printStackTrace();
        }
    }
}
