package ir.beigirad.zeroapplication.model;

import com.google.gson.annotations.SerializedName;

public class SendInfoResponse{

	@SerializedName("succeed")
	private boolean succeed;

	@SerializedName("last_name")
	private String lastName;

	@SerializedName("upadate")
	private boolean upadate;

	@SerializedName("customer_id")
	private int customerId;

	@SerializedName("first_name")
	private String firstName;

	@SerializedName("message")
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setSucceed(boolean succeed){
		this.succeed = succeed;
	}

	public boolean isSucceed(){
		return succeed;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setUpadate(boolean upadate){
		this.upadate = upadate;
	}

	public boolean isUpadate(){
		return upadate;
	}

	public void setCustomerId(int customerId){
		this.customerId = customerId;
	}

	public int getCustomerId(){
		return customerId;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	@Override
 	public String toString(){
		return 
			"SendInfoResponse{" + 
			"succeed = '" + succeed + '\'' + 
			",last_name = '" + lastName + '\'' + 
			",upadate = '" + upadate + '\'' + 
			",customer_id = '" + customerId + '\'' + 
			",first_name = '" + firstName + '\'' + 
			"}";
		}
}