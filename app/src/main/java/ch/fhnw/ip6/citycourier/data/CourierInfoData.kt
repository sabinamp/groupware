package ch.fhnw.ip6.citycourier.data

import ch.fhnw.ip6.citycourier.model.*

fun createCourierInfoForPreview(): CourierInfo {
    val info = CourierInfo()
    info.courierName = "Martin Hauser"
    info.conn= Conn.ONLINE
    info.status= CourierStatus.AVAILABLE
    info.assignedOrders= setOf("OR1126")
    val contact: ContactInfo = ContactInfo()
    val emailAddr = Email()
    emailAddr.email = "info@citycourier.com"
    contact.email = emailAddr
    val phone: Phone = Phone()
    phone.phoneTechType=1
    phone.countryAccessCode="41"
    phone.phoneNumber= "442205020"
    val courierPhones: List<Phone> = listOf(phone)
    contact.phones=courierPhones
    info.contactInfo = contact
    return info
}
val courierC106Data: CourierInfo = createCourierInfoForPreview()
