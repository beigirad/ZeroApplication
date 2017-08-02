package ir.beigirad.zeroapplication.model;

import com.google.gson.annotations.SerializedName;

public class SendPhoneNumberRequest {

	@SerializedName("send")
	private boolean send;

	@SerializedName("username")
	private String username;

	public SendPhoneNumberRequest(boolean send, String username) {
		this.send = send;
		this.username = username;
	}

	public void setSend(boolean send){
		this.send = send;
	}

	public boolean isSend(){
		return send;
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
			"SendPhoneNumberRequest{" +
			"send = '" + send + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}