package ch.fhnw.ip6.citycourier.mqttservice

import android.util.Log
import ch.fhnw.ip6.citycourier.data.CURRENT_COURIER_ID
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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



/**
 *  network request
 */
private var networkRequestDone = false
private fun connectToMqttBroker(mqttClientTaskRequestSubscriber: MqttClientHelper) {
    //TODO subscribe to orders/CourierId/+/request
    //TODO subscribe to orders/CourierId/+/timeout
    GlobalScope.launch { // launch a new coroutine in background and continue
        var msg1 = connectToBrokerSubscribeToTopic(
            mqttClientTaskRequestSubscriber,
            "orders/" + CURRENT_COURIER_ID + "/+/request"
        )
        /*   connectToBrokerSubscribeToTopic(
               mqttClientTaskTimeoutSubscriber,
               "orders/" + CURRENT_COURIER_ID + "/+/timeout"
           )*/
    }
    networkRequestDone=true
}