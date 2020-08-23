package ch.fhnw.ip6.citycourier.mqttservice

import org.eclipse.paho.client.mqttv3.MqttException

fun connectToBrokerSubscribeToTopic(mqttClient: MqttClientHelper, topic:String):String{
   var msg= try {
        mqttClient.subscribe(topic)
        "Subscribed to topic '$topic'"
    } catch (ex: MqttException) {
        "Error subscribing to topic: $topic"
    }
    return msg
}

