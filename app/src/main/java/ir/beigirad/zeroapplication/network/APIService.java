package ir.beigirad.zeroapplication.network;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by farhad-mbp on 7/17/17.
 */

public interface APIService {

    @POST("...")
    Call<ResponseBody> sendPhoneNumber(@Body RequestBody phoneNumberRequest);

    @POST("...")
    Call<ResponseBody> sendCode(@Body RequestBody codeRequest);

    @POST("...")
    Call<ResponseBody> sendInfo(@Body RequestBody infoRequest);





}
