package ir.beigirad.zeroapplication.network;

import ir.beigirad.zeroapplication.model.SendCodeRequest;
import ir.beigirad.zeroapplication.model.SendCodeResponse;
import ir.beigirad.zeroapplication.model.SendInfoRequest;
import ir.beigirad.zeroapplication.model.SendInfoResponse;
import ir.beigirad.zeroapplication.model.SendPhoneNumberRequest;
import ir.beigirad.zeroapplication.model.SendPhoneNumberResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by farhad-mbp on 7/17/17.
 */

public interface APIService {


    @POST("ideh-app-peyk60-login-customer/data")
    Call<SendPhoneNumberResponse> sendPhoneNumber(@Body SendPhoneNumberRequest phoneNumberRequest);

    @POST("ideh-app-peyk60-login-customer/data")
    Call<SendCodeResponse> sendCode(@Body SendCodeRequest codeRequest);

    @POST("ideh-app-peyk60-login-customer/data")
    Call<SendInfoResponse> sendInfo(@Body SendInfoRequest infoRequest);





}
