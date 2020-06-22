package ch.fhnw.ip6.citycourier.ui.welcome


import androidx.compose.Composable
import androidx.ui.core.Text

import androidx.ui.core.dp
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.Column
import androidx.ui.layout.Padding
import androidx.ui.layout.Spacing
import androidx.ui.material.AlertDialog
import androidx.ui.material.Button
import androidx.ui.material.ButtonStyle
import androidx.ui.material.FloatingActionButton
import androidx.ui.tooling.preview.Preview

import ch.fhnw.ip6.citycourier.model.TaskRequest
import ch.fhnw.ip6.citycourier.ui.themes.LightThemeColors

@Composable
fun NotificationList(notifications: List<TaskRequest>) {
    VerticalScroller(isScrollable = true) {
        Column(modifier = Spacing(2.dp)){
            // each notification in the list
            for (each in notifications) {
                Padding(2.dp) {
                    NotificationCard(each)
                }
            }
        }

    }
}
