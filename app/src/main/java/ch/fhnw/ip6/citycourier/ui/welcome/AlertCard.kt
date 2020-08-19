package ch.fhnw.ip6.citycourier.ui.welcome

import androidx.compose.Composable
import androidx.ui.foundation.Box
import androidx.ui.foundation.Text

import androidx.ui.layout.*
import androidx.ui.material.Card
import androidx.ui.material.Divider

import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import ch.fhnw.ip6.citycourier.data.dataService
import ch.fhnw.ip6.citycourier.ui.themes.LightThemeColors
import ch.fhnw.ip6.citycourier.ui.themes.themeTypography


@Composable
fun AlertCard() {
    Card(color = LightThemeColors.onBackground) {
        Box(padding = 12.dp) {
            Column {
                Row(horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "Delivery Requests", style = themeTypography.h3)


                }
                Box(padding =10.dp) {
                    Divider(color = LightThemeColors.background, modifier = androidx.ui.core.Modifier.height(2.dp))
                }
                Row (horizontalArrangement = Arrangement.Center){

                    val text = "New notifications. Please accept or deny each task request as soon as possible."
                        Text(
                            style = themeTypography.body1,
                            text = text
                        )


                }
                Box(padding =  10.dp) {
                }
                //notification list
                NotificationList(notifications = dataService())
            }
        }
    }
}

@Preview
@Composable
fun AlertCardPreview() {
    AlertCard()
}