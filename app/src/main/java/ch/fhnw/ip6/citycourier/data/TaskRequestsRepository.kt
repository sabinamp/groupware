package ch.fhnw.ip6.citycourier.data

import ch.fhnw.ip6.citycourier.model.RequestReply
import ch.fhnw.ip6.citycourier.model.TaskRequest

interface TaskRequestsRepository{

    /**
     * Get a specific task request.
     */
    fun getTaskRequest(taskId: String, callback: (Result<TaskRequest?>) -> Unit)
    /**
     * Get list of task requests.
     */
    fun getTaskRequests(callback: (Result<List<TaskRequest>>) -> Unit)

    /**
     * Get list of accepted task requests.
     */
   fun getAcceptedTaskRequests(callback: (Result<List<TaskRequest>>) -> Unit)

    /**
     * Add a specific task request.
     */
    fun addTaskRequest(task: TaskRequest): Boolean

    /**
     * Remove a specific task request.
     */
    fun removeTaskRequest(taskId: String): Boolean

    /**
     * Update task request after sending a reply to the dispatcher
     */
   // fun updateTaskRequest(id: Long, newTaskState: RequestReply): Boolean

}