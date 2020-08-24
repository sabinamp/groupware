package ch.fhnw.ip6.citycourier.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.setContent
import ch.fhnw.ip6.citycourier.CityCourierApplication
import ch.fhnw.ip6.citycourier.mqttservice.BrokerClient


class MainActivity : AppCompatActivity() {
    private val IDENTIFIER_REQUESTCLIENT = "Android Client Request Subscriber"
    private val IDENTIFIER_TIMEOUTCLIENT = "Android Client Timeout Subscriber"
    private val mqttClientTaskRequestSubscriber by lazy {
        BrokerClient(
            this,
            IDENTIFIER_REQUESTCLIENT
        )

    }
    private val mqttClientTaskTimeoutSubscriber by lazy {
        BrokerClient(
            this,
            IDENTIFIER_TIMEOUTCLIENT
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appContainer = (application as CityCourierApplication).container

        setContent {
            CityCourierApp(appContainer = appContainer)
        }

        mqttClientTaskRequestSubscriber.connectToBroker()
        mqttClientTaskTimeoutSubscriber.connectToBroker()
    }


    override fun onDestroy() {
        mqttClientTaskRequestSubscriber.destroy()
        mqttClientTaskTimeoutSubscriber.destroy()
        super.onDestroy()
    }


}

