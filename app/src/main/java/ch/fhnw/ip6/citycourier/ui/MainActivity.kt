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


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appContainer = (application as CityCourierApplication).container
       var msg1= connectToBrokerSubscribeToTopic(mqttClient,"orders/"+ CURRENT_COURIER_ID+"/+/request")
        connectToBrokerSubscribeToTopic(mqttClient,"orders/"+ CURRENT_COURIER_ID+"/+/timeout")
        setContent {
            CityCourierApp(appContainer = appContainer)
        }

    }

    val mqttClient by lazy {
        MqttClientHelper(this)
    }

    override fun onDestroy() {
        mqttClient.destroy()
        super.onDestroy()
    }
}

