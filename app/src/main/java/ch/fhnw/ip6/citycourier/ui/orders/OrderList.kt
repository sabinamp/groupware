package ch.fhnw.ip6.citycourier.ui.orders

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.layout.padding
import androidx.ui.material.Divider
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import ch.fhnw.ip6.citycourier.data.taskRequestData
import ch.fhnw.ip6.citycourier.model.TaskRequest
import ch.fhnw.ip6.citycourier.ui.ThemedPreview
import ch.fhnw.ip6.citycourier.ui.themes.LightThemeColors


@Composable
fun OrderList(orders: List<TaskRequest>) {
    VerticalScroller(isScrollable = true) {
        Column(modifier = Modifier.padding(10.dp)) {
            // each notification in the list
            orders.forEach { each ->
                OrderCard(each)
                Divider(
                    modifier = Modifier.padding(vertical = 5.dp),
                    color = LightThemeColors.primaryVariant.copy(alpha = 0.08f)
                )
            }
        }
    }
}

@Preview("Delivery Task List")
@Composable
fun OrderListPreview() {
    ThemedPreview() {
        val notifications: List<TaskRequest> = taskRequestData()
        OrderList(notifications)

    }
}