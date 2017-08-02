package ir.beigirad.zeroapplication.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by farhad-mbp on 7/17/17.
 */

public class APIProvider {

    Retrofit mRetrofit;
    APIService mApiService;

    public APIProvider() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addNetworkInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder builder = original.newBuilder();

                builder.addHeader("user_id", "fucking_user_id");
                builder.addHeader("token", "fucking_user_token");

                return chain.proceed(builder.build());
            }
        });

        client.addNetworkInterceptor(new StethoInterceptor());

        mRetrofit = new Retrofit.Builder()
                .client(client.build())
                .baseUrl("http://beigirad.ir/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApiService = mRetrofit.create(APIService.class);

    }

    public APIService getApiService() {
        return mApiService;
    }

}
