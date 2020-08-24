package ch.fhnw.ip6.citycourier.mqttservice;

import android.util.Log;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import ch.fhnw.ip6.citycourier.model.TaskRequest;
import ch.fhnw.ip6.citycourier.mqttservice.util.ModelObjectsConverter;

public class MqttCallbackHandler implements MqttCallbackExtended {
    @Override
    public void connectComplete(boolean reconnect, String serverURI) {
        String msg = "Connected to host:\n"+ BrokerClient.HIVEMQ_MQTT_HOST;
        Log.w("Debug", msg);
    }

    @Override
    public void connectionLost(Throwable cause) {
        String msg = "Connected to host lost:\n"+ BrokerClient.HIVEMQ_MQTT_HOST;
        Log.w("Debug", msg);
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) {
        Log.w("Debug", "Message received from host "+ BrokerClient.HIVEMQ_MQTT_HOST+ mqttMessage);
        String received= mqttMessage.toString();
        TaskRequest taskRequest= ModelObjectsConverter.convertJsonToTaskRequest(received);
        if(taskRequest != null){
            String taskId=taskRequest.getTaskId();
            Log.w("Debug", "task request received from host .Task id: "+ taskId);
        }


    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        Log.w("Debug", "Message published to host "+ BrokerClient.HIVEMQ_MQTT_HOST);
    }
}
