package ch.fhnw.ip6.citycourier.ui

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Modifier
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.core.sp
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.*
import androidx.ui.material.Divider
import androidx.ui.material.surface.Card
import androidx.ui.material.withOpacity
import androidx.ui.res.vectorResource
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import ch.fhnw.ip6.citycourier.R
import ch.fhnw.ip6.citycourier.model.Notification

@Composable
fun NotificationCard(notification: Notification){
    Card(shape = RoundedCornerShape(8.dp), elevation = 8.dp,modifier = Height(130.dp) wraps Expanded) {
        Row(modifier = Modifier.None,
            arrangement = Arrangement.Begin
        ){
            Column( modifier = Spacing(4.dp)
            ){
                Container(height=44.dp, width=44.dp) {
                DrawVector(vectorImage = +vectorResource(R.drawable.ic_bell40))
             }
            }
            WidthSpacer(10.dp)
            Column(modifier = Spacing(8.dp)
            ) {
                Text(notification.title, style = TextStyle(color=Color.Black, fontSize= 20.sp, fontWeight = FontWeight.Bold)
                )
                Text(notification.message, style= TextStyle(color=Color.Black, fontSize= 16.sp)
                    .withOpacity(0.90f)

                )
            }

        }
    }
    Divider(color = Color.Gray)

}


