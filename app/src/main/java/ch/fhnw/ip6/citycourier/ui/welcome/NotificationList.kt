package ch.fhnw.ip6.citycourier.ui.welcome


import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box

import androidx.ui.foundation.VerticalScroller

import androidx.ui.layout.Column
import androidx.ui.layout.padding
import androidx.ui.unit.dp

import ch.fhnw.ip6.citycourier.model.TaskRequest


@Composable
fun NotificationList(notifications: List<TaskRequest>) {
    VerticalScroller(isScrollable = true) {
        Column(modifier = Modifier.padding(2.dp)){
            // each notification in the list
            for (each in notifications) {
                Box(padding=2.dp) {
                    NotificationCard(each)
                }
            }
        }

    }
}
