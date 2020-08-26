package ch.fhnw.ip6.citycourier.mqttservice;

import ch.fhnw.ip6.citycourier.model.TaskRequest;

public interface RequestReplyEventListener {
    void handleAcceptTask(TaskRequest taskRequest);
    void handleDenyTask(TaskRequest taskRequest);
}
