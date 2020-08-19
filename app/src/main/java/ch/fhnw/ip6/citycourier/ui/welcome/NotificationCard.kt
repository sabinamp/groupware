package ch.fhnw.ip6.citycourier.ui.welcome


import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.Text

import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color

import androidx.ui.layout.*
import androidx.ui.material.*

import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp


import ch.fhnw.ip6.citycourier.R
import ch.fhnw.ip6.citycourier.model.*
import ch.fhnw.ip6.citycourier.ui.themes.LightThemeColors
import ch.fhnw.ip6.citycourier.ui.themes.themeTypography
import java.time.LocalDateTime


@Composable
fun NotificationCard(notification: TaskRequest){
    Card(shape = RoundedCornerShape(8.dp), elevation = 8.dp,
        modifier = Modifier.height(120.dp).plus(Modifier.fillMaxWidth())) {
        Row(horizontalArrangement = Arrangement.Center){

                Column( modifier = Modifier.padding(8.dp)){
                   Box(modifier = Modifier.height(50.dp).plus(Modifier.width(50.dp))) {
                        if(DeliveryType.STANDARD == notification.deliveryType ){
                            vectorResource(R.drawable.ic_bell_60)
                        }else{
                            vectorResource(R.drawable.ic_bell_urgent_60)
                        }

                    }

                }
            }

               Column(modifier =Modifier.padding(4.dp)
               ) {
                   Text("Delivery Request "+notification.orderId, style = themeTypography.subtitle2)
                   Text("Task: "+notification.taskType, style= themeTypography.body2)

                   Row(modifier = Modifier.padding(10.dp)
                   ) {
                       Button(
                           backgroundColor = LightThemeColors.primaryVariant,
                           shape = RoundedCornerShape(5.dp),
                           modifier =Modifier.height(50.dp),
                           onClick = {}){"New"
                    }
                      Spacer(modifier = Modifier.width(4.dp))
                      Button(backgroundColor = Color(151, 255, 177),
                               shape = CircleShape,
                          modifier =Modifier.height(50.dp),
                           onClick = {}){
                          "OK}"
                          Spacer(modifier = Modifier.width(4.dp))

                       Button(backgroundColor = LightThemeColors.onError, shape = CircleShape,
                           modifier =Modifier.height(50.dp),
                           onClick = {}){
                           "NO"
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

    notification1.deliveryType(DeliveryType.STANDARD)
    notification1.taskType(TaskType.PARCEL_COLLECTION)

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
        notification1.dueOn(LocalDateTime.now().plusHours(7))
        notification1.sentWhen(LocalDateTime.now())
    }

    NotificationCard(notification1)

}




