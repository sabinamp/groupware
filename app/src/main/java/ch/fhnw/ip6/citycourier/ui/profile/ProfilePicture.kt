package ch.fhnw.ip6.citycourier.ui.profile

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Alignment
import androidx.ui.core.Clip
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.shape.DrawShape
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color

import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.*
import androidx.ui.material.surface.Card
import androidx.ui.res.imageResource

import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import ch.fhnw.ip6.citycourier.R
import ch.fhnw.ip6.citycourier.ui.themes.LightThemeColors
import ch.fhnw.ip6.citycourier.ui.themes.themeTypography


@Composable
fun ProfilePicture(name:String){

    Card(modifier= Height(150.dp) wraps Expanded,
        color= LightThemeColors.onBackground,
        elevation = 4.dp,
        shape = RoundedCornerShape(topLeft = 90.dp, topRight = 90.dp,
            bottomLeft = 0.dp, bottomRight = 0.dp)
    ){
        Column(modifier = Spacing(10.dp), arrangement = Arrangement.Center) {
            FlexRow(){
                expanded(flex=2.0f){
                    Container(height=100.dp,
                        width=100.dp,
                        alignment = Alignment.Center,
                        padding = EdgeInsets(20.dp)) {
                        Clip(shape= CircleShape){
                            DrawVector(vectorImage = +vectorResource(R.drawable.ic_profile_100))
                        }
                    }
                }

                expanded(flex=3.0f){
                    Column() {
                        Padding(padding = 10.dp) {
                            Text(name, style = themeTypography.h2, maxLines = 1)
                        }
                        Padding(padding = 10.dp) {
                            Row(modifier = Height(50.dp)){
                                Text("Available", style = themeTypography.h5, maxLines = 1)
                                WidthSpacer(width = 5.dp)
                                Container(
                                        height = 30.dp,
                                        width = 30.dp,
                                        alignment = Alignment.Center,
                                        padding = EdgeInsets(10.dp)
                                    ) {

                                        DrawShape(shape = CircleShape, color = Color.Green)

                                    }

                            }

                        }

                    }


                }
            }


        }

    }

}

@Preview
@Composable
fun ProfilePicturePreview(){
    ProfilePicture("Martin Hauser")
}