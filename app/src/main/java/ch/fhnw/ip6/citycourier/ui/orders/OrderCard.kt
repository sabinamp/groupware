package ch.fhnw.ip6.citycourier.ui.orders


import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.*
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.vector.DrawVector
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.surface.Card

import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview


import ch.fhnw.ip6.citycourier.R
import ch.fhnw.ip6.citycourier.model.*
import ch.fhnw.ip6.citycourier.ui.themes.LightThemeColors
import ch.fhnw.ip6.citycourier.ui.themes.themeTypography
import java.time.LocalDateTime


@Composable
fun OrderCard(notification: TaskRequest){
    Card(shape = RoundedCornerShape(8.dp), elevation = 8.dp, modifier = Height(90.dp) wraps Expanded) {
        FlexRow{
            expanded(flex=1.0f){
                Column( modifier = Spacing(2.dp)
                ){
                    Container(height=60.dp, width=60.dp) {
                        if(DeliveryType.STANDARD == notification.deliveryType ){
                            DrawVector(vectorImage = +vectorResource(R.drawable.ic_bell_60)  )
                        }else{
                            DrawVector(vectorImage = +vectorResource(R.drawable.ic_bell_urgent_60))
                        }


                    }
                }
            }
         expanded(flex=5.0f){
               Column(modifier = Spacing(4.dp)
               ) {
                   Text("Order "+notification.orderId, style = themeTypography.body1)
                   Text("Task: "+notification.taskType, style= themeTypography.body2.withOpacity(0.90f))

                   Text("Address: "+notification.addressLine, style= themeTypography.body2.withOpacity(0.90f)
                   )
               }
           }
            expanded(flex=3.5f) {
                    Row(modifier = Spacing(8.dp)
                    ) {
                        /*Button(text = "Info",
                            style = ButtonStyle(Color(151, 255, 177),
                                shape = RoundedCornerShape(12.dp)),
                            onClick = {})*/
                        FloatingActionButton(
                            color = Color(151, 255, 177),
                            text = "Info",
                            onClick = {})
                         FloatingActionButton(
                           color = LightThemeColors.onError,
                           text = "Call",
                           onClick = {})

                    }
            }
        }
    }
    Divider(color = Color.DarkGray)

}


@Preview
@Composable
fun OrderCardPreview() {
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

    OrderCard(notification1)

}




