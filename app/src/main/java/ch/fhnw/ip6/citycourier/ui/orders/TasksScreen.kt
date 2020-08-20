package ch.fhnw.ip6.citycourier.ui.orders


import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.*

import androidx.ui.layout.*
import androidx.ui.material.Divider

import androidx.ui.material.IconButton
import androidx.ui.material.TopAppBar
import androidx.ui.material.icons.Icons
import androidx.ui.material.icons.filled.ArrowBack
import androidx.ui.tooling.preview.Preview

import androidx.ui.unit.dp

import ch.fhnw.ip6.citycourier.data.OrdersRepository
import ch.fhnw.ip6.citycourier.data.TaskRequestsRepository
import ch.fhnw.ip6.citycourier.data.dataService
import ch.fhnw.ip6.citycourier.model.TaskRequest
import ch.fhnw.ip6.citycourier.ui.Screen
import ch.fhnw.ip6.citycourier.ui.ThemedPreview
import ch.fhnw.ip6.citycourier.ui.navigateTo
import ch.fhnw.ip6.citycourier.ui.themes.LightThemeColors

import ch.fhnw.ip6.citycourier.ui.themes.themeTypography


@Composable
fun TasksScreen(ordersRepository: OrdersRepository, taskRequestsRepository: TaskRequestsRepository) {
    OrdersAppBar()
    OrderList(dataService())

}



@Composable
fun OrdersAppBar(){
        TopAppBar(
            title = {
                Text(
                    text = "City Courier. Your Current Orders",
                    style = themeTypography.h3,
                    color = contentColor()
                )
            },
            navigationIcon = {
                IconButton(onClick = { navigateTo(Screen.WelcomeScreen) }) {
                    Icon(Icons.Filled.ArrowBack)
                }
            }
        )
    
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

@Preview("Orders ApBar")
@Composable
fun OrdersAppBarPreview(){
    ThemedPreview {
        OrdersAppBar()
    }
}

@Preview("Order List")
@Composable
fun OrderListPreview(){
    ThemedPreview() {
        val tasks = dataService()
        OrderList(orders =tasks)
    }
}