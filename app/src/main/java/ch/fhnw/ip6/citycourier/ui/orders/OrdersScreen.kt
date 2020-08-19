package ch.fhnw.ip6.citycourier.ui.orders


import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.Text

import androidx.ui.foundation.VerticalScroller
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Divider

import androidx.ui.unit.dp
import ch.fhnw.ip6.citycourier.data.dataService
import ch.fhnw.ip6.citycourier.model.TaskRequest

import ch.fhnw.ip6.citycourier.ui.themes.themeTypography


@Composable
fun OrdersScreen(ordersRepository: Any?) {
    OrdersAppBar()
    OrdersBody()
}

@Composable
fun OrdersAppBar() {
    // TODO: Transform to tabs
    Row(modifier = Modifier.padding(5.dp) ) {
        Text("Your Current Orders" , style= themeTypography.h3 )
        // TODO: Other items
    }
}

@Composable
fun OrdersBody(){
        Column(modifier = Modifier.padding(25.dp)) {
            // TODO: scrolling container
            //OrderCardTop()
            Spacer(modifier = Modifier.padding(5.dp))
            CurrentOrdersCard()
        }

}


@Composable
fun CurrentOrdersCard() {
    Text(text = "Current Orders", style = themeTypography.h3)
    Column(modifier = Modifier.padding(start=12.dp, top=0.dp, bottom=4.dp, end=12.dp)) {

        Divider(color = Color.DarkGray)
    }
    OrderList(orders = dataService() )
}

@Composable
fun OrderList(orders: List<TaskRequest>) {
    VerticalScroller(isScrollable = true) {
        Column(modifier = Modifier.padding(2.dp)){
            // each notification in the list
            for (each in orders) {
                Box(padding=4.dp) {
                    OrderCard(each)
                }
            }
        }

    }
}

