package ch.fhnw.ip6.citycourier.ui.welcome

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Clip
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.*
import androidx.ui.material.Divider
import androidx.ui.material.surface.Card
import androidx.ui.res.vectorResource
import ch.fhnw.ip6.citycourier.R
import ch.fhnw.ip6.citycourier.data.dataService
import ch.fhnw.ip6.citycourier.ui.LightThemeColors
import ch.fhnw.ip6.citycourier.ui.btn.TransparentButton
import ch.fhnw.ip6.citycourier.ui.themeTypography

@Composable
fun AlertCard() {
    Card(color = LightThemeColors.onSurface) {
        Padding(padding = 12.dp) {
            Column {
                FlexRow(mainAxisAlignment = MainAxisAlignment.SpaceBetween) {
                    expanded(flex = 1.0f){
                        Text(text = "Delivery Requests", style = themeTypography.h3)
                    }

                }
                Padding(padding = EdgeInsets(0.dp, 12.dp, 4.dp, 12.dp)) {
                    val colors = LightThemeColors
                    Divider(color = colors.onBackground, height = 2.dp)
                }
                FlexRow (mainAxisAlignment = MainAxisAlignment.Center){
                   expanded(flex = 1.0f) {
                        val text = "New notifications. Please accept or deny each task request as soon as possible."
                        Text(
                            style = themeTypography.body1,
                            text = text
                        )
                    }

                }
                Padding(padding = EdgeInsets(0.dp, 12.dp, 10.dp, 12.dp)) {
                }
                //notification list
                NotificationList(notifications = dataService())
            }
        }
    }
}