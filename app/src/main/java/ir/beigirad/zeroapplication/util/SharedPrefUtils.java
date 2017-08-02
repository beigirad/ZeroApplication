package ir.beigirad.zeroapplication.util;

import android.content.Context;
import android.content.SharedPreferences;

import ir.beigirad.zeroapplication.ZeroApplication;
import ir.beigirad.zeroapplication.model.LoginInfoModel;

/**
 * Created by farhad-mbp on 7/26/17.
 */

public class SharedPrefUtils {
    private final String AUTO_LOCATION = "AUTO_LOCATION";
    private final String FBTOKEN = "FBTOKEN";

    private final String NAME = "NAME";
    private final String FAMILY = "FAMILY";
    private final String EMAIL = "EMAIL";
    private final String USER_CODE = "USER_CODE";
    private final String TOKEN = "TOKEN";


    public static final String FBTOKEN_DEFAULT = "FBTOKEN_DEFAULT";


    SharedPreferences mSettingsPreferences;
    SharedPreferences mBasicPreferences;
    Context mContext;

    public SharedPrefUtils(Context mContext) {
        this.mContext = mContext;
        mSettingsPreferences = mContext.getSharedPreferences("SettingPrefs", Context.MODE_PRIVATE);
        mBasicPreferences = mContext.getSharedPreferences("BasicPrefs", Context.MODE_PRIVATE);
    }

    public SharedPrefUtils() {
        this.mContext = ZeroApplication.getAppContext();
        mSettingsPreferences = mContext.getSharedPreferences("SettingPrefs", Context.MODE_PRIVATE);
        mBasicPreferences = mContext.getSharedPreferences("BasicPrefs", Context.MODE_PRIVATE);
    }

    public String getFBToken() {
        return mBasicPreferences.getString(FBTOKEN, FBTOKEN_DEFAULT);
    }

    public void setFBToken(String token) {
        mBasicPreferences.edit().putString(FBTOKEN, token).commit();
    }


    public void setAutoLocation(boolean enabled) {
        mSettingsPreferences.edit().putBoolean(AUTO_LOCATION, enabled).commit();
    }

    public boolean getAutoLocation() {
        return mSettingsPreferences.getBoolean(AUTO_LOCATION, false);
    }


    public void setInfo(LoginInfoModel loginInfoModel) {
        SharedPreferences.Editor edit = mBasicPreferences.edit();
        edit.putString(NAME, loginInfoModel.getName());
        edit.putString(FAMILY, loginInfoModel.getFamily());
        edit.putString(EMAIL, loginInfoModel.getEmail());
        edit.putString(TOKEN, loginInfoModel.getToken());
        edit.putInt(USER_CODE, loginInfoModel.getUserCode());

        edit.commit();
    }

    public LoginInfoModel getInfo() {

        LoginInfoModel loginInfoModel = new LoginInfoModel(
                mBasicPreferences.getString(NAME, ""),
                mBasicPreferences.getString(FAMILY, ""),
                mBasicPreferences.getString(EMAIL, ""),
                mBasicPreferences.getInt(USER_CODE, -1),
                mBasicPreferences.getString(TOKEN, ""));

        return loginInfoModel;
    }

    public void setUserCode(int userCode){
        mBasicPreferences.edit().putInt(USER_CODE,userCode).commit();
    }
    public int getUserCode(){
        return mBasicPreferences.getInt(USER_CODE,-1);
    }
}
