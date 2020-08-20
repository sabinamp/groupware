package ch.fhnw.ip6.citycourier.ui.orders


import androidx.compose.Composable
import androidx.compose.remember
import androidx.compose.state
import androidx.ui.core.ContextAmbient
import androidx.ui.core.Modifier
import androidx.ui.foundation.*

import androidx.ui.layout.*
import androidx.ui.material.*

import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.ArrowBack
import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview

import androidx.ui.unit.dp
import ch.fhnw.ip6.citycourier.R
import ch.fhnw.ip6.citycourier.data.*

import ch.fhnw.ip6.citycourier.model.RequestReply

import ch.fhnw.ip6.citycourier.model.TaskRequest
import ch.fhnw.ip6.citycourier.state.previewDataFrom
import ch.fhnw.ip6.citycourier.ui.AppDrawer
import ch.fhnw.ip6.citycourier.ui.Screen
import ch.fhnw.ip6.citycourier.ui.ThemedPreview
import ch.fhnw.ip6.citycourier.ui.navigateTo
import ch.fhnw.ip6.citycourier.ui.themes.CityCourierTheme


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
        OrderList(tasks)
    }
}

@Composable
private fun loadFakeTasks(): List<TaskRequest> {
    return previewDataFrom(BlockingFakeTaskRequestsRepository(ContextAmbient.current)::getAcceptedTaskRequests)
    //return assignedTaskRequestData()
    }





@Composable
fun OrderList(orders: List<TaskRequest>) {
    VerticalScroller(isScrollable = true) {
        Column(modifier = Modifier.padding(15.dp)){
            // each notification in the list
            for (each in orders) {
                OrderCard(each)
                Spacer(modifier = Modifier.padding(5.dp))
              /*  Divider(
                    modifier = Modifier.padding(vertical = 5.dp),
                    color = LightThemeColors.onSurface.copy(alpha = 0.08f)
                )*/
            }
        }


    }
}



@Preview("OrderList")
@Composable
fun TasksScreenBodyPreview(){
    ThemedPreview {
        OrderList(orders = loadFakeTasks())
    }
}