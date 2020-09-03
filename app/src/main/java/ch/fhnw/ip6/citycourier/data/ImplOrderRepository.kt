package ch.fhnw.ip6.citycourier.data

import android.content.res.Resources
import android.os.Handler
import ch.fhnw.ip6.citycourier.model.OrderDescriptiveInfo
import ch.fhnw.ip6.citycourier.model.RequestReply
import ch.fhnw.ip6.citycourier.model.TaskRequest
import ch.fhnw.ip6.citycourier.mqttservice.OrderGetEventListener
import ch.fhnw.ip6.citycourier.mqttservice.RequestReplyEventListener
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutorService
import kotlin.random.Random

/*
* Implementation that returns a hardcoded list of Task Requests
 */
class ImplOrderRepository(
    private val executorService: ExecutorService,
    private val resultThreadHandler: Handler,
    private val resources: Resources
): OrdersRepository {


    private val orders: MutableMap<String,OrderDescriptiveInfo> by lazy{
        orderFakeData()
    }

    private var getOrderUIListeners: MutableList<OrderGetEventListener> = mutableListOf()

    fun orderFakeData(): MutableMap<String, OrderDescriptiveInfo>{
        val fakeOrder = OrderDescriptiveInfo()
        fakeOrder.customerName="Anastasia G"
        fakeOrder.timeZone="Europe/Zuerich"
        fakeOrder.orderId="OR100"
        val fakeMap= mutableMapOf<String, OrderDescriptiveInfo>()
        fakeMap.put("OR100",fakeOrder)
        return fakeMap
    }


    /**
     * Executes a block of code in the past and returns an error in the [callback]
     * if [block] throws an exception.
     */
    private fun executeInBackground(callback: (Result<Nothing>) -> Unit, block: () -> Unit) {
        executorService.execute {
            try {
                block()
            } catch (e: Exception) {
                resultThreadHandler.post { callback(Result.Error(e)) }
            }
        }
    }



    override fun getOrder(orderId: String, callback: (Result<OrderDescriptiveInfo?>) -> Unit) {
        executeInBackground(callback) {
            resultThreadHandler.post {
                callback(
                    Result.Success(
                        orders[orderId]
                    ))
            }
        }
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
        for( each: OrderGetEventListener in getListeners()){
            each.handleGetOrder(orderId)
        }
    }

}
