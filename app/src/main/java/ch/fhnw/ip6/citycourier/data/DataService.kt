package ch.fhnw.ip6.citycourier.data


import ch.fhnw.ip6.citycourier.model.*

import java.time.LocalDateTime


fun dataService(): List<TaskRequest>{
    val notification1 = TaskRequest()
    notification1.taskId="T1"
    notification1.orderId ="OR1123"
    notification1.assigneeId = "C102"

        notification1.deliveryType = DeliveryType.STANDARD
        notification1.taskType = TaskType.PARCEL_COLLECTION

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
        notification1.dueOn = LocalDateTime.now().plusDays(1)
        notification1.sentWhen = LocalDateTime.now()
    }


    val notification2 = TaskRequest()
    notification2.taskId="T2"
    notification2.orderId="OR1122"
    notification2.assigneeId="C102"

    notification2.deliveryType=DeliveryType.STANDARD
    notification2.taskType =  TaskType.PARCEL_COLLECTION

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
        notification2.dueOn = LocalDateTime.now().plusDays(1)
        notification2.sentWhen = LocalDateTime.now()
    }
    val notification3 = TaskRequest()
    notification3.taskId="T3"
    notification3.orderId="OR1124"
    notification3.assigneeId="C102"

    notification3.deliveryType=DeliveryType.URGENT
    notification3.taskType=  TaskType.DELIVERY_FIRST

   if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
        notification3.dueOn = LocalDateTime.now().plusDays(2)
        notification3.sentWhen = LocalDateTime.now()
    }

    val notification4 = TaskRequest()
    notification4.taskId="T4"
    notification4.orderId = "OR1125"
    notification4.assigneeId="C102"

    notification4.deliveryType=DeliveryType.URGENT
    notification4.taskType=  TaskType.PARCEL_COLLECTION

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
        notification4.dueOn= LocalDateTime.now().plusHours(1)
        notification4.sentWhen= LocalDateTime.now()
    }
    //val notification3 = Notification("Delivery Request for Order OR1125.", "second delivery attempt",DeliveryType.STANDARD)
    //val notification4 = Notification("Delivery Request for Order OR1126.","first delivery attempt to final destination",        DeliveryType.STANDARD)

    return listOf( notification1, notification2,notification3, notification4  )
}

