package ch.fhnw.ip6.citycourier.data


import android.content.res.Resources
import android.os.Handler
import ch.fhnw.ip6.citycourier.model.CourierInfo
import java.util.concurrent.ExecutorService

class FakeCourierRepository(private val executorService: ExecutorService,
                            private val resultThreadHandler: Handler,
                            private val resources: Resources): CourierRepository {
    private val courierInfoC106: CourierInfo by lazy {
        createCourierInfoForPreview()
    }

    override fun getCourier(courierId: String, callback: (Result<CourierInfo>) -> Unit) {
        executeInBackground(callback) {
            resultThreadHandler.post {
                callback(
                    Result.Success(
                        courierInfoC106
                    ))
            }
        }
    }

    /**
     * Executes a block of code in the past and returns an error in the [callback]
     * if [block] throws an exception.
     */
    private fun executeInBackground(callback: (Result<Nothing>) -> Unit, block: () -> Unit) {
        executorService.execute {
            try {
                block()
            } catch (e: Exception) {
                resultThreadHandler.post { callback(Result.Error(e)) }
            }
        }
    }

}
