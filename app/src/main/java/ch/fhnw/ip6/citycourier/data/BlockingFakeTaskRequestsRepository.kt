package ch.fhnw.ip6.citycourier.data

import android.content.Context
import android.content.res.Resources
import android.os.Handler
import ch.fhnw.ip6.citycourier.model.*
import ch.fhnw.ip6.citycourier.mqttservice.RequestReplyEventListener
import ch.fhnw.ip6.citycourier.mqttservice.TaskCompletedEventListener
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime
import java.util.concurrent.ExecutorService
import kotlin.random.Random

/*
* Implementation that returns a hardcoded list of Task Requests
 */
class BlockingFakeTaskRequestsRepository(private val context: Context
): TaskRequestsRepository {

    private val taskRequests: MutableList<TaskRequest> by lazy{
        taskRequestData()

    }
    private var listeners: MutableList<RequestReplyEventListener> = mutableListOf()
    private var listenersCompleted: MutableList<TaskCompletedEventListener> = mutableListOf()

    override fun getTaskRequest(taskId: String, callback: (Result<TaskRequest?>) -> Unit) {
        callback(Result.Success(taskRequests.find { it.taskId == taskId }))
    }
    override fun getTaskRequests(callback: (Result<List<TaskRequest>>) -> Unit) {
        callback( Result.Success(taskRequests))

    }

    override fun getAcceptedTaskRequests(callback: (Result<List<TaskRequest>>) -> Unit) {
        callback( Result.Success(taskRequests.filter { t->t.confirmed.equals(RequestReply.ACCEPTED)}))
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
        runBlocking {launch {  taskRequests.find { it.taskId == taskId }?.confirmed(reply) }}
        return true
    }

    override fun addRequestReplyEventListener(index:Int, listener: RequestReplyEventListener){
        listeners.add(index, listener)
    }

    override fun getListeners(): MutableList<RequestReplyEventListener>{
        return listeners
    }
    private fun getListenersCompleted(): MutableList<TaskCompletedEventListener>{
        return listenersCompleted
    }

    override fun handleAcceptRequestEvent(taskRequest: TaskRequest, accepted: RequestReply) {
                for( each: RequestReplyEventListener in getListeners()){
                    if(accepted.equals(RequestReply.ACCEPTED)){
                        runBlocking {
                            launch {
                                each.handleAcceptTask(taskRequest)
                                updateTask(taskRequest.taskId, RequestReply.ACCEPTED)
                            }
                        }
                    }else if (accepted.equals(RequestReply.DENIED)){
                        runBlocking {
                            launch {
                                each.handleDenyTask(taskRequest)
                                updateTask(taskRequest.taskId, RequestReply.DENIED)
                            }
                        }
                    }
                }

    }

    override fun handleCompleteTaskEvent(taskRequest: TaskRequest, outcome:OrderStatus) {
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

    }
}
