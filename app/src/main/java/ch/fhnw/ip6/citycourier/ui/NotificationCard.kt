package ch.fhnw.ip6.citycourier.ui

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Modifier
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.*
import androidx.ui.material.Divider
import androidx.ui.material.surface.Card
import androidx.ui.material.withOpacity
import androidx.ui.res.vectorResource
import ch.fhnw.ip6.citycourier.R
import ch.fhnw.ip6.citycourier.model.Notification

@Composable
fun NotificationCard(notification: Notification){
    Card(shape = RoundedCornerShape(8.dp), elevation = 8.dp) {
        Row(modifier = Modifier.None,
            arrangement = Arrangement.Begin
        ){
            Column( modifier = Spacing(6.dp)
            ){
                DrawVector(vectorImage = +vectorResource(R.drawable.ic_bell24))
            }
            WidthSpacer(10.dp)
            Column(modifier = Spacing(6.dp)
            ) {
                Text(notification.title, style = themeTypography.h6
                )
                Text(notification.message, style = themeTypography.body2
                    .withOpacity(0.87f)

                )
            }

        }
    }
    Divider(color = Color.Gray)

}


