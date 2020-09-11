package ch.fhnw.ip6.citycourier.data

import android.content.res.Resources
import android.os.Handler
import androidx.compose.launchInComposition
import ch.fhnw.ip6.citycourier.model.OrderStatus
import ch.fhnw.ip6.citycourier.model.RequestReply
import ch.fhnw.ip6.citycourier.model.TaskRequest
import ch.fhnw.ip6.citycourier.mqttservice.RequestReplyEventListener
import ch.fhnw.ip6.citycourier.mqttservice.TaskCompletedEventListener
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime
import java.util.concurrent.ExecutorService
import kotlin.random.Random

/*
* Implementation that returns a hardcoded list of Task Requests
 */
class FakeTaskRequestsRepository(
    private val executorService: ExecutorService,
    private val resultThreadHandler: Handler,
    private val resources: Resources
): TaskRequestsRepository {

    private val taskRequests: MutableList<TaskRequest> by lazy{
        taskRequestData()
    }

    private var listeners: MutableList<RequestReplyEventListener> = mutableListOf()
    private var listenersCompleted: MutableList<TaskCompletedEventListener> = mutableListOf()

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
        //callback( Result.Success(taskRequests))
        executeInBackground(callback) {
           // simulateNetworkRequest()
            if (shouldRandomlyFail()) {
                throw IllegalStateException()
            }
            resultThreadHandler.post { callback(Result.Success(taskRequests)) }
        }
    }

    override fun getAcceptedTaskRequests(callback: (Result<List<TaskRequest>>) -> Unit) {
        callback(Result.Success(taskRequests.filter { t -> t.confirmed.equals(RequestReply.ACCEPTED) }))
    }

    override fun addTaskRequest(task: TaskRequest): Boolean {
        taskRequests.add(task)
        return true
    }

    override fun removeTaskRequest(taskId: String): Boolean {
       var removed =false
       for(task: TaskRequest in taskRequests){
           if(task.taskId.equals(taskId)){
                removed=taskRequests.remove(task)
           }
       }
        return removed
    }

    override fun updateTask(taskId: String, reply: RequestReply): Boolean {
        taskRequests.find { it.taskId == taskId }?.confirmed(reply)
        return true
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

    override fun addRequestReplyEventListener(index: Int, listener: RequestReplyEventListener){
        listeners.add(index, listener)
    }

    override fun getListeners(): MutableList<RequestReplyEventListener>{
        return listeners
    }

    private fun getListenersCompleted(): MutableList<TaskCompletedEventListener>{
        return listenersCompleted
    }

    override fun handleAcceptRequestEvent(taskRequest: TaskRequest, accepted: RequestReply) {
        runBlocking {
            launch{
                for( each: RequestReplyEventListener in getListeners()){
                    if(accepted.equals(RequestReply.ACCEPTED)){
                        each.handleAcceptTask(taskRequest)
                        updateTask(taskRequest.taskId, RequestReply.ACCEPTED)
                    }else if (accepted.equals(RequestReply.DENIED)){
                        each.handleDenyTask(taskRequest)
                        updateTask(taskRequest.taskId, RequestReply.DENIED)
                    }
                }
            }
        }

    }

    override fun handleCompleteTaskEvent(taskRequest: TaskRequest,outcome: OrderStatus) {
        taskRequest.completedWhen= LocalDateTime.now()
        taskRequest.isDone=true
        taskRequest.outcome= outcome
        runBlocking {
            launch{
                for( each: TaskCompletedEventListener in getListenersCompleted()){
                    each.handleTaskCompleted(taskRequest)
                }
            }
        }
        removeTaskRequest(taskRequest.taskId)
    }


    /**
     * 1/3 requests should fail loading
     */
    private fun shouldRandomlyFail(): Boolean = Random.nextFloat() < 0.33f
}
