package ch.fhnw.ip6.citycourier.mqttservice;

import android.content.Context;
import android.util.Log;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.nio.charset.StandardCharsets;

import ch.fhnw.ip6.citycourier.data.CourierRepository;
import ch.fhnw.ip6.citycourier.data.CurrentCourierIdKt;
import ch.fhnw.ip6.citycourier.data.TaskRequestsRepository;
import ch.fhnw.ip6.citycourier.model.TaskRequest;

public class BrokerClient implements RequestReplyEventListener{
    private static final String HIVEMQ_ANDROID_CLIENT_USER_NAME = "hivemq-android-client";
   //private static final String HIVEMQ_ANDROID_CLIENT_PASSWORD = "<your-service-password>"

  // Other options
    public static final  int BROKER_CONNECTION_TIMEOUT = 5;
    public static final  int BROKER_CONNECTION_KEEP_ALIVE_INTERVAL = 240;
    public static final  boolean BROKER_CONNECTION_CLEAN_SESSION = true;
    public static final boolean BROKER_CONNECTION_RECONNECT = true;
    private static final String TAG = "AndroidBrokerClient";

    static final String CURRENT_COURIER_ID = "C106";
    private static final String  requestTopic = "orders/"+CURRENT_COURIER_ID+"/+/request";
    private static final String  timeoutTopic = "orders/"+CURRENT_COURIER_ID+"/+/timeout";
    private static final String  publishRequestReplyTopicOK = "orders/"+CURRENT_COURIER_ID+"/+/accept";
    private static final String  publishRequestReplyTopicNO = "orders/"+CURRENT_COURIER_ID+"/+/deny";
    private static final String  publishRequestCompletedTopic = "orders/"+CURRENT_COURIER_ID+"/+/completed";

    public static final String IDENTIFIER_REQUESTCLIENT = "Android Client Request Subscriber";
    public static final String IDENTIFIER_COURIERCLIENT = "Android Client Courier Info Subscriber";

    static final String HIVEMQ_MQTT_HOST = "tcp://192.168.0.108:1883";
    MqttAndroidClient clientRequestSubscriber ;
    MqttAndroidClient clientCourierSubscriber;

    public BrokerClient(Context context, TaskRequestsRepository taskRequestsRepository, CourierRepository courierRepository){

         clientRequestSubscriber = new MqttAndroidClient(context, HIVEMQ_MQTT_HOST, IDENTIFIER_REQUESTCLIENT);
        //Set callback handler
        clientRequestSubscriber.setCallback(new RequestMqttCallbackHandler(taskRequestsRepository));
        clientCourierSubscriber = new MqttAndroidClient( context,HIVEMQ_MQTT_HOST, IDENTIFIER_COURIERCLIENT);
        clientCourierSubscriber.setCallback(new CourierInfoMqttCallbackHandler(courierRepository, CURRENT_COURIER_ID));

    }

    private void connectToBroker(MqttAndroidClient client){
         try {
             MqttConnectOptions options = new MqttConnectOptions();
             //The MqttAndroidClient will connect with MQTT 3.1.1 by default
             options.setAutomaticReconnect(BROKER_CONNECTION_RECONNECT);
             options.setCleanSession(BROKER_CONNECTION_CLEAN_SESSION);
             options.setConnectionTimeout(BROKER_CONNECTION_TIMEOUT);
             options.setKeepAliveInterval(BROKER_CONNECTION_KEEP_ALIVE_INTERVAL);
            IMqttToken token = client.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.d(TAG, client.getClientId()+" onSuccess-Connected");
                    if(client.getClientId().equals(IDENTIFIER_REQUESTCLIENT)){
                        subscribeToTopic(clientRequestSubscriber, requestTopic, 2);
                        subscribeToTopic(clientRequestSubscriber, timeoutTopic,2);
                    }else if(client.getClientId().equals(IDENTIFIER_COURIERCLIENT)){

                        String courierInfoTopic = "couriers/info/get/"+CURRENT_COURIER_ID;
                        publishToTopic(clientCourierSubscriber,courierInfoTopic,null, true,2);
                        String courierInfoResponseTopic = "couriers/info/get/"+CURRENT_COURIER_ID+"/response";
                        subscribeToTopic(clientCourierSubscriber, courierInfoResponseTopic, 2);
                    }
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Log.d(TAG, client.getClientId() +" onFailure -Something went wrong"+exception.getLocalizedMessage());

                }
            });
        } catch ( MqttException e) {
            e.printStackTrace();
        }
    }

    private void disconnectFromBroker(MqttAndroidClient client){
        try {
            IMqttToken disconToken = client.disconnect();
            disconToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // we are now successfully disconnected
                    Log.d(TAG, client.getClientId() +" successfully disconnected");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken,
                                      Throwable exception) {
                    // something went wrong, but probably we are disconnected anyway
                    Log.d(TAG, client.getClientId() +" onFailure -Something went wrong"+exception.getLocalizedMessage());
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    public void destroy() throws MqttException {
        clientRequestSubscriber.unregisterResources();
        clientRequestSubscriber.disconnect();
        clientCourierSubscriber.unregisterResources();
        clientCourierSubscriber.disconnect();
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
                    connectToBroker(client);
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
                        // The subscription could not be performed, maybe the user was not
                        // authorized to subscribe on the specified topic e.g. using wildcards
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




    public void connectBothClients(){
        connectToBroker(clientRequestSubscriber);
        connectToBroker(clientCourierSubscriber);
    }


    @Override
    public void handleAcceptTask(TaskRequest taskRequest) {
        String acceptRequestTopic="orders/"+ CURRENT_COURIER_ID+"/"+taskRequest.getTaskId()+"/accept";
        publishToTopic(clientRequestSubscriber,acceptRequestTopic, null,  true,2);

    }

    @Override
    public void handleDenyTask(TaskRequest taskRequest) {
        String denyRequestTopic="orders/"+ CURRENT_COURIER_ID+"/"+taskRequest.getTaskId()+"/deny";
        publishToTopic(clientRequestSubscriber,denyRequestTopic, null,  true,2);
    }
}
