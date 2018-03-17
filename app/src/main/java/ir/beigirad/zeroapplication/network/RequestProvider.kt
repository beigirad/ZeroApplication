package ir.beigirad.zeroapplication.network

import android.content.Context
import ir.beigirad.zeroapplication.ZeroApplication
import okhttp3.ResponseBody
import retrofit2.Call

/**
 * Created by farhad-mbp on 1/23/18.
 */

class RequestProvider private constructor() {

    private var apiService: APIService
    private var apiProvider: APIProvider
    private var context: Context

    init {
        this.context = ZeroApplication.getAppContext()
        this.apiProvider = APIProvider()
        apiService = apiProvider.getAPIService()
    }

    private object InstanceHolder {
        val instance = RequestProvider()
    }

    companion object {
        val instance: RequestProvider by lazy { InstanceHolder.instance }
    }

    fun test(): Call<ResponseBody> {
        return apiService.test()
    }

    fun cancellAll() {
        apiProvider.cancellAll()
    }

}