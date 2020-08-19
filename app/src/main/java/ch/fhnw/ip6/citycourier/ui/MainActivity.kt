package ch.fhnw.ip6.citycourier.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.setContent
import ch.fhnw.ip6.citycourier.data.AppContainerImpl


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val appContainer = (application as CityCourierApplication).container
        val appContainer =  AppContainerImpl(this)
        setContent {
            CityCourierApp(appContainer = appContainer)
        }

    }
}

