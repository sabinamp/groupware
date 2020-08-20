package ch.fhnw.ip6.citycourier.data

import android.content.res.Resources
import android.os.Handler
import ch.fhnw.ip6.citycourier.model.*
import java.time.LocalDateTime
import java.util.concurrent.ExecutorService
import kotlin.random.Random

/*
* Implementation that returns a hardcoded list of Task Requests
 */
class FakeTaskRequestsRepository(private val executorService: ExecutorService,
                                 private val resultThreadHandler: Handler,
                                 private val resources: Resources
): TaskRequestsRepository {

    private val taskRequests: List<TaskRequest> by lazy{
    taskRequestList
    }

    override fun getTaskRequest(id: String, callback: (Result<TaskRequest?>) -> Unit) {
        executeInBackground(callback) {
            resultThreadHandler.post {
                callback(
                    Result.Success(
                        taskRequests.find { it.taskId == id }
                ))
            }
        }
    }

    override fun getTaskRequests(callback: (Result<List<TaskRequest>>) -> Unit) {
        //callback( Result.Success(taskRequests))
        executeInBackground(callback) {
            simulateNetworkRequest()
            Thread.sleep(1500L)
            if (shouldRandomlyFail()) {
                throw IllegalStateException()
            }
            resultThreadHandler.post { callback(Result.Success(taskRequests)) }
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

    /**
     * Simulates network request
     */
    private var networkRequestDone = false
    private fun simulateNetworkRequest() {
        if (!networkRequestDone) {
            Thread.sleep(2000L)
            networkRequestDone = true
        }
    }

    /**
     * 1/3 requests should fail loading
     */
    private fun shouldRandomlyFail(): Boolean = Random.nextFloat() < 0.33f
}
