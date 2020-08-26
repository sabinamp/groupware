package ch.fhnw.ip6.citycourier.data

import ch.fhnw.ip6.citycourier.model.Courier
import ch.fhnw.ip6.citycourier.model.CourierInfo
import ch.fhnw.ip6.citycourier.model.TaskRequest


interface CourierRepository {
    /**
     * Get order by id.
     */
    fun getCourier(courierId:String, callback: (Result<CourierInfo>) -> Unit)


    /**
     * Add info about a specific courier.
     */
    fun setCourierInfo(info: CourierInfo): Boolean
}
