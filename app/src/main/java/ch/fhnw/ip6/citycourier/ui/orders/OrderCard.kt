package ch.fhnw.ip6.citycourier.ui.orders



import androidx.compose.Composable

import androidx.ui.foundation.Text
import androidx.ui.core.*
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Image

import androidx.ui.foundation.clickable

import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.MoreVert

import androidx.ui.res.imageResource
import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp

import ch.fhnw.ip6.citycourier.R
import ch.fhnw.ip6.citycourier.model.*
import ch.fhnw.ip6.citycourier.ui.Screen
import ch.fhnw.ip6.citycourier.ui.ThemedPreview
import ch.fhnw.ip6.citycourier.ui.btn.TransparentButton
import ch.fhnw.ip6.citycourier.ui.navigateTo
import ch.fhnw.ip6.citycourier.ui.themes.themeTypography

import java.time.LocalDateTime


@Composable
fun NotificationDetails(
    post: TaskRequest,
    modifier: Modifier = Modifier
) {
    Row(modifier) {
        ProvideEmphasis(EmphasisAmbient.current.medium) {
            val textStyle = themeTypography.body2
            Text(
                text = "${post.taskId} -${post.taskType}. Deliver till - ${post.dueOn} ",
                style = textStyle
            )

        }
    }
}
@Composable
fun NotificationTitle(taskRequest: TaskRequest) {
    ProvideEmphasis(EmphasisAmbient.current.high) {

        val taskStatus=  ""+ if (taskRequest.completedWhen!= null &&taskRequest.completedWhen.isBefore(LocalDateTime.now())) {
            Text("Completed")
        } else {
            Text("Pending")
        }
        val content=   "Task "+ taskRequest.taskId+ " OrderID: "+taskRequest.orderId +taskStatus
        Text(content, style = themeTypography.subtitle1)
    }
}

@Composable
fun NotificationImage(taskRequest: TaskRequest, modifier: Modifier = Modifier) {
    val urgent= (DeliveryType.STANDARD == taskRequest.deliveryType)
    var image = if(urgent) {
        vectorResource(R.drawable.ic_bell_60)
    }else{
        vectorResource(id = R.drawable.ic_bell_urgent_60)
    }

    Image(
        asset = image,
        modifier = modifier
            .preferredSize(40.dp, 40.dp)
            .padding(4.dp)

    )
}
@Composable
fun OrderCard(taskRequest: TaskRequest) {
    Row(modifier = Modifier
        .clickable(onClick = { navigateTo(Screen.TaskDetails(taskRequest.taskId)) })
        .padding(10.dp)
    ) {
        NotificationImage(taskRequest = taskRequest, modifier = Modifier.padding(end = 16.dp))
        Column(modifier = Modifier.weight(1f)) {
            NotificationTitle(taskRequest)
            NotificationDetails(taskRequest)

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
    notification1.taskId = "T10"
    notification1.orderId("OR1123")
    notification1.assigneeId( "C102")

    notification1.deliveryType(DeliveryType.STANDARD)
    notification1.taskType(TaskType.PARCEL_COLLECTION)

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
        val notification = createTaskRequestForPreview()
        NotificationDetails(post = notification)
    }
}


@Preview("Order Card")
@Composable
fun OrderCardPreview() {
    val notification = createTaskRequestForPreview()
    ThemedPreview() {
        OrderCard(notification)
    }
}




