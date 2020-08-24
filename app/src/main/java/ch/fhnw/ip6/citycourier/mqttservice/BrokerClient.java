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

import ch.fhnw.ip6.citycourier.model.TaskRequest;
import ch.fhnw.ip6.citycourier.mqttservice.util.ModelObjectsConverter;

public class BrokerClient {
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
    public static final String IDENTIFIER_TIMEOUTCLIENT = "Android Client Timeout Subscriber";

    static final String HIVEMQ_MQTT_HOST = "tcp://192.168.0.108:1883";
    MqttAndroidClient clientRequestSubscriber ;
    MqttAndroidClient clientTimeoutSubscriber;

    public BrokerClient(Context context){
         clientRequestSubscriber = new MqttAndroidClient(context,
                HIVEMQ_MQTT_HOST,
                IDENTIFIER_REQUESTCLIENT
        );
        //Set callback handler
        clientRequestSubscriber.setCallback(new MqttCallbackHandler());
         clientTimeoutSubscriber = new MqttAndroidClient( context,HIVEMQ_MQTT_HOST, IDENTIFIER_TIMEOUTCLIENT);
        clientTimeoutSubscriber.setCallback(new MqttCallbackHandler());

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
                    }else if(client.getClientId().equals(IDENTIFIER_TIMEOUTCLIENT)){
                        subscribeToTopic(clientTimeoutSubscriber, timeoutTopic,2);
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
        clientTimeoutSubscriber.unregisterResources();
        clientTimeoutSubscriber.disconnect();
    }

    private void publishToTopic(MqttAndroidClient client, String topic, String payload, boolean retained, int qos){
        byte[] encodedPayload = new byte[0];
        try {
            encodedPayload = payload.getBytes(StandardCharsets.UTF_8);
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

    /*public void setMqttCallBack(MqttAndroidClient client) {
        client.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean reconnect, String serverURI) {
                String msg = "Connected to host:\n"+ HIVEMQ_MQTT_HOST;
                Log.w("Debug", msg);
            }

            @Override
            public void connectionLost(Throwable cause) {
                String msg = "Connected to host lost:\n"+ HIVEMQ_MQTT_HOST;
                Log.w("Debug", msg);
            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                Log.w("Debug", "Message received from host "+ HIVEMQ_MQTT_HOST+ mqttMessage);
                String received= mqttMessage.toString();

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                Log.w("Debug", "Message published to host "+ HIVEMQ_MQTT_HOST);
            }
        });
    }*/


public void connectBothClients(){
    connectToBroker(clientRequestSubscriber);

    connectToBroker(clientTimeoutSubscriber);
}

}
