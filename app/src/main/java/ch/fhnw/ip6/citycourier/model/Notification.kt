package ch.fhnw.ip6.citycourier.model


data class Notification(
    val title: String,
    val message: String,
    val type: DeliveryType
)
