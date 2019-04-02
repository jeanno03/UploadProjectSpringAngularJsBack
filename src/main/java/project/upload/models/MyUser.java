package project.upload.models;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = "login"),@UniqueConstraint(columnNames = "email")})
public class MyUser {

	private Long id;
	private String login;
	private String password;
	private String email;

	private Collection<MySpace> mySpaces;
	private Collection<MyRole> myRoles;

	public MyUser() {
		super();
		mySpaces = new HashSet();
		myRoles = new HashSet();
	}

	public MyUser(String login, String password, String email) {
		this();
		this.login = login;
		this.password = password;
		this.email = email;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	@Transient
	public Long getTheId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@OneToMany(mappedBy="myUser",cascade = CascadeType.ALL)
	public Collection<MySpace> getMySpaces() {
		return mySpaces;
	}

	public void setMySpaces(Collection<MySpace> mySpaces) {
		this.mySpaces = mySpaces;
	}

	@ManyToMany
	public Collection<MyRole> getMyRoles() {
		return myRoles;
	}

	public void setMyRoles(Collection<MyRole> myRoles) {
		this.myRoles = myRoles;
	}

	@Override
	public String toString() {
		return "MyUser [id=" + id + ", login=" + login + ", password=" + password + ", email=" + email + "]";
	}

}
