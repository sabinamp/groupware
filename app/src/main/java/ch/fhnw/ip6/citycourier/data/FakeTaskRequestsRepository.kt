package ch.fhnw.ip6.citycourier.data

import ch.fhnw.ip6.citycourier.model.*
import java.time.LocalDateTime
import java.util.concurrent.ExecutorService
import android.content.res.Resources
import android.os.Handler
/*
* Implementation that returns a hardcoded list of Task Requests
 */
class FakeTaskRequestsRepository( private val executorService: ExecutorService,
                                  private val resultThreadHandler: Handler,
                                  private val resources: Resources): TaskRequestsRepository {

    private val taskRequests by lazy{
        val notification1 = TaskRequest()
        notification1.orderId ="OR1123"
        notification1.assigneeId = "C102"
        notification1.addressLine = "Rosenstrasse 14"
        notification1.deliveryType = DeliveryType.STANDARD
        notification1.taskType = TaskType.PARCEL_COLLECTION
        notification1.shift = ShiftType.AM
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification1.dueOn = LocalDateTime.now().plusDays(1)
            notification1.sentWhen = LocalDateTime.now()
        }


        val notification2 = TaskRequest()
        notification2.orderId="OR1122"
        notification2.assigneeId="C102"
        notification1.addressLine="Rosenstrasse 12"
        notification2.deliveryType= DeliveryType.STANDARD
        notification2.taskType =  TaskType.PARCEL_COLLECTION
        notification1.shift = ShiftType.AM
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification1.dueOn = LocalDateTime.now().plusDays(1)
            notification1.sentWhen = LocalDateTime.now()
        }
        val notification3 = TaskRequest()
        notification2.orderId="OR1124"
        notification2.assigneeId="C102"
        notification1.addressLine="Tulpenstr 12"
        notification2.deliveryType= DeliveryType.URGENT
        notification2.taskType=  TaskType.DELIVERY_FIRST
        notification1.shift = ShiftType.PM
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification1.dueOn = LocalDateTime.now().plusDays(2)
            notification1.sentWhen = LocalDateTime.now()
        }
        val notification4 = TaskRequest()
        notification2.orderId = "OR1125"
        notification2.assigneeId="C102"
        notification1.addressLine="Tulpenstr 18"
        notification2.deliveryType= DeliveryType.URGENT
        notification2.taskType=  TaskType.PARCEL_COLLECTION
        notification1.shift= ShiftType.AM
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification1.dueOn= LocalDateTime.now().plusHours(1)
            notification1.sentWhen= LocalDateTime.now()
        }
        val notification5 = Notification("Delivery Request for Order OR1127.", "new order", DeliveryType.URGENT)
        listOf( notification1, notification2,notification3, notification4)
    }

    override fun getTaskRequests(callback: (Result<List<TaskRequest>>) -> Unit) {
        callback( Result.success(taskRequests))
    }
}
