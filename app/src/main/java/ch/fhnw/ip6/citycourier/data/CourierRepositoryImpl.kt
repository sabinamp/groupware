package ch.fhnw.ip6.citycourier.data

import android.content.Context
import ch.fhnw.ip6.citycourier.model.Courier

class CourierRepositoryImpl(private val context: Context) : CourierRepository {
    override fun getCourier(courierId: String, callback: (Result<Courier>) -> Unit) {
        TODO("Not yet implemented")
    }

}