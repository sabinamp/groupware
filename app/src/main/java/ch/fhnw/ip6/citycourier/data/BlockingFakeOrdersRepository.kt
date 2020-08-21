package ch.fhnw.ip6.citycourier.data

import android.content.Context
import android.content.res.Resources
import android.os.Handler
import ch.fhnw.ip6.citycourier.model.OrderDescriptiveInfo
import java.util.concurrent.ExecutorService
/*
* Implementation that returns a hardcoded list of Task Requests
 */
class BlockingFakeOrdersRepository(private val context: Context) : OrdersRepository {
    companion object Factory {
        val orderMap = mutableMapOf<String, OrderDescriptiveInfo>()
        fun createOrder(orderId: String): OrderDescriptiveInfo{
            val order1= OrderDescriptiveInfo()
            order1.orderId = orderId
            orderMap.put(orderId, order1)
            return order1
        }

    }
    private val orderList: MutableMap<String, OrderDescriptiveInfo> = initOrders()
    private fun initOrders():  MutableMap<String,OrderDescriptiveInfo> {

        val order1123= OrderDescriptiveInfo()
        order1123.orderId= "OR1123"

        val order1122 = OrderDescriptiveInfo()
        order1122.orderId="OR1122"


        return mutableMapOf(Pair("OR1122", order1122), Pair("OR1123",order1123))
    }

    override fun getOrder(orderId:String, callback: (Result<OrderDescriptiveInfo>) -> Unit): Unit {
        callback(Result.Success(orderList.getValue(orderId)))
    }


}