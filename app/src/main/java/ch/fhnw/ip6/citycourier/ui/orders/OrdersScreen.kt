package ch.fhnw.ip6.citycourier.ui.orders


import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.*
import androidx.ui.material.Divider
import androidx.ui.tooling.preview.Preview
import ch.fhnw.ip6.citycourier.data.dataService
import ch.fhnw.ip6.citycourier.model.TaskRequest
import ch.fhnw.ip6.citycourier.ui.themes.LightThemeColors
import ch.fhnw.ip6.citycourier.ui.themes.themeTypography


@Composable
fun OrdersScreen(){
    OrdersAppBar()
    OrdersBody()
}

@Composable
fun OrdersAppBar() {
    // TODO: Transform to tabs
    Row(modifier = Spacing(3.dp), arrangement = Arrangement.Center ) {
        Text("Your Current Orders" , style= themeTypography.h3 )
        // TODO: Other items
    }
}

@Composable
fun OrdersBody(){
    Padding(padding = 26.dp) {
        Column {
            // TODO: scrolling container
            OrdersAlertCard()
            HeightSpacer(height = 10.dp)
            CurrentOrdersCard()
        }
    }
}


@Composable
fun CurrentOrdersCard() {
    Text(text = "Current Orders", style = themeTypography.h3)
    Padding(padding = EdgeInsets(0.dp, 12.dp, 4.dp, 12.dp)) {
        val colors = LightThemeColors
        Divider(color = colors.onBackground, height = 2.dp)
    }
    OrderList(orders = dataService() )
}

@Composable
fun OrderList(orders: List<TaskRequest>) {
    VerticalScroller(isScrollable = true) {
        Column(modifier = Spacing(2.dp)){
            // each notification in the list
            for (each in orders) {
                Padding(4.dp) {
                    OrderCard(each)
                }
            }
        }

    }
}

@Preview
@Composable
fun OrdersPreview() {
    OrdersScreen()
}