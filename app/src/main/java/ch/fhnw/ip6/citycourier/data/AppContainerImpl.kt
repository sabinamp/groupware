package ch.fhnw.ip6.citycourier.data


import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import android.content.Context
import android.os.Handler
import android.os.Looper


/**
 * Dependency Injection container at the application level.
 */
interface AppContainer {
    val taskRequestRepository: TaskRequestsRepository
    val ordersRepository: OrdersRepository
    val courierRepository: CourierRepository
}

/**
 * Implementation for the Dependency Injection container at the application level.
 *
 * Variables are initialized lazily and the same instance is shared across the whole app.
 */
class AppContainerImpl(private val applicationContext: Context) : AppContainer {

    private val executorService: ExecutorService by lazy {
        Executors.newFixedThreadPool(4)
    }

    private val mainThreadHandler: Handler by lazy {
        Handler(Looper.getMainLooper())
    }

    override val taskRequestRepository: TaskRequestsRepository by lazy {
        FakeTaskRequestsRepository(executorService = executorService,
            resultThreadHandler = mainThreadHandler,
            resources = applicationContext.resources)
    }



    override val courierRepository: CourierRepository by lazy {
      FakeCourierRepository(executorService = executorService,
          resultThreadHandler = mainThreadHandler,
          resources = applicationContext.resources)
    }

    override val ordersRepository: OrdersRepository by lazy {
        ImplOrderRepository(executorService = executorService,
            resultThreadHandler = mainThreadHandler,
            resources = applicationContext.resources)
    }

}




