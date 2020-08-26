package ch.fhnw.ip6.citycourier.data

import android.content.Context
import ch.fhnw.ip6.citycourier.model.*

class BlockingFakeCourierRepository(private val context: Context) : CourierRepository {

    private var currentCourierInfo: CourierInfo = createCourierInfoForPreview()


    override fun getCourier(courierId: String, callback: (Result<CourierInfo>) -> Unit) {
        callback(Result.Success(currentCourierInfo))
    }

    override fun setCourierInfo(info: CourierInfo): Boolean {
        return if(info!=null){
            currentCourierInfo=info
            true
        }else false

    }

}