package ch.fhnw.ip6.citycourier.mqttservice;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class BrokerClient {
    private static final String HIVEMQ_ANDROID_CLIENT_USER_NAME = "hivemq-android-client";
   //private static final String HIVEMQ_ANDROID_CLIENT_PASSWORD = "<your-service-password>"

// Other options
    private static final  int BROKER_CONNECTION_TIMEOUT = 10;
    private static final  int BROKER_CONNECTION_KEEP_ALIVE_INTERVAL = 120;
    private static final  boolean BROKER_CONNECTION_CLEAN_SESSION = true;
    private static final boolean BROKER_CONNECTION_RECONNECT = true;
    private static final String TAG = "AndroidBrokerClient";
   /* private static final String IDENTIFIER_REQUEST_CLIENT="Android Client Request Subscriber";
    private static final String IDENTIFIER_TIMEOUT_CLIENT="Android Client Timeout Subscriber";*/
    private static final String HIVEMQ_MQTT_HOST = "tcp://127.0.0.1:1883";
    private MqttAndroidClient client;
    private String clientIdentifier;

   public BrokerClient(Context context, String clientIdentifier){
        this.clientIdentifier=clientIdentifier;
        client = new MqttAndroidClient(context, HIVEMQ_MQTT_HOST,
                        clientIdentifier);
    }
    public void connectToBroker(){
         try {
             MqttConnectOptions options = new MqttConnectOptions();
             options.setAutomaticReconnect(BROKER_CONNECTION_RECONNECT);
             options.setCleanSession(BROKER_CONNECTION_CLEAN_SESSION);
             options.setConnectionTimeout(BROKER_CONNECTION_TIMEOUT);
             options.setKeepAliveInterval(BROKER_CONNECTION_KEEP_ALIVE_INTERVAL);
            IMqttToken token = client.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.d(TAG, clientIdentifier+" onSuccess-Connected");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Log.d(TAG, clientIdentifier +" onFailure -Something went wrong"+exception.getLocalizedMessage());

                }
            });
        } catch ( MqttException e) {
            e.printStackTrace();
        }
    }

    public  void disconnectFromBroker(){
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
        client.unregisterResources();
        client.disconnect();
    }

    public void publishToTopic(String topic, String payload, boolean retained, int qos){
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

    public void subscribeToTopic(String topic, int qos){
        try {
            IMqttToken subToken = client.subscribe(topic, qos);
            subToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // The message was published
                    Log.d(TAG, client.getClientId() +" successfully subscribed to topic"+topic);
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken,
                                      Throwable exception) {
                    // The subscription could not be performed, maybe the user was not
                    // authorized to subscribe on the specified topic e.g. using wildcards
                    Log.d(TAG, client.getClientId() +"could not subscribe to topic"+topic);
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

}
