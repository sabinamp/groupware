package ch.fhnw.ip6.citycourier.ui.welcome

import android.graphics.drawable.VectorDrawable
import androidx.compose.Composable
import androidx.compose.ambient
import androidx.compose.unaryPlus
import androidx.ui.core.*
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.Image
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
import ch.fhnw.ip6.citycourier.data.dataService
import java.util.*


@Composable
private fun CityCourierLogo() {
    Container(modifier = Height(110.dp) wraps Expanded) {
        Clip(shape = RoundedCornerShape(8.dp)) {
            DrawImage(image = +imageResource(R.drawable.deliveryservice_logo))
        }
    }
}

@Composable
 fun WelcomeScreen() {
    val context = +ambient(ContextAmbient)


    MaterialTheme (colors = LightThemeColors, typography = themeTypography) {
        VerticalScroller(isScrollable = true) {
            Column( modifier = Spacing(5.dp)) {
                CityCourierLogo()
                HeightSpacer(5.dp)

                Row(modifier = Spacing(2.dp), arrangement = Arrangement.Center ) {
                    IconsOverview()
                }
                HeightSpacer(5.dp)

                /*  Container(modifier = Height(60.dp) wraps Expanded) {
                      Surface(color = Color.White, shape = RoundedCornerShape(4.dp),
                            elevation = 10.dp,  modifier = Spacing(10.dp)) {
                            Text("Notifications",
                                style= themeTypography.h6,
                                modifier = Spacing(left = Dp(10f), right = Dp(10f)))
                        }

                }*/
                Row(modifier = Spacing(2.dp), arrangement = Arrangement.Center ) {
                    Text("Notifications",
                        style= themeTypography.h3,
                        modifier = Spacing(left = Dp(10f), right = Dp(10f)))
                }
                //notification list
                  NotificationList(notifications = dataService())
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
                    Text("Orders", style = themeTypography.h3)
                }
            }

            WidthSpacer(width = 18.dp)

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
                    Text("Profile" , style= themeTypography.h3 )

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
                       //val vectorImage = +vectorResource(R.drawable.ic_message_100)

                   }
                   Text("Chat", style= themeTypography.h3 )
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