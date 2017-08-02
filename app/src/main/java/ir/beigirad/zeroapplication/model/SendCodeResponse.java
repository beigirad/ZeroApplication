package ir.beigirad.zeroapplication.model;

import com.google.gson.annotations.SerializedName;

public class SendCodeResponse{

	@SerializedName("role")
	private String role;

	@SerializedName("succeed")
	private boolean succeed;

	@SerializedName("customer_id")
	private int customerId;

	public void setRole(String role){
		this.role = role;
	}

	public String getRole(){
		return role;
	}

	public void setSucceed(boolean succeed){
		this.succeed = succeed;
	}

	public boolean isSucceed(){
		return succeed;
	}

	public void setCustomerId(int customerId){
		this.customerId = customerId;
	}

	public int getCustomerId(){
		return customerId;
	}

	@Override
 	public String toString(){
		return 
			"SendCodeResponse{" + 
			"role = '" + role + '\'' + 
			",succeed = '" + succeed + '\'' + 
			",customer_id = '" + customerId + '\'' + 
			"}";
		}
}