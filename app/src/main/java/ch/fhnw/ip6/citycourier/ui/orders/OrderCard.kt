package ch.fhnw.ip6.citycourier.ui.orders



import androidx.compose.Composable

import androidx.ui.foundation.Text
import androidx.ui.core.*
import androidx.ui.foundation.Image

import androidx.ui.foundation.clickable

import androidx.ui.layout.*
import androidx.ui.material.*

import androidx.ui.res.imageResource
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp

import ch.fhnw.ip6.citycourier.R
import ch.fhnw.ip6.citycourier.model.*
import ch.fhnw.ip6.citycourier.ui.Screen
import ch.fhnw.ip6.citycourier.ui.btn.TransparentButton
import ch.fhnw.ip6.citycourier.ui.navigateTo

import java.time.LocalDateTime

@Composable
fun OrderCard(taskRequest: TaskRequest) {
    Row(modifier = Modifier
        .clickable(onClick = { navigateTo(Screen.TaskDetails(taskRequest.taskId)) })
        .padding(16.dp)
    ) {
        NotificationImage(taskRequest, Modifier.padding(end = 16.dp))
        Column(modifier = Modifier.weight(1f)) {
            NotificationTitle(taskRequest)
            NotificationDetails(taskRequest)
        }
        TransparentButton("Pending")
    }
}
@Composable
fun NotificationDetails(
    post: TaskRequest,
    modifier: Modifier = Modifier
) {
    Row(modifier) {
        ProvideEmphasis(EmphasisAmbient.current.medium) {
            val textStyle = MaterialTheme.typography.body2
            Text(
                text = "Task -${post.taskType} ",
                style = textStyle
            )
            Text(
                text = "Deliver till - ${post.dueOn} ",
                style = textStyle
            )
        }
    }
}
@Composable
fun NotificationTitle(post: TaskRequest) {
    ProvideEmphasis(EmphasisAmbient.current.high) {
        Text(post.taskId+ "OrderID: "+post.orderId, style = MaterialTheme.typography.subtitle1)
    }
}

@Composable
fun NotificationImage(taskRequest: TaskRequest, modifier: Modifier = Modifier) {
    val urgent= (DeliveryType.STANDARD == taskRequest.deliveryType)
    var image =imageResource(R.drawable.bell)

    Image(
        asset = image,
        modifier = modifier
            .preferredSize(40.dp, 40.dp)
            .clip(MaterialTheme.shapes.small)
    )
}



@Preview
@Composable
fun OrderCardPreview() {
    val notification1 = TaskRequest()
    notification1.orderId("OR1123")
    notification1.assigneeId( "C102")

    notification1.deliveryType(DeliveryType.STANDARD)
    notification1.taskType(TaskType.PARCEL_COLLECTION)

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
        notification1.dueOn(LocalDateTime.now().plusHours(7))
        notification1.sentWhen(LocalDateTime.now())
    }


    OrderCard(notification1)

}




