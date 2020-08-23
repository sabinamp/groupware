package ch.fhnw.ip6.citycourier.ui.orders

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.setValue
import androidx.compose.state
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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

import ch.fhnw.ip6.citycourier.data.BlockingFakeTaskRequestsRepository
import ch.fhnw.ip6.citycourier.data.TaskRequestsRepository
import ch.fhnw.ip6.citycourier.data.successOr
import ch.fhnw.ip6.citycourier.model.*
import ch.fhnw.ip6.citycourier.state.UiState
import ch.fhnw.ip6.citycourier.ui.Screen
import ch.fhnw.ip6.citycourier.ui.ThemedPreview
import ch.fhnw.ip6.citycourier.ui.effects.fetchTask
import ch.fhnw.ip6.citycourier.ui.navigateTo
import ch.fhnw.ip6.citycourier.ui.themes.LightThemeColors
import ch.fhnw.ip6.citycourier.ui.themes.themeTypography
import ch.fhnw.ip6.citycourier.ui.util.*

@Composable
fun TaskDetailsScreen(taskId: String, taskRequestsRepository: TaskRequestsRepository){
    val tasksState = fetchTask(taskId, taskRequestsRepository)
    if (tasksState is UiState.Success<TaskRequest>) {
        TaskDetailsScr(tasksState.data)
    }
}

@Composable
private fun TaskDetailsScr(task:TaskRequest){
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
                TaskDetailsContent(task, modifier)
            },
            bottomAppBar = {
                BottomBar(task) { showDialog = true }
            }
        )
}

@Composable
private fun BottomBar(task: TaskRequest, onUnimplementedAction: () -> Unit) {
    Surface(elevation = 2.dp, color = MaterialTheme.colors.primary) {
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
                        modifier = Modifier.preferredSize(60.dp).plus(Modifier.padding(10.dp)))
                }


                IconButton(onClick = onUnimplementedAction) {
                    Icon(/*Icons.Filled.ReplyAll,*/
                        imageResource(R.drawable.ok),
                        modifier = Modifier.preferredSize(60.dp).plus(Modifier.padding(10.dp)))
                }

                IconButton(onClick = onUnimplementedAction) {
                    Icon(
                        imageResource(R.drawable.no),
                        modifier = Modifier.preferredSize(60.dp).plus(Modifier.padding(10.dp))
                    )
                }
            }
    }
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
private fun TaskDetailsContent(task: TaskRequest, modifier: Modifier) {
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
                lineHeight = 25.sp
            )
            Text(
                text = "Task to be completed till : ${
                    formatDateAndTime(task.dueOn)} Order info - to do",
                style = themeTypography.body1,
                lineHeight = 25.sp
            )
        }

        Spacer(Modifier.preferredHeight(48.dp))
        Text(
            text = " Please accept the task within 15 minutes",
            style = themeTypography.body1,
            lineHeight = 25.sp
        )
        Spacer(Modifier.preferredHeight(24.dp))
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


@Preview("TopSection")
@Composable
fun TopSectionPreview(){
    ThemedPreview {
        TopSection()
    }
}

