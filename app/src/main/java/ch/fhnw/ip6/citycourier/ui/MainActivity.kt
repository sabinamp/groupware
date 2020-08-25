package ch.fhnw.ip6.citycourier.ui

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.setContent
import ch.fhnw.ip6.citycourier.CityCourierApplication
import ch.fhnw.ip6.citycourier.mqttservice.BrokerClient



class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appContainer = (application as CityCourierApplication).container

        setContent {
            CityCourierApp(appContainer = appContainer)
        }
        val mqttClient by lazy{
            BrokerClient(this, appContainer.taskRequestRepository, appContainer.courierRepository)
        }


        mqttClient.connectBothClients()
    }


    override fun onDestroy() {
     //   mqttClient.destroy()
        super.onDestroy()
    }




}

