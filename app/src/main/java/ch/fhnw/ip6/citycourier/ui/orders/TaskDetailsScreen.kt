package ch.fhnw.ip6.citycourier.ui.orders

import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.setValue
import androidx.compose.state
import androidx.ui.core.Alignment
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text

import androidx.ui.foundation.contentColor
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.AlarmAdd
import androidx.ui.material.icons.filled.AlarmOff
import androidx.ui.material.icons.filled.ArrowBack
import androidx.ui.material.icons.filled.FavoriteBorder

import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import ch.fhnw.ip6.citycourier.R
import ch.fhnw.ip6.citycourier.data.TaskRequestsRepository
import ch.fhnw.ip6.citycourier.model.DeliveryType
import ch.fhnw.ip6.citycourier.model.RequestReply
import ch.fhnw.ip6.citycourier.model.TaskRequest
import ch.fhnw.ip6.citycourier.model.TaskType
import ch.fhnw.ip6.citycourier.state.UiState
import ch.fhnw.ip6.citycourier.ui.Screen
import ch.fhnw.ip6.citycourier.ui.ThemedPreview
import ch.fhnw.ip6.citycourier.ui.btn.EditButton
import ch.fhnw.ip6.citycourier.ui.btn.NoButton
import ch.fhnw.ip6.citycourier.ui.btn.OKButton
import ch.fhnw.ip6.citycourier.ui.effects.fetchTask
import ch.fhnw.ip6.citycourier.ui.navigateTo
import ch.fhnw.ip6.citycourier.ui.themes.LightThemeColors
import ch.fhnw.ip6.citycourier.ui.themes.themeTypography


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
    Surface(elevation = 2.dp) {
            Row(
                verticalGravity = Alignment.CenterVertically,
                modifier = Modifier
                    .preferredHeight(75.dp)
                    .fillMaxWidth()
            ) {
                IconButton(onClick = onUnimplementedAction,modifier = Modifier.preferredSize(40.dp,40.dp)) {
                    Icon(Icons.Filled.AlarmAdd)
                }
                IconButton(onClick = onUnimplementedAction, modifier = Modifier.preferredSize(40.dp,40.dp)) {
                    Icon(vectorResource(R.drawable.ic_phone_60))
                }
                val context = ContextAmbient.current

                OKButton(onClick = onUnimplementedAction, modifier = Modifier.width(60.dp))

                NoButton(onClick = onUnimplementedAction)
                Spacer(modifier = Modifier.padding(5.dp))
                EditButton()

            }
    }
}

@Composable
private fun TaskDetailsContent(task: TaskRequest, modifier: Modifier) {
    //TODO
}

@Composable
fun FunctionalityNotAvailablePopup(onDismiss: () -> Unit) {
        AlertDialog(
            onCloseRequest = onDismiss,
            text = {
                Text(
                    text = "Functionality not available \uD83D\uDE48",
                    style = MaterialTheme.typography.body2
                )
            },
            confirmButton = {
                TextButton(onClick = onDismiss) {
                    Text(text = "CLOSE")
                }
            }
        )
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
fun ScreenDivider() {
        Divider(
            modifier = Modifier.padding(horizontal = 14.dp),
            color = LightThemeColors.onSurface.copy(alpha = 0.08f)
        )
}




@Preview("TopSection")
@Composable
fun TopSectionPreview(){
    ThemedPreview {
        TopSection()
    }
}

