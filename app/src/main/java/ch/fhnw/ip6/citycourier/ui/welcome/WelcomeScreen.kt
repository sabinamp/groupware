package ch.fhnw.ip6.citycourier.ui.welcome

import androidx.compose.Composable
import androidx.compose.ambient
import androidx.compose.unaryPlus
import androidx.ui.core.*
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.material.surface.Surface
import androidx.ui.res.imageResource
import androidx.ui.res.vectorResource

import androidx.ui.tooling.preview.Preview
import ch.fhnw.ip6.citycourier.R
import ch.fhnw.ip6.citycourier.model.Notification
import ch.fhnw.ip6.citycourier.ui.LightThemeColors
import ch.fhnw.ip6.citycourier.ui.themeTypography
import java.math.BigInteger


@Composable
private fun CityCourierLogo() {
    Container(modifier = Height(130.dp) wraps Expanded) {
        Clip(shape = RoundedCornerShape(8.dp)) {
            DrawImage(image = +imageResource(R.drawable.deliveryservice_logo))
        }
    }
}

@Composable
 fun WelcomeScreen() {
    val context = +ambient(ContextAmbient)
    val notification1 = Notification("Order 12345"," You have received a new order")
    val notification2 = Notification("Order 34567.","The delivery has been rescheduled.")
    val notification3 = Notification("Order 56789.","The delivery has been rescheduled.")
    val notifyList: List<Notification> = listOf( notification1, notification2, notification3)

    MaterialTheme (colors = LightThemeColors, typography = themeTypography) {
        VerticalScroller(isScrollable = true) {
            Column( modifier = Spacing(10.dp)) {
                CityCourierLogo()
                HeightSpacer(8.dp)

                Row(modifier = Spacing(2.dp), arrangement = Arrangement.Center ) {
                    IconsOverview()
                }
                HeightSpacer(8.dp)

                Container(modifier = Height(100.dp) wraps Expanded) {
                        Surface(color = Color.White, shape = RoundedCornerShape(4.dp),
                            elevation = 10.dp,  modifier = Spacing(30.dp)) {
                            Text("Notifications",
                                style= themeTypography.h6,
                                modifier = Spacing(left = Dp(10f), right = Dp(10f)))
                        }

                }

                //notification list
                  NotificationList(notifications = notifyList)
                }
            }


    }
}


@Composable
fun IconsOverview() {
    MaterialTheme (colors = LightThemeColors, typography = themeTypography) {
        FlexRow(modifier = Spacing(14.dp)) {
            expanded(flex=1.0f) {
                Column(){
                    Container(
                        width = Dp(60f),
                        height = Dp(60f),
                        alignment = Alignment.TopCenter
                    ) {
                        Clip(shape = RoundedCornerShape(8.dp)) {
                            DrawVector(vectorImage = +vectorResource(R.drawable.ic_bell100))
                        }
                    }
                    Text("Orders", style = themeTypography.h5)
                }
            }

            WidthSpacer(width = 14.dp)

            expanded(flex=1.0f){
                Column(){
                    Container(
                        width = Dp(60f),
                        height = Dp(60f),
                        alignment = Alignment.TopCenter
                    ) {
                        Clip(shape = RoundedCornerShape(8.dp)) {
                            DrawVector(vectorImage = +vectorResource(R.drawable.ic_profile_80))
                        }
                    }
                    Text("Profile" , style= themeTypography.h5 )

                }
            }

            WidthSpacer(width = 14.dp)

            expanded(flex=1.0f){
               Column(){
                   Container(
                       width = Dp(60f),
                       height = Dp(60f),
                       alignment = Alignment.TopCenter
                   ) {
                       Clip(shape = RoundedCornerShape(8.dp)) {
                           DrawVector(vectorImage = +vectorResource(R.drawable.ic_message_100))
                       }
                   }
                   Text("Chat", style= themeTypography.h5 )
               }
           }
        }
    }

}



@Preview
@Composable
fun DefaultPreview() {
    WelcomeScreen()

}