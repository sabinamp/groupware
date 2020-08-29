package ch.fhnw.ip6.citycourier.ui

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.setContent
import ch.fhnw.ip6.citycourier.CityCourierApplication
import ch.fhnw.ip6.citycourier.mqttservice.BrokerClient



class MainActivity : AppCompatActivity() {

    private  val mqttClient by lazy{
            BrokerClient()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appContainer = (application as CityCourierApplication).container

        setContent {
            CityCourierApp(appContainer = appContainer)
        }

        mqttClient.createClients(this, appContainer.taskRequestRepository, appContainer.courierRepository)
        appContainer.taskRequestRepository.addRequestReplyEventListener(0, mqttClient)

        mqttClient.connectMqttClients()
    }


    override fun onDestroy() {
        mqttClient.disconnectMQttClients()
        super.onDestroy()
    }




}

