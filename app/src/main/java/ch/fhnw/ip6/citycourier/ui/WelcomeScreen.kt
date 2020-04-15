package ch.fhnw.ip6.citycourier.ui

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
import androidx.ui.text.TextStyle
import androidx.ui.text.font.Font
import androidx.ui.text.font.FontFamily
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
    val context = +ambient(ContextAmbient)

    MaterialTheme (colors = LightThemeColors) {
        Column( modifier = Spacing(10.dp)) {
            CityCourierLogo()
            HeightSpacer(10.dp)
            IconsOverview()
            HeightSpacer(10.dp)

            Container(modifier = Height(100.dp) wraps Expanded) {
                Surface(color = Color.White, shape = RoundedCornerShape(4.dp),
                    elevation = 8.dp,  modifier = Spacing(10.dp)) {
                    Text("Notifications",
                        style =TextStyle(color = Color.Black, fontSize= 24.sp) )
                }

            }
            val notification1 = Notification("Order 12345"," You have received a new order")
            val notification2 = Notification("Order 34567.","The delivery has been rescheduled.")

            val notifyList: List<Notification> = listOf( notification1, notification2)
            //notification list
            NotificationList(notifications = notifyList)

        }

    }

}


@Composable
private fun IconsOverview() {
    MaterialTheme (colors = LightThemeColors) {
        Row(modifier = Spacing(30.dp), arrangement = Arrangement.SpaceBetween ) {
            Column (){
                Container(
                    width = Dp(100f),
                    height = Dp(100f),
                    alignment = Alignment.TopCenter
                ) {
                    Clip(shape = RoundedCornerShape(8.dp)) {
                        DrawVector(vectorImage = +vectorResource(R.drawable.ic_bell100))
                    }
                }
                Text("Orders",  style = TextStyle(color=Color.White, fontSize= 16.sp,
                    fontFamily = FontFamily(Font(name="Roboto-Bold.ttf"))
                ) )
            }
            WidthSpacer(width = 26.dp)

            Column() {
                Container(
                    modifier = Width(Dp(100f)), height = Dp(100f),
                    alignment = Alignment.TopCenter
                ) {
                    Clip(shape = RoundedCornerShape(8.dp)) {
                        DrawVector(vectorImage = +vectorResource(R.drawable.ic_profile_100))
                    }
                }
                Text("Profile" , style=TextStyle(color=Color.White, fontSize= 16.sp,
                    fontFamily = FontFamily(Font(name="Roboto-Bold.ttf"))
                ) )

            }
            WidthSpacer(width = 26.dp)
            Column() {
                Container(
                    width = Dp(100f),
                    height = Dp(100f),
                    alignment = Alignment.TopCenter
                ) {
                    Clip(shape = RoundedCornerShape(8.dp)) {
                        DrawVector(vectorImage = +vectorResource(R.drawable.ic_message_100))
                    }
                }
                Text("Chat", style= TextStyle(color=Color.White,
                    fontSize= 16.sp,fontFamily = FontFamily(Font(name="Roboto-Bold.ttf"))
                ) )

            }

        }
    }

}
@Composable
private fun NotificationList(notifications: List<Notification>) {
    VerticalScroller(isScrollable = true) {
        // each notification in the list
        for (each in notifications) {
            Padding(10.dp) {
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