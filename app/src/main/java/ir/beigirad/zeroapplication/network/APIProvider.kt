package ir.beigirad.zeroapplication.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by farhad-mbp on 11/4/17.
 */
public class APIProvider {
    private val BASE_URL = "http://46.32.17.139:9080"

    private var okHttpClient: OkHttpClient

    private var mRetrifit: Retrofit? = null
    private var mAPIService: APIService? = null

    init {
        var builder = OkHttpClient.Builder()
        builder.addNetworkInterceptor(StethoInterceptor())

        builder.addInterceptor { chain ->
            val build = chain?.request()?.newBuilder()
//                        ?.addHeader("Authorization", "")
                    ?.build()

            chain!!.proceed(build)
        }
        okHttpClient = builder.build()
        mRetrifit = Retrofit.Builder()
                .client(builder.build())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        mAPIService = mRetrifit!!.create(APIService::class.java)
    }


    fun getAPIService(): APIService {
        return mAPIService!!
    }

    fun getRetrofit(): Retrofit {
        return mRetrifit!!
    }

    fun cancellAll() {
        okHttpClient.dispatcher().cancelAll()
    }

}