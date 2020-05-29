package ch.fhnw.ip6.citycourier.data

import ch.fhnw.ip6.citycourier.model.DeliveryType
import ch.fhnw.ip6.citycourier.model.ShiftType
import ch.fhnw.ip6.citycourier.model.TaskRequest
import ch.fhnw.ip6.citycourier.model.TaskType
import org.junit.Assert
import org.junit.Test
import java.time.LocalDateTime

class DataServiceTest {
    @Test
    fun new_TaskRequest_OK() {
        val notification1 = TaskRequest()
        notification1.orderId("OR1123")
        notification1.assigneeId( "C102")
        notification1.addressLine("Rosenstrasse 14")
        notification1.deliveryType(DeliveryType.STANDARD)
        notification1.taskType(TaskType.PARCEL_COLLECTION)
        notification1.shift(ShiftType.AM)
        Assert.assertEquals(DeliveryType.STANDARD, notification1.deliveryType)
        Assert.assertEquals("Rosenstrasse 14", notification1.addressLine)
        Assert.assertEquals("OR1123", notification1.orderId)
    }

    @Test
    fun list_of_TaskRequest_OK(){
     /*   val notification1 = TaskRequest()
        notification1.orderId("OR1123")
        notification1.assigneeId( "C102")
        notification1.addressLine("Rosenstrasse 14")
        notification1.deliveryType(DeliveryType.STANDARD)
        notification1.taskType(TaskType.PARCEL_COLLECTION)
        notification1.shift(ShiftType.AM)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
               notification1.dueOn(LocalDateTime.now().plusDays(1))
               notification1.sentWhen(LocalDateTime.now())
           }

        val notification2 = TaskRequest()
        notification2.orderId("OR1122")
        notification2.assigneeId("C102")
        notification1.addressLine("Rosenstrasse 12")
        notification2.deliveryType(DeliveryType.STANDARD)
        notification2.taskType(  TaskType.PARCEL_COLLECTION)
        notification1.shift(ShiftType.AM)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification1.dueOn(LocalDateTime.now().plusDays(1))
            notification1.sentWhen(LocalDateTime.now())
        }
        val notification3 = TaskRequest()
        notification2.orderId("OR1124")
        notification2.assigneeId("C102")
        notification1.addressLine("Tulpenstr 12")
        notification2.deliveryType(DeliveryType.URGENT)
        notification2.taskType(  TaskType.DELIVERY_FIRST)
        notification1.shift(ShiftType.PM)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification1.dueOn(LocalDateTime.now().plusDays(2))
            notification1.sentWhen(LocalDateTime.now())
        }
        val notification4 = TaskRequest()
        notification2.orderId("OR1125")
        notification2.assigneeId("C102")
        notification1.addressLine("Tulpenstr 18")
        notification2.deliveryType(DeliveryType.URGENT)
        notification2.taskType(  TaskType.PARCEL_COLLECTION)
        notification1.shift(ShiftType.AM)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification1.dueOn(LocalDateTime.now().plusHours(1))
            notification1.sentWhen(LocalDateTime.now())
        }
*/
        Assert.assertEquals(dataService().size, 4)
        Assert.assertEquals(  "Rosenstrasse 12" ,dataService().get(0).addressLine  )
        Assert.assertEquals(  "OR1123" ,dataService().get(0).orderId  )

        Assert.assertEquals(  "Tulpenstr 18" ,dataService().get(3).addressLine  )
        Assert.assertEquals(  "OR1125" ,dataService().get(3).orderId  )
    }
}