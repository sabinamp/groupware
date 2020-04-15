package ch.fhnw.ip6.citycourier.ui

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.*
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.*
import androidx.ui.material.Divider
import androidx.ui.material.MaterialTheme
import androidx.ui.material.surface.Surface
import androidx.ui.res.imageResource
import androidx.ui.res.vectorResource
import androidx.ui.text.TextStyle
import androidx.ui.tooling.preview.Preview
import ch.fhnw.ip6.citycourier.R
import ch.fhnw.ip6.citycourier.model.Notification


@Composable
private fun CityCourierLogo() {
    Container(modifier = Height(120.dp) wraps Expanded) {
        Clip(shape = RoundedCornerShape(8.dp)) {
            DrawImage(image = +imageResource(R.drawable.deliveryservice_logo))
        }
    }
}

@Composable
 fun WelcomeScreen() {
    // val context = +ambient(ContextAmbient)

    val notification1 = Notification("Order 12345"," You have received a new order")
    val notList: List<Notification> = listOf( notification1, Notification("Order 34567.","The delivery has been rescheduled."))

    MaterialTheme () {
        Column( modifier = Spacing(10.dp)) {
            CityCourierLogo()
            HeightSpacer(10.dp)
            IconsOverview()
            HeightSpacer(10.dp)

            Container(modifier = Height(120.dp) wraps Expanded) {
                Surface(color = Color.White, shape = RoundedCornerShape(8.dp), elevation = 8.dp) {
                    Text("Notifications",
                        style =TextStyle(color=Color.Black),
                        modifier = Spacing(left=10.dp,right = 50.dp)
                    )
                }
            }
            Divider(color = Color(0x14333333))
            //notification list
            //NotificationList(notifications = notList)
        }


    }

}


@Composable
private fun IconsOverview() {
    MaterialTheme (colors = LightThemeColors) {
        Row(modifier = Spacing(20.dp), arrangement = Arrangement.SpaceBetween ) {
            Column (){
                Container(
                    width = Dp(60f),
                    height = Dp(60f),
                    alignment = Alignment.TopCenter
                ) {
                    Clip(shape = RoundedCornerShape(8.dp)) {
                        DrawVector(vectorImage = +vectorResource(R.drawable.ic_bell40))
                    }
                }
                Text("Orders",  style = TextStyle(color=Color.White))
            }
            WidthSpacer(width = 20.dp)

            Column() {
                Container(
                    modifier = Width(Dp(60f)), height = Dp(60f),
                    alignment = Alignment.TopCenter
                ) {
                    Clip(shape = RoundedCornerShape(8.dp)) {
                        DrawVector(vectorImage = +vectorResource(R.drawable.ic_profile40))
                    }
                }
                Text("Profile" , style=TextStyle(color=Color.White))

            }
            WidthSpacer(width = 20.dp)
            Column() {
                Container(
                    width = Dp(60f),
                    height = Dp(60f),
                    alignment = Alignment.TopCenter
                ) {
                    Clip(shape = RoundedCornerShape(8.dp)) {
                        DrawVector(vectorImage = +vectorResource(R.drawable.ic_message_40))
                    }
                }
                Text("Chat", style= TextStyle(color=Color.White))

            }

        }
    }

}
@Composable
private fun NotificationList(notifications: List<Notification>) {
    VerticalScroller {
        // each notification in the list
        for (each in notifications) {
            Padding(16.dp) {
                NotificationCard(each)
            }
        }
    }


}
@Preview
@Composable
fun DefaultPreview() {
        WelcomeScreen()

}