package ch.fhnw.ip6.citycourier.ui.welcome

import androidx.compose.Composable
import androidx.ui.core.dp
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.layout.Padding
import androidx.ui.layout.Spacing
import ch.fhnw.ip6.citycourier.model.Notification
import ch.fhnw.ip6.citycourier.ui.NotificationCard

@Composable
fun NotificationList(notifications: List<Notification>) {
    VerticalScroller(isScrollable = true) {
        Column(modifier = Spacing(4.dp)){
            // each notification in the list
            for (each in notifications) {
                Padding(5.dp) {
                    NotificationCard(each)
                }
            }
        }

    }
}