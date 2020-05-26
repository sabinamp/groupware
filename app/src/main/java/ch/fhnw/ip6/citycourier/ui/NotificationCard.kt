package ch.fhnw.ip6.citycourier.ui

import android.graphics.drawable.shapes.RectShape
import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.*
import androidx.ui.engine.geometry.Shape
import androidx.ui.foundation.HorizontalScroller
import androidx.ui.foundation.shape.RectangleShape
import androidx.ui.foundation.shape.border.Border
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.ButtonStyle
import androidx.ui.material.Divider
import androidx.ui.material.surface.Card
import androidx.ui.material.surface.Surface
import androidx.ui.material.withOpacity
import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview

import ch.fhnw.ip6.citycourier.R
import ch.fhnw.ip6.citycourier.model.Notification
import ch.fhnw.ip6.citycourier.ui.welcome.WelcomeScreen

@Composable
fun NotificationCard(notification: Notification){
    Card(shape = RoundedCornerShape(8.dp), elevation = 8.dp, modifier = Height(90.dp) wraps Expanded) {
        FlexRow{
            expanded(flex=1.0f){
                Column( modifier = Spacing(5.dp)
                ){
                    Container(height=50.dp, width=50.dp) {
                        DrawVector(vectorImage = +vectorResource(R.drawable.ic_bell80))
                    }
                }
            }
            //WidthSpacer(5.dp)
           expanded(flex=5.0f){
               Column(modifier = Spacing(5.dp)
               ) {
                   Text(notification.title, style = themeTypography.subtitle1)
                   Text(notification.message, style= themeTypography.body2.withOpacity(0.90f)
                   )
               }
           }
            expanded(flex=2.0f) {
                Column(modifier = Spacing(5.dp)
                ) {
                    Button(text = "OK",
                        style = ButtonStyle(Color(151,255,177), shape = RectangleShape),
                        onClick = {})
                    HeightSpacer(5.dp)
                    Button(text = "NO",
                        style = ButtonStyle(Color(255,151,171), shape = RectangleShape),
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
    NotificationCard(Notification("title", "message"))

}





