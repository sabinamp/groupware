package ch.fhnw.ip6.citycourier.data

import ch.fhnw.ip6.citycourier.model.RequestReply
import ch.fhnw.ip6.citycourier.model.TaskRequest

interface TaskRequestsRepository{

    /**
     * Get a specific task request.
     */
    fun getTaskRequest(postId: String, callback: (Result<TaskRequest?>) -> Unit)
    /**
     * Get list of task requests.
     */
    fun getTaskRequests(callback: (Result<List<TaskRequest>>) -> Unit)

    /**
     * Get list of accepted task requests.
     */
   fun getAcceptedTaskRequests(callback: (Result<List<TaskRequest>>) -> Unit)

    /**
     * Update task request before sendind a reply to the dispatcher
     */
   // fun updateTaskRequest(id: Long, newTaskState: RequestReply): Boolean

}