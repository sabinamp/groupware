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
import androidx.ui.material.Button
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
                CourierLogo()
                HeightSpacer(5.dp)
                Row(modifier = Spacing(2.dp), arrangement = Arrangement.Center ) {
                    StatusBar()
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
        Text(text = "Profile info",
            //style = TextStyle(fontSize = TextUnit.Companion.Sp(28))
            style = themeTypography.subtitle1
        )
    }
    HeightSpacer(height = 5.dp)

    Container(modifier = Size(100.dp, 100.dp)) {
        Clip(shape= CircleShape){
            DrawVector(vectorImage = +vectorResource(R.drawable.ic_profile_60))
        }
    }
}

@Composable
fun ProfileInfo(){
    HeightSpacer(height=2.dp)
    Column(modifier = Spacing(5.dp)) {
            Row(modifier = Spacing(5.dp)) {
                Text(text="CourierID", modifier = Width(150.dp))
                Text(text="courier00C1", modifier = Width(150.dp))
            }

            Row(modifier = Spacing(5.dp)) {
                Text(text="Device name", modifier = Width(150.dp))
                Text(text="RedmiHauser", modifier = Width(150.dp))
            }

            Row(modifier = Spacing(5.dp)){
                Text(text="First name", modifier = Width(150.dp))
                Text(text="Martin", modifier = Width(150.dp))
            }
            Row(modifier = Spacing(5.dp)) {
                Text(text="Last name", modifier = Width(150.dp))
                Text(text="Hauser", modifier = Width(150.dp))
            }

            Row(modifier = Spacing(5.dp)) {
                Text(text="Region", modifier = Width(150.dp))
                Text(text="City Zürich West", modifier = Width(150.dp))
            }
            Row(modifier = Spacing(5.dp)) {
                Text(text="Mail", modifier = Width(150.dp))
                Text(text="info@citycourier.ch", modifier = Width(150.dp))
            }

            Row(modifier = Spacing(5.dp)) {
                Text(text="Telephone number", modifier = Width(150.dp))
                Text(text="+41442205020", modifier = Width(150.dp))
            }


    }
}


@Composable
fun CourierLogo() {
    Container(modifier = Height(90.dp) wraps Expanded) {
        Clip(shape = RoundedCornerShape(8.dp)) {
            DrawImage(image = +imageResource(R.drawable.deliveryservice_logo))
        }
    }
}

@Composable
fun StatusBar() {
    MaterialTheme (colors = LightThemeColors, typography = themeTypography) {
        FlexRow(modifier = Spacing(14.dp)) {
            expanded(flex=1.0f) {
                Column(){
                    Container(
                        width = Dp(30f),
                        height = Dp(30f),
                        alignment = Alignment.TopCenter
                    ) {
                        Clip(shape = RoundedCornerShape(8.dp)) {
                            DrawImage(image = +imageResource(R.drawable.arrow))
                        }
                    }
                }
            }

            WidthSpacer(width = 10.dp)

            expanded(flex=1.0f){
                Column() {
                    Row() {

                        Container(
                            width = Dp(30f),
                            height = Dp(30f),
                            alignment = Alignment.CenterRight
                        ) {
                            Clip(shape = RoundedCornerShape(8.dp)) {
                                DrawImage(image = +imageResource(R.drawable.online_connection))
                            }

                        }
                            WidthSpacer(width = 15.dp)
                            Button() {
                                Text("Online")

                                }

                            }


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





