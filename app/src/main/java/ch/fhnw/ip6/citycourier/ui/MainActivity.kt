package ch.fhnw.ip6.citycourier.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.ui.core.ContextAmbient
import androidx.ui.core.setContent
import ch.fhnw.ip6.citycourier.CityCourierApplication
import ch.fhnw.ip6.citycourier.data.CURRENT_COURIER_ID
import ch.fhnw.ip6.citycourier.mqttservice.MqttClientHelper
import ch.fhnw.ip6.citycourier.mqttservice.connectToBrokerSubscribeToTopic

import ch.fhnw.ip6.citycourier.ui.util.makePhoneCall
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appContainer = (application as CityCourierApplication).container

        setContent {
            CityCourierApp(appContainer = appContainer)
        }

    }

    private val mqttClientTaskRequestSubscriber by lazy {
        MqttClientHelper(this)

    }
    private val mqttClientTaskTimeoutSubscriber by lazy {
        MqttClientHelper(this)

    }
    override fun onDestroy() {
        mqttClientTaskRequestSubscriber.destroy()
        mqttClientTaskTimeoutSubscriber.destroy()
        super.onDestroy()
    }
    /**
     *  network request
     */
    private var networkRequestDone = false
    private fun connectToMqttBroker() {
        //TODO subscribe to orders/CourierId/+/request
        //TODO subscribe to orders/CourierId/+/timeout
        GlobalScope.launch { // launch a new coroutine in background and continue
            var msg1 = connectToBrokerSubscribeToTopic(
                mqttClientTaskRequestSubscriber,
                "orders/" + CURRENT_COURIER_ID + "/+/request"
            )
            connectToBrokerSubscribeToTopic(
                mqttClientTaskTimeoutSubscriber,
                "orders/" + CURRENT_COURIER_ID + "/+/timeout"
            )
        }
        networkRequestDone=true
    }
}

