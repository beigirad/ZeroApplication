package ir.beigirad.zeroapplication.model;

import com.google.gson.annotations.SerializedName;

public class SendPhoneNumberResponse {

	@SerializedName("succeed")
	private boolean succeed;

	@SerializedName("message")
	private String message;

	@SerializedName("registered")
	private boolean registered;

	public void setSucceed(boolean succeed){
		this.succeed = succeed;
	}

	public boolean isSucceed(){
		return succeed;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}


	public boolean isRegistered() {
		return registered;
	}

	public void setRegistered(boolean registered) {
		this.registered = registered;
	}

	@Override
	public String toString() {
		return "SendPhoneNumberResponse{" +
				"succeed=" + succeed +
				", message='" + message + '\'' +
				", registered=" + registered +
				'}';
	}
}