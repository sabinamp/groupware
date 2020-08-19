package ch.fhnw.ip6.citycourier.mqttservice;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class BrokerClientManager {
    private static final String TAG = "AndroidBrokerClient";


    public void connectToBroker(Activity activity){
        String clientId = MqttClient.generateClientId();
        MqttAndroidClient client =
                new MqttAndroidClient(activity, "tcp://127.0.0.1:1883",
                        clientId);
        try {
            IMqttToken token = client.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    Toast.makeText(activity, "Connected", Toast.LENGTH_LONG);
                    Log.d(TAG, "onSuccess");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Toast.makeText(activity, "Something went wrong e.g. connection timeout or firewall problems", Toast.LENGTH_LONG);
                    Log.d(TAG, "onFailure");

                }
            });
        } catch ( MqttException e) {
            e.printStackTrace();
        }
    }

}
