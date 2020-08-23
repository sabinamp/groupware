package ch.fhnw.ip6.citycourier.ui.welcome


import android.os.Handler
import android.os.Looper
import androidx.compose.*
import androidx.core.os.postDelayed
import androidx.ui.core.*
import androidx.ui.foundation.*

import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.graphics.Color

import androidx.ui.layout.*
import androidx.ui.layout.RowScope.weight
import androidx.ui.material.*

import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import ch.fhnw.ip6.citycourier.R
import ch.fhnw.ip6.citycourier.data.BlockingFakeTaskRequestsRepository
import ch.fhnw.ip6.citycourier.data.FakeTaskRequestsRepository
import ch.fhnw.ip6.citycourier.data.TaskRequestsRepository
import ch.fhnw.ip6.citycourier.data.taskRequestData
import ch.fhnw.ip6.citycourier.model.TaskRequest
import ch.fhnw.ip6.citycourier.mqttservice.MqttClientHelper
import ch.fhnw.ip6.citycourier.state.*
import ch.fhnw.ip6.citycourier.ui.AppDrawer
import ch.fhnw.ip6.citycourier.ui.Screen
import ch.fhnw.ip6.citycourier.ui.SwipeToRefreshLayout
import ch.fhnw.ip6.citycourier.ui.ThemedPreview
import ch.fhnw.ip6.citycourier.ui.orders.OrderList
import ch.fhnw.ip6.citycourier.ui.themes.CityCourierTheme
import ch.fhnw.ip6.citycourier.ui.themes.LightThemeColors
import ch.fhnw.ip6.citycourier.ui.themes.snackbarAction
import ch.fhnw.ip6.citycourier.ui.themes.themeTypography


@Composable
fun WelcomeScreen(
    taskRequestRepository: TaskRequestsRepository,
    scaffoldState: ScaffoldState = remember { ScaffoldState() }
) {
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            AppDrawer(
                currentScreen = Screen.WelcomeScreen,
                closeDrawer = { scaffoldState.drawerState = DrawerState.Closed }
            )
        },
        topAppBar = {
            TopAppBar(
                title = { Text(text = "City Courier") },
                navigationIcon = {
                    IconButton(onClick = { scaffoldState.drawerState = DrawerState.Opened }) {
                        Icon(vectorResource(R.drawable.ic_menu_icon_24))
                    }
                }
            )
        },
        bodyContent = { modifier ->
            WelcomeScreenContent(taskRequestRepository, modifier)
        }
    )
}


@Composable
 fun WelcomeScreenBody(tasks: List<TaskRequest>, modifier: Modifier = Modifier) {
   CityCourierTheme{
       Column(modifier = modifier) {
           HomeScreenIntro()
           OrderList(orders = tasks)
       }

    }
}

@Composable
fun WelcomeScreenContent(taskRequestRepository: TaskRequestsRepository, modifier: Modifier) {

        val (postsState, refreshTasks) = refreshableUiStateFrom(taskRequestRepository::getTaskRequests)

        if (postsState.loading && !postsState.refreshing) {
            LoadingHomeScreen()
        } else {
            SwipeToRefreshLayout(
                refreshingState = postsState.refreshing,
                onRefresh = { refreshTasks() },
                refreshIndicator = {
                    Surface(elevation = 10.dp, shape = CircleShape) {
                        CircularProgressIndicator(Modifier.preferredSize(50.dp).padding(4.dp))
                    }
                }
            ) {
                WelcomeScreenBodyWrapper(
                    modifier = modifier,
                    state = postsState,
                    onErrorAction = {
                        refreshTasks()
                    }
                )
            }

        }
}

@Composable
private fun LoadingHomeScreen() {
    Box(modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)) {
        CircularProgressIndicator()
    }
}

@Composable
fun HomeScreenIntro() {
    Card(color = MaterialTheme.colors.background) {
        Column(Modifier.weight(1f)){
            Row(horizontalArrangement = Arrangement.Center) {
                Text(text = "New notifications.", style = themeTypography.h3, modifier = Modifier.padding(start = 10.dp, top = 5.dp, end = 10.dp))
            }

            Divider(color = Color.LightGray, modifier = Modifier.height(2.dp))

            Row (horizontalArrangement = Arrangement.Center){
                val text = "Please accept or reject each task request within 15 minutes. After 15 minutes, we will assign the task to another courier"
                Text(modifier = Modifier.padding(start = 10.dp, top = 10.dp,bottom = 5.dp, end = 10.dp),
                    style = themeTypography.body2,
                    text = text
                )
            }

        }
    }

}

@Composable
fun ErrorSnackbar(
    showError: Boolean,
    modifier: Modifier = Modifier,
    onErrorAction: () -> Unit = { },
    onDismiss: () -> Unit = { }
) {
    if (showError) {
        // Make Snackbar disappear after 5 seconds if the user hasn't interacted with it
        onActive {
            // With coroutines, this will be cancellable
            Handler(Looper.getMainLooper()).postDelayed(5000L) {
                onDismiss()
            }
        }

        Snackbar(
            modifier = modifier.padding(16.dp),
            text = { Text("Can't update latest notifications") },
            action = {
                TextButton(
                    onClick = {
                        onErrorAction()
                        onDismiss()
                    },
                    contentColor = contentColor()
                ) {
                    Text(
                        text = "RETRY",
                        color = MaterialTheme.colors.snackbarAction
                    )
                }
            }
        )
    }
}
@Composable
private fun WelcomeScreenBodyWrapper(
    modifier: Modifier = Modifier,
    state: RefreshableUiState<List<TaskRequest>>,
    onErrorAction: () -> Unit
) {
    // State for showing the Snackbar error. This state will reset with the content of the lambda
    // inside stateFor each time the RefreshableUiState input parameter changes.
    // showSnackbarError is the value of the error state, use updateShowSnackbarError to update it.
    val (showSnackbarError, updateShowSnackbarError) = stateFor(state) {
        state is RefreshableUiState.Error
    }

    Stack(modifier = modifier.fillMaxSize()) {
        state.currentData?.let { posts ->
            WelcomeScreenBody(tasks = posts)
        }
        ErrorSnackbar(
            showError = showSnackbarError,
            onErrorAction = onErrorAction,
            onDismiss = { updateShowSnackbarError(false) },
            modifier = Modifier.gravity(Alignment.BottomCenter)
        )
    }
}

@Preview("HomeScreenIntro Preview")
@Composable
fun HomeScreenIntroPreview() {
    ThemedPreview {
        HomeScreenIntro()
    }
}

@Preview("Home screen body")
@Composable
fun WelcomeScreenBodyPreview(){

    ThemedPreview() {
        val taskRequests = loadFakeTasks()
        WelcomeScreenBody(tasks = taskRequests)
    }
}

@Preview("Home screen, open drawer dark theme")
@Composable
private fun PreviewDrawerOpenDark() {
    ThemedPreview(darkTheme = true) {
        WelcomeScreen(
            taskRequestRepository = BlockingFakeTaskRequestsRepository(ContextAmbient.current),
            scaffoldState = ScaffoldState(drawerState = DrawerState.Opened)
        )
    }
}

@Composable
private fun loadFakeTasks(): List<TaskRequest> {
    return previewDataFrom(BlockingFakeTaskRequestsRepository(ContextAmbient.current)::getTaskRequests)

}

@Preview("Home screen,  closed drawer")
@Composable
private fun PreviewWelcomeScreenClosed() {
    ThemedPreview(darkTheme = false) {
        WelcomeScreen(
            taskRequestRepository = BlockingFakeTaskRequestsRepository(ContextAmbient.current),
            scaffoldState = ScaffoldState(drawerState = DrawerState.Closed)
        )
    }
}
