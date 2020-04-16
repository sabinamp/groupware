package ch.fhnw.ip6.citycourier.ui

import androidx.compose.Composable
import androidx.ui.core.dp
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.layout.Padding
import ch.fhnw.ip6.citycourier.model.Notification
import androidx.ui.foundation.VerticalScroller

@Composable
fun NotificationList(notifications: List<Notification>) {
    VerticalScroller(isScrollable = true) {
        Column(
        ){
            // each notification in the list
            for (each in notifications) {
                Padding(10.dp) {
                    NotificationCard(each)
                }
            }
        }

    }
}