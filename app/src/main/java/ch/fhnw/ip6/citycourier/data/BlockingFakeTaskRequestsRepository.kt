package ch.fhnw.ip6.citycourier.data

import android.content.Context
import android.content.res.Resources
import android.os.Handler
import ch.fhnw.ip6.citycourier.model.*
import ch.fhnw.ip6.citycourier.mqttservice.RequestReplyEventListener
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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
        GlobalScope.launch {  taskRequests.find { it.taskId == taskId }?.confirmed(reply) }
         return true
    }

    override fun addRequestReplyEventListener(index:Int, listener: RequestReplyEventListener){
        listeners.add(index, listener)
    }

    override fun getListeners(): MutableList<RequestReplyEventListener>{
        return listeners
    }

    override fun handleAcceptRequestEvent(taskRequest: TaskRequest, accepted: RequestReply) {
        GlobalScope.launch {
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
