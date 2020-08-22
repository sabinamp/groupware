package ch.fhnw.ip6.citycourier.ui.orders

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.clickable
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.layout.RowScope.weight
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.Divider
import androidx.ui.material.EmphasisAmbient
import androidx.ui.material.ProvideEmphasis
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.MoreVert
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import ch.fhnw.ip6.citycourier.data.taskRequestData
import ch.fhnw.ip6.citycourier.model.TaskRequest
import ch.fhnw.ip6.citycourier.ui.Screen
import ch.fhnw.ip6.citycourier.ui.ThemedPreview
import ch.fhnw.ip6.citycourier.ui.navigateTo
import ch.fhnw.ip6.citycourier.ui.themes.LightThemeColors
import ch.fhnw.ip6.citycourier.ui.themes.themeTypography
import ch.fhnw.ip6.citycourier.ui.util.formatDateAndTime

@Composable
private fun AcceptedTaskDetails(
    task: TaskRequest,
    modifier: Modifier = Modifier) {
    Column(modifier) {
        ProvideEmphasis(EmphasisAmbient.current.high) {
            val textStyle = themeTypography.body1
            Text(
                text = "${task.taskId} - task ${task.taskType} Due on - " +
                        formatDateAndTime(task.dueOn)+". ",
                style = textStyle, color = LightThemeColors.primary
            )
        }
        ProvideEmphasis(EmphasisAmbient.current.medium) {
            val textStyle = themeTypography.body1
            Text(
                text = "Reply to the dispatcher: ${task.confirmed} - task for the order ${task.orderId} \nDestination address: todo ",
                style = textStyle
            )
        }
    }
}

@Composable
fun AcceptedTaskCard(taskRequest: TaskRequest) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        NotificationImage(taskRequest = taskRequest, modifier = Modifier.padding(end = 16.dp))
        Column(modifier = Modifier.weight(1f)) {
            NotificationTitle(taskRequest = taskRequest)
            AcceptedTaskDetails(task=taskRequest)

        }
        ProvideEmphasis(EmphasisAmbient.current.medium) {
            Icon(asset = Icons.Filled.MoreVert)
        }

    }
}

@Composable
fun AcceptedTaskList(orders: List<TaskRequest>) {
    VerticalScroller(isScrollable = true) {
        Column(modifier = Modifier.padding(5.dp)) {
            // each notification in the list
            orders.forEach { each ->
                AcceptedTaskCard(taskRequest = each)
                Divider(
                    modifier = Modifier.padding(vertical = 5.dp),
                    color = LightThemeColors.primaryVariant.copy(alpha = 0.08f)
                )
            }
        }
    }
}

@Preview("Accepted Task List")
@Composable
fun AcceptedTaskListPreview() {
    ThemedPreview() {
        val notifications: List<TaskRequest> = taskRequestData()
        AcceptedTaskList(notifications)

    }
}

@Preview("AcceptedTaskDetails")
@Composable
fun AcceptedTaskDetailsPreview() {
    ThemedPreview() {
        AcceptedTaskDetails(task = createTaskRequestForPreview())
    }
}


@Preview("AcceptedTask Card")
@Composable
fun AcceptedTaskCardPreview() {
    ThemedPreview() {
        val notification = createTaskRequestForPreview()
        AcceptedTaskCard(notification)
    }
}
