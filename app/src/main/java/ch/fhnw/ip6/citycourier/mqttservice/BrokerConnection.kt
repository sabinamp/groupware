package ch.fhnw.ip6.citycourier.mqttservice

import android.util.Log
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended
import org.eclipse.paho.client.mqttv3.MqttException
import org.eclipse.paho.client.mqttv3.MqttMessage


fun connectToBrokerSubscribeToTopic(mqttClient: MqttClientHelper, topic:String):String{
   val msg= try {
        mqttClient.subscribe(topic)
        "Subscribed to topic '$topic'"
    } catch (ex: MqttException) {
        "Error subscribing to topic: $topic"
    }
    return msg
}

private fun setMqttCallBack(mqttClient: MqttClientHelper,) {
    mqttClient.setCallback(object : MqttCallbackExtended {
        override fun connectComplete(b: Boolean, s: String) {
            val snackbarMsg = "Connected to host:\n'$HIVEMQ_MQTT_HOST'."
            Log.w("Debug", snackbarMsg)

        }
        override fun connectionLost(throwable: Throwable) {
            val snackbarMsg = "Connection to host lost:\n'$HIVEMQ_MQTT_HOST'"
            Log.w("Debug", snackbarMsg)

        }
        //@Throws(Exception::class)
        override fun messageArrived(topic: String, mqttMessage: MqttMessage) {
            Log.w("Debug", "Message received from host '$HIVEMQ_MQTT_HOST': $mqttMessage")

        }

        override fun deliveryComplete(iMqttDeliveryToken: IMqttDeliveryToken) {
            Log.w("Debug", "Message published to host '$HIVEMQ_MQTT_HOST'")
        }
    })
}


