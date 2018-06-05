package com.managed;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedProperty;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.User;

@Named
@ApplicationScoped
public class LoginBean {

	@PersistenceContext
	EntityManager em;

	@ManagedProperty(value = "username")
	private String username;
	
	@ManagedProperty(value = "password")
	private String password;
	
	@ManagedProperty(value = "errorMessage")
	private String errorMessage;
	
	@ManagedProperty(value = "user")
	private User user;

	public LoginBean() {
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login() {
		
		errorMessage = "";
		
		TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
		List<User> list = (List<User>) query.getResultList(); //lambda

		Optional<User> users =  list.stream().filter(p -> p.getUsername().equals(username)).findAny();
		
		user = null;
		try{
			user = users.get();
		}
		catch(NoSuchElementException e) {
			errorMessage =  "Username does not exist";
		}
		if(user == null)
		{
			errorMessage = "Couldn't connect";
			return null;
		}
		if(!user.getPassword().equals(password))
			errorMessage =  "Wrong password";

		if ((errorMessage != null) && (errorMessage.length() > 0))
			return "index";

		return user.getAdminRights() == 0 ? "user":"admin";
	}

	public String createAccount() {
		return "createaccount";
	}
}
