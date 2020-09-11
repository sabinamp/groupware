package ch.fhnw.ip6.citycourier.data

import android.content.Context
import ch.fhnw.ip6.citycourier.model.OrderDescriptiveInfo
import ch.fhnw.ip6.citycourier.mqttservice.OrderGetEventListener
import ch.fhnw.ip6.citycourier.mqttservice.RequestReplyEventListener
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class BlockingOrdersRepository(private val context: Context) : OrdersRepository {

    private val orders: MutableMap<String,OrderDescriptiveInfo> by lazy{
        orderFakeData()
    }
    private var getOrderUIListeners: MutableList<OrderGetEventListener> = mutableListOf()

    fun orderFakeData(): MutableMap<String, OrderDescriptiveInfo>{
        val fakeOrder = OrderDescriptiveInfo()
        fakeOrder.customerName="Anastasia G"
        fakeOrder.timeZone="Europe/Zuerich"
        fakeOrder.orderId="OR1100"
        return mutableMapOf(Pair("order1100", fakeOrder))
    }


    override fun getOrder(orderId: String, callback: (Result<OrderDescriptiveInfo?>) -> Unit) {
        callback(Result.Success(orders[orderId]))
    }

    override fun addOrder(orderId:String, order: OrderDescriptiveInfo): Boolean {
        orders[orderId] = order
        return true
    }

    override fun addGetOrderListener(index: Int, listener: OrderGetEventListener) {
        getOrderUIListeners.add(index,listener)
    }

    override fun getListeners(): MutableList<OrderGetEventListener> {
        return getOrderUIListeners
    }

    override fun handleGetOrderUIEvent(orderId: String) {
        runBlocking {
            launch{
                for( each: OrderGetEventListener in getListeners()){
                    each.handleGetOrder(orderId)
                }
            }
        }

    }

}