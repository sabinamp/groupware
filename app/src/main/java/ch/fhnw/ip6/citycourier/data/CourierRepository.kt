package ch.fhnw.ip6.citycourier.data

import ch.fhnw.ip6.citycourier.model.Courier


interface CourierRepository {
    /**
     * Get order by id.
     */
    fun getCourier(courierId:String, callback: (Result<Courier>) -> Unit)
}
