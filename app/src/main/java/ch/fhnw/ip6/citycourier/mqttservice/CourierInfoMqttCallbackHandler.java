package ch.fhnw.ip6.citycourier.mqttservice;

import android.util.Log;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import ch.fhnw.ip6.citycourier.data.CourierRepository;
import ch.fhnw.ip6.citycourier.data.TaskRequestsRepository;
import ch.fhnw.ip6.citycourier.model.CourierInfo;
import ch.fhnw.ip6.citycourier.model.TaskRequest;
import ch.fhnw.ip6.citycourier.mqttservice.util.ModelObjectsConverter;

public class CourierInfoMqttCallbackHandler implements MqttCallbackExtended {
    private CourierRepository courierRepository;
    private String courierId;

    public CourierInfoMqttCallbackHandler(CourierRepository courierRepository, String courierId){
        this.courierRepository= courierRepository;
        this.courierId = courierId;
    }

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
        CourierInfo courierInfo= ModelObjectsConverter.convertJsonToCourierInfo(received);

        Log.w("Debug", "courier id:"+ courierId+ "courier info received from host.");
        this.courierRepository.setCourierInfo(courierInfo);

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        Log.w("Debug", "Message published to host "+ BrokerClient.HIVEMQ_MQTT_HOST);
    }
}
