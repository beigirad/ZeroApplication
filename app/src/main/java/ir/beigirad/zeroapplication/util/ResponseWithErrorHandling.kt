package ir.beigirad.zeroapplication.util

import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

abstract class ResponseWithErrorHandling<R, E> : Callback<ResponseBody> {
    private val responseType: Type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
    private val errorType: Type

    abstract fun onResponseRequest(call: Call<ResponseBody>, response: R)

    abstract fun onErrorRequest(call: Call<ResponseBody>, error: E)

    abstract fun onFailureRequest(call: Call<ResponseBody>, error: Throwable)

    init {
        this.errorType = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1]
    }

    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

        try {
            if (response.isSuccessful) {
                val mResponse = Gson().fromJson<R>(response.body().string(), responseType)
                onResponseRequest(call, mResponse)

            } else {
                val mError = Gson().fromJson<E>(response.errorBody().string(), errorType)
                onErrorRequest(call, mError)
            }

        } catch (e: Exception) {
            e.printStackTrace()
            onFailureRequest(call, e)
        }

    }

    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
        onFailureRequest(call, t)
    }
}