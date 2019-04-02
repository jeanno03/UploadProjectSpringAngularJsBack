package project.upload.models;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class MyRole {
	
	private Long id;
	private String name;
	
	private Collection <MyUser> myUsers;
	
	public MyRole() {
		super();
		myUsers = new HashSet();
	}

	public MyRole(String name) {
		this();
		this.name = name;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonIgnore
	@ManyToMany(mappedBy="myRoles", cascade = CascadeType.ALL)
	public Collection<MyUser> getMyUsers() {
		return myUsers;
	}

	public void setMyUsers(Collection<MyUser> myUsers) {
		this.myUsers = myUsers;
	}

	@Override
	public String toString() {
		return "MyRole [id=" + id + ", name=" + name + "]";
	}


}
