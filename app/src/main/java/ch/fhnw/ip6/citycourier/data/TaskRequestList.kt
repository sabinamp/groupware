package ch.fhnw.ip6.citycourier.data

import ch.fhnw.ip6.citycourier.model.DeliveryType
import ch.fhnw.ip6.citycourier.model.TaskRequest
import ch.fhnw.ip6.citycourier.model.TaskType
import java.time.LocalDateTime
/**
 * Define hardcoded task notifications to avoid handling any non-ui operations.
 */

private fun createTaskRequest(taskId:String, orderId:String, assigneeId:String, deliveryType: DeliveryType,taskType: TaskType, nbh: Long): TaskRequest{
    val notification = TaskRequest()
    notification.taskId = taskId
    notification.orderId = orderId
    notification.assigneeId = assigneeId

    notification.deliveryType = deliveryType
    notification.taskType =  taskType

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
        notification.dueOn= LocalDateTime.now().plusHours(nbh)
        notification.sentWhen= LocalDateTime.now()
    }
    return notification
}

val notification1 = createTaskRequest("T1","OR1123","C102",DeliveryType.STANDARD, TaskType.PARCEL_COLLECTION, 20)

val notification2 = createTaskRequest("T2","OR1122","C102",DeliveryType.STANDARD, TaskType.PARCEL_COLLECTION, 21)

val notification3 = createTaskRequest("T3","OR1124","C102",DeliveryType.URGENT,TaskType.DELIVERY_FIRST, 4)
val notification4 = createTaskRequest("T4","OR1125","C102",DeliveryType.URGENT,TaskType.PARCEL_COLLECTION, 3)
val taskRequestList=listOf( notification1, notification2,notification3, notification4)