package ch.fhnw.ip6.citycourier.data

import ch.fhnw.ip6.citycourier.model.DeliveryType
import ch.fhnw.ip6.citycourier.model.RequestReply
import ch.fhnw.ip6.citycourier.model.TaskRequest
import ch.fhnw.ip6.citycourier.model.TaskType
import java.time.LocalDateTime
/**
 * Define hardcoded task notifications to avoid handling any non-ui operations.
 */

private fun createTaskRequest(taskId:String, orderId:String, assigneeId:String, deliveryType: DeliveryType,taskType: TaskType,
                              nbh: Long, accepted: RequestReply): TaskRequest{
    val notification = TaskRequest()
    notification.taskId = taskId
    notification.orderId = orderId
    notification.assigneeId = assigneeId
    notification.confirmed = accepted
    notification.deliveryType = deliveryType
    notification.taskType =  taskType

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
        notification.dueOn= LocalDateTime.now().plusHours(nbh)
        notification.sentWhen= LocalDateTime.now()
    }
    return notification
}

val notification1 = createTaskRequest("T1","OR1123","C102",DeliveryType.STANDARD, TaskType.PARCEL_COLLECTION, 20, RequestReply.DENIED)

val notification2 = createTaskRequest("T2","OR1122","C102",DeliveryType.STANDARD, TaskType.PARCEL_COLLECTION, 21,RequestReply.PENDING)

val notification3 = createTaskRequest("T3","OR1124","C102",DeliveryType.URGENT,TaskType.DELIVERY_FIRST, 4,RequestReply.PENDING)
val notification4 = createTaskRequest("T4","OR1125","C102",DeliveryType.URGENT,TaskType.PARCEL_COLLECTION, 3, RequestReply.PENDING)
val notification5 = createTaskRequest("T5","OR1111","C102",DeliveryType.URGENT,TaskType.DELIVERY_FIRST, 4, RequestReply.ACCEPTED)

val taskRequestList =listOf( // notification1, notification2,notification3, notification4, notification5)
    createTaskRequest("T1","OR1123","C102",DeliveryType.STANDARD, TaskType.PARCEL_COLLECTION, 20, RequestReply.DENIED),
    createTaskRequest("T2","OR1122","C102",DeliveryType.STANDARD, TaskType.PARCEL_COLLECTION, 21,RequestReply.PENDING),
    createTaskRequest("T3","OR1124","C102",DeliveryType.URGENT,TaskType.DELIVERY_FIRST, 4,RequestReply.PENDING),
    createTaskRequest("T4","OR1125","C102",DeliveryType.URGENT,TaskType.PARCEL_COLLECTION, 3, RequestReply.PENDING),
    createTaskRequest("T5","OR1111","C102",DeliveryType.URGENT,TaskType.DELIVERY_FIRST, 4, RequestReply.ACCEPTED)
)

fun taskRequestData(): MutableList<TaskRequest>{
    return mutableListOf(
        createTaskRequest("T1","OR1123","C102",DeliveryType.STANDARD, TaskType.PARCEL_COLLECTION, 20, RequestReply.DENIED),
        createTaskRequest("T2","OR1122","C102",DeliveryType.STANDARD, TaskType.PARCEL_COLLECTION, 21,RequestReply.PENDING),
        createTaskRequest("T3","OR1124","C102",DeliveryType.URGENT,TaskType.DELIVERY_FIRST, 4,RequestReply.PENDING),
        createTaskRequest("T4","OR1125","C102",DeliveryType.URGENT,TaskType.PARCEL_COLLECTION, 3, RequestReply.PENDING),
        createTaskRequest("T5","OR1111","C102",DeliveryType.URGENT,TaskType.DELIVERY_FIRST, 4, RequestReply.ACCEPTED)
    )
}

val taskRequestListFromBroker: MutableList<TaskRequest> = mutableListOf()
fun taskRequestDataFromBroker(): MutableList<TaskRequest>{
    return taskRequestListFromBroker
}

fun addTaskToListFromBroker(task:TaskRequest){
    taskRequestListFromBroker.add(task)
}

fun assignedTaskRequestData(): List<TaskRequest> {
    return taskRequestList.filter { t ->
        t.confirmed.equals(RequestReply.ACCEPTED)
    }
}