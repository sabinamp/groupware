package ch.fhnw.ip6.citycourier.ui.orders

import android.content.Context
import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.setValue
import androidx.compose.state
import androidx.ui.core.Alignment
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.*

import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.*
import androidx.ui.res.imageResource

import androidx.ui.res.vectorResource

import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import ch.fhnw.ip6.citycourier.R
import ch.fhnw.ip6.citycourier.data.*

import ch.fhnw.ip6.citycourier.model.*
import ch.fhnw.ip6.citycourier.state.UiState
import ch.fhnw.ip6.citycourier.ui.Screen
import ch.fhnw.ip6.citycourier.ui.ThemedPreview
import ch.fhnw.ip6.citycourier.ui.effects.fetchOrder
import ch.fhnw.ip6.citycourier.ui.effects.fetchTask
import ch.fhnw.ip6.citycourier.ui.navigateTo
import ch.fhnw.ip6.citycourier.ui.themes.background2
import ch.fhnw.ip6.citycourier.ui.themes.themeTypography
import ch.fhnw.ip6.citycourier.ui.util.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext

@Composable
fun TaskDetailsScreen(
    taskId: String,
    taskRequestsRepository: TaskRequestsRepository,
    orderRepository: OrdersRepository
){
    val tasksState = fetchTask(taskId, taskRequestsRepository)

    if (tasksState is UiState.Success<TaskRequest>) {
        val currentTaskRequest= tasksState.data
        TaskDetailsScr(currentTaskRequest, taskRequestsRepository, orderRepository)
        GlobalScope.launch(){
            orderRepository.handleGetOrderUIEvent(currentTaskRequest.orderId)
        }
    }
}

@Composable
private fun TaskDetailsScr(task:TaskRequest,taskRequestsRepository: TaskRequestsRepository,  orderRepository: OrdersRepository){
        var showDialog by state { false }
        if (showDialog) {
            FunctionalityNotAvailablePopup { showDialog = false }
        }

        Scaffold(
            topAppBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Task ID: ${task.taskId} - ${task.confirmed.toString()}",
                            style = themeTypography.subtitle2,
                            color = contentColor()
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navigateTo(Screen.WelcomeScreen) }) {
                            Icon(Icons.Filled.ArrowBack)
                        }
                    }
                )
            },
            bodyContent = { modifier ->
                TaskDetailsContent(task, modifier, orderRepository)
            },
            bottomAppBar = {
                BottomBar(task, taskRequestsRepository) { showDialog = true }
            }
        )
}

@Composable
private fun BottomBar(
    task: TaskRequest,
    taskRequestsRepository: TaskRequestsRepository,
    function: () -> Unit
) {
    Surface(elevation = 3.dp, color = MaterialTheme.colors.primary) {
            Row(
                verticalGravity = Alignment.CenterVertically,
                modifier = Modifier
                    .preferredHeight(75.dp)
                    .fillMaxWidth()
            ) {
                val context= ContextAmbient.current
                IconButton(onClick = { makePhoneCallToDispatcher(context = context)}) {
                    Icon(
                        //Icons.Filled.ContactPhone,
                        vectorResource(R.drawable.ic_phone_60_black),
                        modifier = Modifier.preferredSize(90.dp).plus(Modifier.padding(10.dp)))
                }


                IconButton(onClick = {
                    sendTaskRequestReply(
                        task = task,
                        taskRequestsRepository = taskRequestsRepository,
                        accept = RequestReply.ACCEPTED
                    )
                }) {
                    Icon(
                        imageResource(R.drawable.ok),
                        modifier = Modifier.preferredSize(90.dp).plus(Modifier.padding(10.dp)))
                }

                IconButton(onClick = { sendTaskRequestReply(task=task,
                                   taskRequestsRepository = taskRequestsRepository,
                                    accept = RequestReply.DENIED)}) {
                    Icon(
                        imageResource(R.drawable.no),
                        modifier = Modifier.preferredSize(90.dp).plus(Modifier.padding(10.dp))
                    )
                }

            }
    }
}



fun sendTaskRequestReply(task:TaskRequest,taskRequestsRepository: TaskRequestsRepository, accept:RequestReply) {

     taskRequestsRepository.handleAcceptRequestEvent(taskRequest = task, accepted = accept)

   // refreshTask(taskId = task.taskId,taskRequestsRepository=taskRequestsRepository)


}




fun makePhoneCallToDispatcher(context:Context) {
    makePhoneCall(context)
}

@Composable
fun TaskHeaderImage(taskRequest: TaskRequest, modifier: Modifier = Modifier) {
    val urgent= (DeliveryType.STANDARD == taskRequest.deliveryType)
    val image = if(urgent) {
        vectorResource(R.drawable.ic_bell_60)
    }else{
        vectorResource(id = R.drawable.ic_bell_urgent_60)
    }
    Image(
        asset = image,
        modifier = modifier
            .preferredSize(80.dp, 80.dp)
            .padding(4.dp)

    )
}


@Composable
private fun TaskDetailsContent(task: TaskRequest, modifier: Modifier,  orderRepository: OrdersRepository) {

    VerticalScroller(
        modifier = modifier.padding(horizontal = 10.dp)
    ) {
        Spacer(Modifier.preferredHeight(5.dp))
        TaskHeaderImage(task)
        Text(text = "Delivery Task Request ${task.taskId} - ${task.taskType}", style = themeTypography.h3)
        Spacer(Modifier.preferredHeight(8.dp))

        ProvideEmphasis(EmphasisAmbient.current.medium) {
            Text(
                text = "Order : ${task.orderId} Delivery Type: ${task.deliveryType}",
                style = themeTypography.body1,
                lineHeight = 30.sp
            )
            Text(
                text = "Task to be completed till : ${formatDateAndTime(task.dueOn)} ",
                style = themeTypography.body1,
                lineHeight = 30.sp
            )
            val order:OrderDescriptiveInfo
            val orderState= fetchOrder(task.orderId, orderRepository)
            if(orderState is UiState.Success<OrderDescriptiveInfo>){
                order= orderState.data
            Text(
                text = "Task Order Information : ${task.orderId}"
                        +" Customer Name: ${order.customerName}",
                style = themeTypography.body1,
                lineHeight = 30.sp
            )
                Text(
                    text = "Destination Address "+order.finalDestinationContactInfos[0].address.addressLine,
                    style = themeTypography.body1,
                    lineHeight = 30.sp
                )
                Text(
                    text = "City "+order.finalDestinationContactInfos[0].address.cityName
                            +" Postal code "+order.finalDestinationContactInfos[0].address.postalCode,
                    style = themeTypography.body1,
                    lineHeight = 30.sp
                )
            }
        }

        Spacer(Modifier.preferredHeight(48.dp))
        Text(
            text = " Please accept the task within 15 minutes.",
            style = themeTypography.body1,
            lineHeight = 30.sp
        )
        Spacer(Modifier.preferredHeight(28.dp))
    }
}




@Composable
private fun TopSection() {
        ProvideEmphasis(EmphasisAmbient.current.high) {
            Text(
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp),
                text = "This task has been assigned to you",
                style = themeTypography.subtitle1
            )
        }

        ScreenDivider()
}


@Composable
private fun loadFakeTask(taskId: String): TaskRequest {
    var request: TaskRequest? = null
    BlockingFakeTaskRequestsRepository(ContextAmbient.current).getTaskRequest(taskId) { result ->
        request = result.successOr(null)
    }
    return request!!
}

@Composable
private fun loadFakeOrder(orderId: String): OrderDescriptiveInfo {
    var order: OrderDescriptiveInfo? = null
    BlockingOrdersRepository(ContextAmbient.current).getOrder(orderId = orderId){ result ->
        order = result.successOr(null)
    }
    return order!!
}

@Preview("TopSection")
@Composable
fun TopSectionPreview(){
    ThemedPreview {
        TopSection()
    }
}

