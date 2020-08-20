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

    private val taskRequests: List<TaskRequest> by lazy{
       taskRequestList
    }
    override fun getTaskRequest(id: String, callback: (Result<TaskRequest?>) -> Unit) {
        callback(Result.Success(taskRequests.find { it.taskId == id }))
    }
    override fun getTaskRequests(callback: (Result<List<TaskRequest>>) -> Unit) {
        callback( Result.Success(taskRequests))

    }

}
