package ir.beigirad.zeroapplication.model;

import com.google.gson.annotations.SerializedName;

public class SendCodeRequest{

	@SerializedName("sec_code")
	private String secCode;

	@SerializedName("username")
	private String username;

	public SendCodeRequest(String secCode, String username) {
		this.secCode = secCode;
		this.username = username;
	}

	public void setSecCode(String secCode){
		this.secCode = secCode;
	}

	public String getSecCode(){
		return secCode;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"SendCodeRequest{" + 
			"sec_code = '" + secCode + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}