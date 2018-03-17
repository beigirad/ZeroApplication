package ir.beigirad.zeroapplication.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.POST

/**
 * Created by farhad-mbp on 11/4/17.
 */
interface APIService {

    @POST("/")
    fun test(): Call<ResponseBody>

}