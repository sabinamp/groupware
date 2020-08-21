package ch.fhnw.ip6.citycourier.data

import android.content.Context
import ch.fhnw.ip6.citycourier.model.*

class BlockingFakeCourierRepository(private val context: Context) : CourierRepository {

    private val courierInfoC106: CourierInfo by lazy {
        courierC106Data
    }


    override fun getCourier(courierId: String, callback: (Result<CourierInfo>) -> Unit) {
        callback(Result.Success(courierInfoC106))
    }

}