package ch.fhnw.ip6.citycourier.ui.orders



import androidx.compose.Composable

import androidx.ui.core.*
import androidx.ui.foundation.*

import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.MoreVert

import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp

import ch.fhnw.ip6.citycourier.R
import ch.fhnw.ip6.citycourier.model.*
import ch.fhnw.ip6.citycourier.ui.Screen
import ch.fhnw.ip6.citycourier.ui.ThemedPreview
import ch.fhnw.ip6.citycourier.ui.navigateTo
import ch.fhnw.ip6.citycourier.ui.themes.LightThemeColors
import ch.fhnw.ip6.citycourier.ui.themes.themeTypography
import ch.fhnw.ip6.citycourier.ui.util.formatDateAndTime

import java.time.LocalDateTime


@Composable
private fun NotificationDetails(
    task: TaskRequest,
    modifier: Modifier = Modifier) {
    Row(modifier) {
        ProvideEmphasis(EmphasisAmbient.current.high) {
            val textStyle = themeTypography.body1
            Text(
                text = "${task.taskId} - task ${task.taskType} Please complete till - ${
                    formatDateAndTime(task.dueOn)}. ",
                style = textStyle
            )
        }

    }
}

@Composable
fun NotificationTitle(taskRequest: TaskRequest) {
    ProvideEmphasis(EmphasisAmbient.current.high) {
        val title= "Task "+ taskRequest.taskId + " OrderID: "+taskRequest.orderId
        Text(title, style = themeTypography.subtitle1, color = LightThemeColors.primaryVariant)
    }
}

@Composable
fun NotificationImage(taskRequest: TaskRequest, modifier: Modifier = Modifier) {
    val urgent= (DeliveryType.STANDARD == taskRequest.deliveryType)
    val image = if(urgent) {
        vectorResource(R.drawable.ic_bell_60)
    }else{
        vectorResource(id = R.drawable.ic_bell_urgent_60)
    }
    Image(
        asset = image,
        modifier = modifier
            .preferredSize(60.dp, 60.dp)
            .padding(4.dp)

    )
}

@Composable
fun OrderCard(taskRequest: TaskRequest) {
    Row(
      modifier = Modifier
            .fillMaxWidth()
            .clickable( onClick = { navigateTo(Screen.TaskDetails(taskRequest.taskId)) })
            .padding(20.dp)
    ) {
        NotificationImage(taskRequest = taskRequest, modifier = Modifier.padding(end = 16.dp))
        Column(modifier = Modifier.weight(1f)) {
            NotificationTitle(taskRequest = taskRequest)
            NotificationDetails(task=taskRequest)

        }
        ProvideEmphasis(EmphasisAmbient.current.medium) {
            Icon(asset = Icons.Filled.MoreVert)
        }

    }
}

@Preview("Notification Image")
@Composable
fun NotificationImagePreview(){
    ThemedPreview() {
        val notification = createTaskRequestForPreview()
        NotificationImage(taskRequest = notification)
    }
}

fun createTaskRequestForPreview(): TaskRequest{
    val notification1 = TaskRequest()
    notification1.taskId = "T1"
    notification1.orderId("OR1123")
    notification1.assigneeId( "C102")

    notification1.deliveryType(DeliveryType.STANDARD)
    notification1.taskType(TaskType.PARCEL_COLLECTION)
    notification1.confirmed(RequestReply.PENDING)

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
        notification1.dueOn(LocalDateTime.now().plusHours(7))
        notification1.sentWhen(LocalDateTime.now())
    }
    return notification1
}

@Preview("Notification Title")
@Composable
fun NotificationTitlePreview() {
    ThemedPreview() {
        val notification = createTaskRequestForPreview()
        NotificationTitle(taskRequest = notification)
    }
}

@Preview("Notification Details")
@Composable
fun NotificationDetailsPreview() {
    ThemedPreview() {
        NotificationDetails(task = createTaskRequestForPreview())
    }
}


@Preview("Order Card")
@Composable
fun OrderCardPreview() {
    ThemedPreview() {
        val notification = createTaskRequestForPreview()
        OrderCard(notification)
    }
}





