package ch.fhnw.ip6.citycourier.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.setContent
import ch.fhnw.ip6.citycourier.CityCourierApplication
import ch.fhnw.ip6.citycourier.mqttservice.BrokerClient
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended
import org.eclipse.paho.client.mqttv3.MqttMessage


class MainActivity : AppCompatActivity() {

    private val mqttClient =
        BrokerClient(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appContainer = (application as CityCourierApplication).container

        setContent {
            CityCourierApp(appContainer = appContainer)
        }


        mqttClient.connectBothClients()
    }


    override fun onDestroy() {
        mqttClient.destroy()
        super.onDestroy()
    }




}

