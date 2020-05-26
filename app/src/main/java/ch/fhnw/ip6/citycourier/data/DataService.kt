package ch.fhnw.ip6.citycourier.data

import ch.fhnw.ip6.citycourier.model.Notification

fun dataService(): List<Notification>{
    val notification1 = Notification("Request to collect the parcel for Order OR1123"," Standard Delivery.")
    val notification2 = Notification("Request to deliver Order OR1124.","Standard Delivery")
    val notification3 = Notification("Request to deliver Order OR1125.","Standard Delivery")
    val notification4 = Notification("Request to deliver Order OR1126.","Standard Delivery")
    val notification5 = Notification("Request to deliver Order OR1127.","Urgent Delivery")
    val notifyList: List<Notification> = listOf( notification1, notification2, notification3, notification4, notification5)
    return  notifyList
}