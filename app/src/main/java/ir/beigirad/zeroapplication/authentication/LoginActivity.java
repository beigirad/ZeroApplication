package ir.beigirad.zeroapplication.authentication;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.yarolegovich.lovelydialog.LovelyProgressDialog;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;
import ir.beigirad.zeroapplication.R;

import butterknife.BindView;
import ir.beigirad.zeroapplication.component.BaseActivity;
import ir.beigirad.zeroapplication.model.LoginInfoModel;
import ir.beigirad.zeroapplication.model.SendCodeRequest;
import ir.beigirad.zeroapplication.model.SendCodeResponse;
import ir.beigirad.zeroapplication.model.SendInfoRequest;
import ir.beigirad.zeroapplication.model.SendInfoResponse;
import ir.beigirad.zeroapplication.model.SendPhoneNumberRequest;
import ir.beigirad.zeroapplication.model.SendPhoneNumberResponse;
import ir.beigirad.zeroapplication.network.APIProvider;
import ir.beigirad.zeroapplication.network.APIService;
import ir.beigirad.zeroapplication.util.SharedPrefUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity implements LoginInterface {


    APIService mApiService;
    FragmentManager mFragmentManager;

    @BindView(R.id.login_container)
    View login_container;

    boolean registered = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {
        super.initVariables();
        mApiService = new APIProvider().getApiService();
        progressDialog = new LovelyProgressDialog(this);
        progressDialog.setMessage("لحظاتی صبر کنید.").setTopColor(getResources().getColor(R.color.colorAccent));
        dialog = new LovelyStandardDialog(this)
                .setTitle("پیام سیستم")
                .setPositiveButton("باشه", null)
                .setTopColor(getResources().getColor(R.color.greenColor));

        mFragmentManager = getSupportFragmentManager();
    }


    @Override
    protected void initUI() {
        super.initUI();
        GetPhoneFragment getPhoneFragment = new GetPhoneFragment();
        mFragmentManager.beginTransaction().replace(R.id.login_container, getPhoneFragment).addToBackStack("GetPhoneFrag").commit();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected CharSequence getToolbarTitle() {
        return "ورود";
    }


    LovelyProgressDialog progressDialog;
    LovelyStandardDialog dialog;

    @Override
    public void onSubmitPhone(final String phone) {

        SendPhoneNumberRequest codeRequet = new SendPhoneNumberRequest(true, phone);
        Call<SendPhoneNumberResponse> sendPhoneNumber = mApiService.sendPhoneNumber(codeRequet);
        progressDialog.show();
        sendPhoneNumber.enqueue(new Callback<SendPhoneNumberResponse>() {
            @Override
            public void onResponse(Call<SendPhoneNumberResponse> call, Response<SendPhoneNumberResponse> response) {
                progressDialog.dismiss();

                if (response.isSuccessful()) {
                    if (response.isSuccessful()) {
                        registered = response.body().isRegistered();
                        gotoGetCode(phone);
                    } else
                        Snackbar.make(login_container, response.body().getMessage(), Snackbar.LENGTH_LONG).show();
                } else
                    Snackbar.make(login_container, "not successful", Snackbar.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<SendPhoneNumberResponse> call, Throwable t) {
                progressDialog.dismiss();

                Snackbar.make(login_container, "FAIL : " + t.getMessage(), Snackbar.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public void onSubmitInfo(final LoginInfoModel loginInfoModel) {
        SendInfoRequest infoRequest = new SendInfoRequest(loginInfoModel.getFamily(), true, loginInfoModel.getName());
        progressDialog.show();

        Call<SendInfoResponse> sendInfoResponseCall = mApiService.sendInfo(infoRequest);
        sendInfoResponseCall.enqueue(new Callback<SendInfoResponse>() {
            @Override
            public void onResponse(Call<SendInfoResponse> call, Response<SendInfoResponse> response) {
                progressDialog.dismiss();

                if (response.isSuccessful()) {
                    if (response.body().isSucceed()) {
                        SendInfoResponse body = response.body();
                        LoginInfoModel login = new LoginInfoModel(body.getFirstName(), body.getLastName(), loginInfoModel.getEmail(), body.getCustomerId(), loginInfoModel.getToken());

                        new SharedPrefUtils(LoginActivity.this).setInfo(login);
                        finish();
                    } else
                        Snackbar.make(login_container, "ناموفق : " + response.body().getMessage(), Snackbar.LENGTH_LONG).show();
                } else
                    Snackbar.make(login_container, "not successful", Snackbar.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<SendInfoResponse> call, Throwable t) {
                Snackbar.make(login_container, "FAIL : " + t.getMessage(), Snackbar.LENGTH_LONG).show();
                progressDialog.dismiss();

            }
        });
    }


    void gotoGetCode(String phone) {
        GetCodeFragment fragment = new GetCodeFragment().newInstance(phone);
        mFragmentManager.beginTransaction().replace(R.id.login_container, fragment).addToBackStack("GetCodeFrag").commit();
    }

    void gotoGetInfo(int userCode) {
        GetInfoFragment fragment = new GetInfoFragment().newInstance(userCode);
        mFragmentManager.beginTransaction().replace(R.id.login_container, fragment).addToBackStack("GetInfoFrag").commit();
    }

    @Override
    public void onSubmitCode(String code, String phone) {
        SendCodeRequest codeRequest = new SendCodeRequest(code, phone);
        Call<SendCodeResponse> sendCodeResponseCall = mApiService.sendCode(codeRequest);
        progressDialog.show();
        sendCodeResponseCall.enqueue(new Callback<SendCodeResponse>() {
            @Override
            public void onResponse(Call<SendCodeResponse> call, Response<SendCodeResponse> response) {
                progressDialog.dismiss();

                if (response.isSuccessful()) {
                    if (response.body().isSucceed()) {
                        new SharedPrefUtils().setUserCode(response.body().getCustomerId());
                        gotoGetInfo(response.body().getCustomerId());
                    } else
                        Snackbar.make(login_container, "کد اشتباه است.", Snackbar.LENGTH_LONG).show();
                } else
                    Snackbar.make(login_container, "not successful", Snackbar.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<SendCodeResponse> call, Throwable t) {
                progressDialog.dismiss();

                Snackbar.make(login_container, "FAIL : " + t.getMessage(), Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
