package ir.beigirad.zeroapplication.network

import android.content.Context
import ir.beigirad.zeroapplication.ZeroApplication
import okhttp3.ResponseBody
import retrofit2.Call
import java.util.*

/**
 * Created by farhad-mbp on 1/23/18.
 */

class RequestProvider private constructor() {

    private val PRE_REGISTRATION_TO_RESEND = 1

    private var apiService: APIService
    private var apiProvider: APIProvider
    private var context: Context
    private var dataMap = HashMap<Int, Any>()

    init {
        this.context = ZeroApplication.getAppContext()
        this.apiProvider = APIProvider()
        apiService = apiProvider.getAPIService()
    }

    private object instanceHolder {
        val instance = RequestProvider()
    }

    companion object {
        val instance: RequestProvider by lazy { instanceHolder.instance }
    }

    fun test(): Call<ResponseBody> {
        return apiService.test()
    }

    fun cancellAll() {
        apiProvider.cancellAll()
    }

}