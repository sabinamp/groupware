package ch.fhnw.ip6.citycourier

import android.app.Application
import ch.fhnw.ip6.citycourier.data.AppContainer
import ch.fhnw.ip6.citycourier.data.AppContainerImpl

class CityCourierApplication: Application() {
    // AppContainer instance used by the rest of classes to obtain dependencies
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
    }
}