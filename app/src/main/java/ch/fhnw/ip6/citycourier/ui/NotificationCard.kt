package ch.fhnw.ip6.citycourier.ui

import android.graphics.drawable.shapes.RectShape
import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.*
import androidx.ui.engine.geometry.Outline
import androidx.ui.engine.geometry.Shape
import androidx.ui.foundation.HorizontalScroller
import androidx.ui.foundation.shape.RectangleShape
import androidx.ui.foundation.shape.border.Border
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.surface.Card
import androidx.ui.material.surface.Surface
import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview

import ch.fhnw.ip6.citycourier.R
import ch.fhnw.ip6.citycourier.model.DeliveryType
import ch.fhnw.ip6.citycourier.model.Notification
import ch.fhnw.ip6.citycourier.ui.welcome.WelcomeScreen

@Composable
fun NotificationCard(notification: Notification){
    Card(shape = RoundedCornerShape(8.dp), elevation = 8.dp, modifier = Height(90.dp) wraps Expanded) {
        FlexRow{
            expanded(flex=1.0f){
                Column( modifier = Spacing(5.dp)
                ){
                    Container(height=60.dp, width=60.dp) {
                        if(notification.type == DeliveryType.STANDARD){
                            DrawVector(vectorImage = +vectorResource(R.drawable.ic_bell_60)  )
                        }else{
                            DrawVector(vectorImage = +vectorResource(R.drawable.ic_bell_urgent_60))
                        }


                    }
                }
            }
            //WidthSpacer(5.dp)
           expanded(flex=5.0f){
               Column(modifier = Spacing(5.dp)
               ) {
                   Text(notification.title, style = themeTypography.body1)
                   Text(notification.message, style= themeTypography.body2.withOpacity(0.90f)
                   )
               }
           }
            expanded(flex=2.0f) {

                    Column(modifier = Spacing(5.dp)
                    ) {
                      /*  FloatingActionButton(
                            color =Color(151, 255, 177),
                            text = "OK",
                            onClick = { *//* do something here *//* })*/
                        Button(text = "OK",
                            style = ButtonStyle(Color(151, 255, 177),
                                shape = RectangleShape),
                            modifier = MaxHeight(45.dp),
                            onClick = {})
                        HeightSpacer(3.dp)
                       /* FloatingActionButton(
                            color = LightThemeColors.onError,
                            text = "NO",
                            onClick = { *//* do something here *//* })*/
                        Button(text = "NO",
                            style = ButtonStyle(LightThemeColors.onError, shape = RectangleShape),
                            modifier = MaxHeight(45.dp),
                            onClick = {})
                    }
            }
        }
    }
    Divider(color = Color.Gray)

}

@Preview
@Composable
fun CardPreview() {
    NotificationCard(Notification("title", "message", DeliveryType.STANDARD))

}





