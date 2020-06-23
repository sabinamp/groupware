package ch.fhnw.ip6.citycourier.ui.welcome


import android.content.res.Resources
import android.graphics.drawable.Icon
import android.widget.ImageView
import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.*
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.Image
import androidx.ui.graphics.imageFromResource
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.surface.Card
import androidx.ui.res.Resource
import androidx.ui.res.imageResource

import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview


import ch.fhnw.ip6.citycourier.R
import ch.fhnw.ip6.citycourier.model.*
import ch.fhnw.ip6.citycourier.ui.themes.LightThemeColors
import ch.fhnw.ip6.citycourier.ui.themes.themeTypography
import java.time.LocalDateTime


@Composable
fun NotificationCard(notification: TaskRequest){
    Card(shape = RoundedCornerShape(8.dp), elevation = 8.dp, modifier = Height(120.dp) wraps Expanded) {
        FlexRow(crossAxisAlignment = CrossAxisAlignment.Start){
            expanded(flex=2.0f){
                Column( modifier = Spacing(8.dp), arrangement = Arrangement.Center
                ){
                   Container(height=50.dp, width=50.dp) {
                        if(DeliveryType.STANDARD == notification.deliveryType ){
                            DrawVector(vectorImage = +vectorResource(R.drawable.ic_bell_60)  )
                        }else{
                            DrawVector(vectorImage = +vectorResource(R.drawable.ic_bell_urgent_60))
                        }

                    }

                }
            }
            expanded(flex=7.0f){
               Column(modifier = Spacing(4.dp)
               ) {
                   Text("Delivery Request "+notification.orderId, style = themeTypography.subtitle2)
                   Text("Task: "+notification.taskType, style= themeTypography.body2.withOpacity(0.90f))

                   Text("Address: "+notification.addressLine, style= themeTypography.body2.withOpacity(0.90f)
                   )
                   Row(modifier = Spacing(10.dp)
                   ) {
                       Button(text = "New",
                           style = ButtonStyle(LightThemeColors.primaryVariant, shape = RoundedCornerShape(5.dp)),
                           modifier = MaxHeight(50.dp),
                           onClick = {})
                      WidthSpacer(width = 4.dp)
                      Button(text = "OK",
                           style = ButtonStyle(Color(151, 255, 177),
                               shape = CircleShape),
                           modifier = MaxHeight(50.dp),
                           onClick = {})
                       WidthSpacer(width = 4.dp)

                       Button(text = "No ",
                           style = ButtonStyle(LightThemeColors.onError, shape = CircleShape),
                           modifier = MaxHeight(50.dp),
                           onClick = {})


                   }
               }
           }

        }
    }
    Divider(color = Color.Gray)

}




@Preview
@Composable
fun NotificationPreview() {
    val notification1 = TaskRequest()
    notification1.orderId("OR1123")
    notification1.assigneeId( "C102")
    notification1.addressLine("Rosenstrasse 14")
    notification1.deliveryType(DeliveryType.STANDARD)
    notification1.taskType(TaskType.PARCEL_COLLECTION)

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
        notification1.dueOn(LocalDateTime.now().plusHours(7))
        notification1.sentWhen(LocalDateTime.now())
    }
    notification1.shift(ShiftType.AM)

    NotificationCard(notification1)

}




