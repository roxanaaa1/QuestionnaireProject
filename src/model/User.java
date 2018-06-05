package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idusers;

	private int adminRights;

	private String email;

	private String firstName;

	private String lastName;

	private String password;

	private String username;

	//bi-directional many-to-one association to Userquestionnaire
	@OneToMany(mappedBy="user")
	private List<Userquestionnaire> userquestionnaires;

	public User() {
	}

	public Integer getIdusers() {
		return this.idusers;
	}

	public void setIdusers(Integer idusers) {
		this.idusers = idusers;
	}

	public int getAdminRights() {
		return this.adminRights;
	}

	public void setAdminRights(int adminRights) {
		this.adminRights = adminRights;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Userquestionnaire> getUserquestionnaires() {
		return this.userquestionnaires;
	}

	public void setUserquestionnaires(List<Userquestionnaire> userquestionnaires) {
		this.userquestionnaires = userquestionnaires;
	}

	public Userquestionnaire addUserquestionnaire(Userquestionnaire userquestionnaire) {
		getUserquestionnaires().add(userquestionnaire);
		userquestionnaire.setUser(this);

		return userquestionnaire;
	}

	public Userquestionnaire removeUserquestionnaire(Userquestionnaire userquestionnaire) {
		getUserquestionnaires().remove(userquestionnaire);
		userquestionnaire.setUser(null);

		return userquestionnaire;
	}

	@Override
	public String toString() {
		return String.valueOf(idusers);
	}
	
	@Override
	public boolean equals(Object obj) {
		return ((User)obj).idusers == idusers;
		
	}

}