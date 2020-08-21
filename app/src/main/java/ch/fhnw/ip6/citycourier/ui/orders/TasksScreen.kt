package ch.fhnw.ip6.citycourier.ui.orders


import androidx.compose.Composable
import androidx.compose.remember
import androidx.compose.state
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.*
import androidx.ui.graphics.Color

import androidx.ui.layout.*
import androidx.ui.layout.RowScope.weight
import androidx.ui.material.*

import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.ArrowBack
import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview

import androidx.ui.unit.dp
import ch.fhnw.ip6.citycourier.R
import ch.fhnw.ip6.citycourier.data.*

import ch.fhnw.ip6.citycourier.model.TaskRequest
import ch.fhnw.ip6.citycourier.state.previewDataFrom
import ch.fhnw.ip6.citycourier.ui.AppDrawer
import ch.fhnw.ip6.citycourier.ui.Screen
import ch.fhnw.ip6.citycourier.ui.ThemedPreview

import ch.fhnw.ip6.citycourier.ui.themes.CityCourierTheme
import ch.fhnw.ip6.citycourier.ui.themes.LightThemeColors
import ch.fhnw.ip6.citycourier.ui.themes.themeTypography


@Composable
fun TasksScreen(/*ordersRepository: OrdersRepository,*/
    scaffoldState: ScaffoldState = remember { ScaffoldState() },
    taskRequestsRepository: TaskRequestsRepository){
        Scaffold(
            scaffoldState = scaffoldState,
            drawerContent = {
                AppDrawer(
                    currentScreen = Screen.TasksScreen,
                    closeDrawer = { scaffoldState.drawerState = DrawerState.Closed }
                )
            },
            topAppBar = {
                TopAppBar(
                    title = { Text("City Courier. Your Current Tasks") },
                    navigationIcon = {
                        IconButton(onClick = { scaffoldState.drawerState = DrawerState.Opened }) {
                            Icon(vectorResource(R.drawable.ic_menu_icon_24))
                        }
                    }
                )
            },
            bodyContent = {

                TasksScreenBody(taskRequestsRepository)
            }
        )
}

@Composable
fun TasksScreenBody(taskRequestsRepository: TaskRequestsRepository) {
    CityCourierTheme {
        val tasks = loadFakeTasks()
        Column() {
            ScreenIntro()
            ScreenDivider()
            AcceptedTaskList(orders = tasks)
        }
    }
}

@Composable
private fun loadFakeTasks(): List<TaskRequest> {
    return previewDataFrom(BlockingFakeTaskRequestsRepository(ContextAmbient.current)::getAcceptedTaskRequests)

}


@Composable
fun ScreenIntro() {
    Card(color = LightThemeColors.background) {
        Column(Modifier.weight(1f)){
            Row(horizontalArrangement = Arrangement.Center) {
                Text(text = "Your accepted tasks.", style = themeTypography.h3, modifier = Modifier.padding(start = 10.dp, top = 5.dp, end = 10.dp))
            }

            Divider(color = Color.LightGray, modifier = Modifier.height(2.dp))

            Row (horizontalArrangement = Arrangement.Center){
                val text = "The current tasks assigned to you"
                Text(modifier = Modifier.padding(start = 10.dp, top = 10.dp, bottom = 5.dp,end = 10.dp),
                    style = themeTypography.body2,
                    text = text
                )
            }

        }
    }

}




@Preview("TasksScreenBodyPreview")
@Composable
fun TasksScreenBodyPreview(){
    ThemedPreview {
        TasksScreenBody(taskRequestsRepository = BlockingFakeTaskRequestsRepository(ContextAmbient.current))
    }
}