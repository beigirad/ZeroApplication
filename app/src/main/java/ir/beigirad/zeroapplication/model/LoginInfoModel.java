package ir.beigirad.zeroapplication.model;

/**
 * Created by farhad-mbp on 8/2/17.
 */

public class LoginInfoModel {
    private String name;
    private String family;
    private String email;
    private int userCode;
    private String token;

    public LoginInfoModel(String name, String family, String email, int userCode, String token) {
        this.name = name;
        this.family = family;
        this.email = email;
        this.userCode = userCode;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
