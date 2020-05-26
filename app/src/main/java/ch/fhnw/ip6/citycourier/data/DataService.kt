package ch.fhnw.ip6.citycourier.data

import ch.fhnw.ip6.citycourier.model.DeliveryType
import ch.fhnw.ip6.citycourier.model.Notification

fun dataService(): List<Notification>{
    val notification1 = Notification("Request to collect the parcel for Order OR1123", "parcel collection",DeliveryType.STANDARD)
    val notification2 = Notification("Delivery Request for Order OR1124.", "delivery to final destination",DeliveryType.STANDARD)
    val notification3 = Notification("Delivery Request for Order OR1125.", "second delivery attempt",DeliveryType.STANDARD)
    val notification4 = Notification("Delivery Request for Order OR1126.","first delivery attempt to final destination",
        DeliveryType.STANDARD)
    val notification5 = Notification("Delivery Request for Order OR1127.", "new order", DeliveryType.URGENT)
    val notifyList: List<Notification> = listOf( notification1, notification2,notification5, notification3, notification4 )
    return  notifyList
}