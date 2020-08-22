package ch.fhnw.ip6.citycourier.ui.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter



fun formatDateAndTime(givenDate: LocalDateTime):String{

    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    return givenDate.format(formatter)
}