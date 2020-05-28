package ch.fhnw.ip6.citycourier.ui.profile

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.ambient
import androidx.compose.unaryPlus
import androidx.ui.core.*
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.*
import androidx.ui.layout.Size
import androidx.ui.material.Divider
import androidx.ui.material.MaterialTheme
import androidx.ui.res.imageResource
import androidx.ui.res.vectorResource
import androidx.ui.text.TextStyle
import androidx.ui.tooling.preview.Preview
import ch.fhnw.ip6.citycourier.R
import ch.fhnw.ip6.citycourier.data.dataService
import ch.fhnw.ip6.citycourier.model.Profile
import ch.fhnw.ip6.citycourier.ui.LightThemeColors
import ch.fhnw.ip6.citycourier.ui.themeTypography
import ch.fhnw.ip6.citycourier.ui.welcome.NotificationList



@Composable
fun ProfileScreen() {
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
                Picture()
                ProfileInfo()
            }
        }

    }
}
@Composable
fun Picture(){
    val image = +imageResource(R.drawable.ic_profile_60)
    Container(modifier = Size(130.dp, 130.dp)) {
        Clip(shape= CircleShape){
            DrawImage(image = image)
        }
    }
}

@Composable
fun ProfileInfo(){
    HeightSpacer(height = 10.dp)
    Text(text="Profile Info", modifier = Width(130.dp), style = TextStyle(fontSize = TextUnit.Companion.Sp(28)))
    Column(modifier = Width(130.dp)) {
        Row(modifier = Width(50.dp)) {
            Text(text="Name", modifier = Width(50.dp))
            Text(text="First name", modifier = Width(50.dp))
            Text(text="Contact", modifier = Width(50.dp))
        }
    }
    Column(modifier = Width(130.dp)) {
        Row() {
            Text(text="Martin", modifier = Width(50.dp))
            Text(text="Keller", modifier = Width(50.dp))
            Text(text="martin.keller@citycourier.ch", modifier = Width(50.dp))
        }
    }
}

@Composable
private fun CityCourierLogo() {
    Container(modifier = Height(110.dp) wraps Expanded) {
        Clip(shape = RoundedCornerShape(8.dp)) {
            DrawImage(image = +imageResource(R.drawable.deliveryservice_logo))
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
                            DrawVector(vectorImage = +vectorResource(R.drawable.arrow))
                        }

                    }
                    Text("Orders", style = themeTypography.subtitle1)
                }
            }

            WidthSpacer(width = 40.dp)

            expanded(flex=1.0f){
                Column(){
                    Container(
                        width = Dp(60f),
                        height = Dp(60f),
                        alignment = Alignment.TopCenter
                    ) {
                        Clip(shape = RoundedCornerShape(8.dp)) {
                            DrawVector(vectorImage = +vectorResource(R.drawable.Online_Connection))
                        }

                    }
                    Text("Profile" , style= themeTypography.subtitle1 )

                }
            }


        }
    }

}





@Preview
@Composable
fun DefaultPreview() {

    ProfileScreen()

}





