package ch.fhnw.ip6.citycourier.mqttservice;

import android.util.Log;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import ch.fhnw.ip6.citycourier.data.OrdersRepository;
import ch.fhnw.ip6.citycourier.data.TaskRequestsRepository;
import ch.fhnw.ip6.citycourier.model.OrderDescriptiveInfo;
import ch.fhnw.ip6.citycourier.model.TaskRequest;
import ch.fhnw.ip6.citycourier.mqttservice.util.ModelObjectsConverter;

public class OrderMqttCallbackHandler implements MqttCallbackExtended {
    private OrdersRepository orderRepository;

    public OrderMqttCallbackHandler(OrdersRepository orderRepository){
        this.orderRepository= orderRepository;
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

        if(received != null && topic.startsWith("orders")){
            OrderDescriptiveInfo orderData= ModelObjectsConverter.convertJsonToOrderDescriptiveInfo(received);
            if(orderData != null){
                String orderId= orderData.getOrderId();
                Log.w("Debug", "order data received from host.Order id: "+ orderId);
                this.orderRepository.addOrder(orderId,orderData);
            }
        }

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        Log.w("Debug", "Message published to host "+ BrokerClient.HIVEMQ_MQTT_HOST);


    }


}
