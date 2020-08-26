package ch.fhnw.ip6.citycourier.data

import android.content.Context
import android.content.res.Resources
import android.os.Handler
import ch.fhnw.ip6.citycourier.model.*
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

         taskRequests.find { it.taskId == taskId }?.confirmed(reply)
         return true
    }
}
