package ch.fhnw.ip6.citycourier.data

import ch.fhnw.ip6.citycourier.model.OrderDescriptiveInfo
import ch.fhnw.ip6.citycourier.model.TaskRequest
import ch.fhnw.ip6.citycourier.mqttservice.OrderGetEventListener
import ch.fhnw.ip6.citycourier.mqttservice.RequestReplyEventListener

interface OrdersRepository{
    /**
     * Get list of orders.
     */
   // fun getOrders(callback: (Result<Map<String, List<OrderDescriptiveInfo>>>) -> Unit)

    /**
     * Get order by id.
     */
    fun getOrder(orderId:String, callback: (Result<OrderDescriptiveInfo?>) -> Unit)

    /**
     * Add a specific task request.
     */
    fun addOrder(orderId:String,order: OrderDescriptiveInfo): Boolean

    fun addGetOrderListener(index:Int, listener: OrderGetEventListener)

    fun getListeners(): MutableList<OrderGetEventListener>

    fun handleGetOrderUIEvent(orderId: String)

}
