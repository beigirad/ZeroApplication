package ir.beigirad.zeroapplication.authentication;

import ir.beigirad.zeroapplication.model.LoginInfoModel;

/**
 * Created by farhad-mbp on 8/2/17.
 */

public interface LoginInterface {
    public void onSubmitCode(String code, String phone);
    public void onSubmitPhone(String phone);
    public void onSubmitInfo(LoginInfoModel loginInfoModel);
}
