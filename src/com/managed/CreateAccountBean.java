package com.managed;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import model.User;

@Named
@RequestScoped
public class CreateAccountBean {

	User user;
	String errorMessage;
	String retypePassword;
	String succesfullMessage;
	
	public CreateAccountBean() {

	}
	
	public String getRetypePassword() {
		return retypePassword;
	}

	public void setRetypePassword(String retypePassword) {
		this.retypePassword = retypePassword;
	}

	public String getSuccesfullMessage() {
		return succesfullMessage;
	}

	public void setSuccesfullMessage(String succesfullMessage) {
		this.succesfullMessage = succesfullMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String createAccount() {
//		if (ConnectionsManager.uniqueUsername(user.getUsername())) {
//			errorMessage = "Username is already used";
//		}
//		else {
//		user.setAdminRights(0);
//		ConnectionsManager.createUser(user);
//		}
		return "createaccount";
	}
	
}
