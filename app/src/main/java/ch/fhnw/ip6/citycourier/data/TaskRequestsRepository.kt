package ch.fhnw.ip6.citycourier.data

import ch.fhnw.ip6.citycourier.model.RequestReply
import ch.fhnw.ip6.citycourier.model.TaskRequest
import ch.fhnw.ip6.citycourier.mqttservice.RequestReplyEventListener

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
     * Update a specific task request.
     */
    fun updateTask(taskId:String, reply:RequestReply): Boolean

    fun addRequestReplyEventListener(index:Int, listener: RequestReplyEventListener)

    fun getListeners(): MutableList<RequestReplyEventListener>

   fun handleAcceptRequestEvent(taskRequest:TaskRequest, accepted: RequestReply)
}