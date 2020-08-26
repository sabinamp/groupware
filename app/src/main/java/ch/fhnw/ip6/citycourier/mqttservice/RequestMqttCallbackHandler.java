package ch.fhnw.ip6.citycourier.mqttservice;

import android.content.Context;
import android.util.Log;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import ch.fhnw.ip6.citycourier.data.TaskRequestsRepository;
import ch.fhnw.ip6.citycourier.model.RequestReply;
import ch.fhnw.ip6.citycourier.model.TaskRequest;
import ch.fhnw.ip6.citycourier.mqttservice.util.ModelObjectsConverter;

public class RequestMqttCallbackHandler implements MqttCallbackExtended {
    private TaskRequestsRepository requestsRepository;

    public RequestMqttCallbackHandler(TaskRequestsRepository taskRequestsRepository){
        this.requestsRepository= taskRequestsRepository;
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

        if(received != null && topic.endsWith("request")){
            TaskRequest taskRequest= ModelObjectsConverter.convertJsonToTaskRequest(received);
            String taskId=taskRequest.getTaskId();
            Log.w("Debug", "task request received from host.Task id: "+ taskId);
            this.requestsRepository.addTaskRequest(taskRequest);
        }else if(topic.endsWith("timeout")){
            Log.w("Debug", topic+"task timeout");
            String[] topicLevels=topic.split("/");
            String taskId=topicLevels[2];
            this.requestsRepository.removeTaskRequest(taskId);
        }


    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        Log.w("Debug", "Message published to host "+ BrokerClient.HIVEMQ_MQTT_HOST);
        try {
            String[] topics= token.getTopics();
            String topic1=topics[0];
            String topic2= topics[1];
            String[] topic1Levels=topic1.split("/");
            String taskId=topic1Levels[2];
            if(token.getMessage().getPayload()==null && topic1.endsWith("deny")){
                this.requestsRepository.updateTask(taskId, RequestReply.DENIED);
            }else if(token.getMessage().getPayload()==null && topic1.endsWith("accept")){
                this.requestsRepository.updateTask(taskId, RequestReply.ACCEPTED);
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


}
