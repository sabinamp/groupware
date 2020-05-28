package ch.fhnw.ip6.citycourier.ui.profile

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
import androidx.ui.material.MaterialTheme
import androidx.ui.res.imageResource
import androidx.ui.res.vectorResource
import androidx.ui.text.TextStyle
import androidx.ui.tooling.preview.Preview
import ch.fhnw.ip6.citycourier.R
import ch.fhnw.ip6.citycourier.ui.LightThemeColors
import ch.fhnw.ip6.citycourier.ui.themeTypography


@Composable
fun ProfileScreen() {
    MaterialTheme (colors = LightThemeColors, typography = themeTypography) {
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
@Composable
fun Picture(){
    Row(modifier = Width(130.dp)){
        Text(text = "Profile info",style = TextStyle(fontSize = TextUnit.Companion.Sp(28)))
        }
    HeightSpacer(height = 15.dp)
    val image = +imageResource(R.drawable.ic_profile_60)
    Container(modifier = Size(130.dp, 130.dp)) {
        Clip(shape= CircleShape){
            DrawImage(image = image)
        }
    }
}

@Composable
fun ProfileInfo(){
    HeightSpacer(height=10.dp)
    Column(modifier=Width(130.dp)){
        Row(modifier=Width(50.dp)){
            Text(text="Name",modifier=Width(50.dp))
        }
    }
    Column(modifier=Width(130.dp)){
        Row(){
            Text(text="Martin",modifier=Width(50.dp))
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
                            DrawVector(vectorImage = +vectorResource(R.drawable.online_connection))
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
fun ProfilePreview() {
    ProfileScreen()
}





