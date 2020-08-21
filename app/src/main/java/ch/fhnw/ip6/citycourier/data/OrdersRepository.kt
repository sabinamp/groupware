package ch.fhnw.ip6.citycourier.data

import ch.fhnw.ip6.citycourier.model.OrderDescriptiveInfo
import ch.fhnw.ip6.citycourier.model.TaskRequest

interface OrdersRepository{
    /**
     * Get list of orders.
     */
   // fun getOrders(callback: (Result<Map<String, List<OrderDescriptiveInfo>>>) -> Unit)

    /**
     * Get order by id.
     */
    fun getOrder(orderId:String, callback: (Result<OrderDescriptiveInfo>) -> Unit)


}


