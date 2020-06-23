package ch.fhnw.ip6.citycourier.data

import ch.fhnw.ip6.citycourier.model.TaskRequest

interface TaskRequestsRepository{


    /**
     * Get list of task requests.
     */
    fun getTaskRequests(callback: (Result<List<TaskRequest>>) -> Unit)

    /**
     * Get list of accepted task requests.
     */
//    fun getAcceptedTaskRequests(callback: (Result<List<TaskRequest>>) -> Unit)

}