package ir.beigirad.zeroapplication.model;

import com.google.gson.annotations.SerializedName;

public class SendInfoRequest{

	@SerializedName("last_name")
	private String lastName;

	@SerializedName("update")
	private boolean update;

	public SendInfoRequest(String lastName, boolean update, String firstName) {
		this.lastName = lastName;
		this.update = update;
		this.firstName = firstName;
	}

	@SerializedName("first_name")
	private String firstName;

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setUpdate(boolean update){
		this.update = update;
	}

	public boolean isUpdate(){
		return update;
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
			"SendInfoRequest{" + 
			"last_name = '" + lastName + '\'' + 
			",update = '" + update + '\'' + 
			",first_name = '" + firstName + '\'' + 
			"}";
		}
}