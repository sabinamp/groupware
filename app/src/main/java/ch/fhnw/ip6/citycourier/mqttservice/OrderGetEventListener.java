package ch.fhnw.ip6.citycourier.mqttservice;

import ch.fhnw.ip6.citycourier.model.TaskRequest;

public interface OrderGetEventListener {
    void handleGetOrder(String orderId);

}
