package ch.fhnw.ip6.citycourier.data

import android.content.res.Resources
import android.os.Handler
import ch.fhnw.ip6.citycourier.model.*
import ch.fhnw.ip6.citycourier.mqttservice.MqttClientHelper
import ch.fhnw.ip6.citycourier.mqttservice.connectToBrokerSubscribeToTopic
import ch.fhnw.ip6.citycourier.ui.MainActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.concurrent.ExecutorService
import kotlin.random.Random

/*
* Implementation that returns a hardcoded list of Task Requests
 */
class MqttTaskRequestsRepository(private val executorService: ExecutorService,
                                 private val resultThreadHandler: Handler,
                                 private val resources: Resources
): TaskRequestsRepository {
    private val taskRequests: MutableList<TaskRequest> = initTasks()

    private fun initTasks(): MutableList<TaskRequest> {

        //TODO

        return taskRequestDataFromBroker()
    }

    override fun getTaskRequest(taskId: String, callback: (Result<TaskRequest?>) -> Unit) {
        executeInBackground(callback) {
            resultThreadHandler.post {
                callback(
                    Result.Success(
                        taskRequests.find { it.taskId == taskId }
                ))
            }
        }
    }

    override fun getTaskRequests(callback: (Result<List<TaskRequest>>) -> Unit) {

        executeInBackground(callback) {
            simulateNetworkRequest()

            if (shouldRandomlyFail()) {
                throw IllegalStateException()
            }
            resultThreadHandler.post { callback(Result.Success(taskRequests)) }
        }
    }


    override fun getAcceptedTaskRequests(callback: (Result<List<TaskRequest>>) -> Unit) {
        callback( Result.Success(taskRequests.filter { t->t.confirmed.equals(RequestReply.ACCEPTED)}))
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
            GlobalScope.launch { // launch a new coroutine in background and continue
                delay(1500L) // non-blocking delay for 1 second (default time unit is ms)
                println("World!") // print after delay
            }
            networkRequestDone = true
        }
    }

    /**
     * 1/3 requests should fail loading
     */
    private fun shouldRandomlyFail(): Boolean = Random.nextFloat() < 0.33f
}
