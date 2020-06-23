package ch.fhnw.ip6.citycourier.ui.orders

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.layout.*
import androidx.ui.material.Divider
import androidx.ui.material.surface.Card
import androidx.ui.tooling.preview.Preview
import ch.fhnw.ip6.citycourier.ui.themes.LightThemeColors
import ch.fhnw.ip6.citycourier.ui.btn.TransparentButton
import ch.fhnw.ip6.citycourier.ui.themes.themeTypography

@Composable
fun OrdersAlertCard() {
    Card(color = LightThemeColors.background) {
        Padding(padding = 12.dp) {
            Column {
                FlexRow(mainAxisAlignment = MainAxisAlignment.SpaceBetween) {
                    expanded(flex = 1.0f){
                        Text(text = "New Alerts", style = themeTypography.subtitle1)
                        TransparentButton(text = "See All", onClick = { })
                    }

                }
                Padding(padding = EdgeInsets(0.dp, 12.dp, 0.dp, 12.dp)) {
                    val colors =
                        LightThemeColors
                    Divider(color = colors.secondary, height = 2.dp)
                }
                FlexRow {
                    expanded(flex = 1.0f) {
                        val text = "Your current delivery orders. Orders are displayed based on the due date."
                        Text(
                            style = themeTypography.body1,
                            text = text
                        )
                    }
                    inflexible {
                        TransparentButton(text = "Sort", onClick = { })
                    }
                }
            }
        }
    }
}
@Preview
@Composable
fun OrdersAlertCardPreview() {
    OrdersAlertCard()
}