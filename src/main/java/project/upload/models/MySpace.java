package project.upload.models;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class MySpace {

	private Long id;
	private String name;
	private String description;
	private Date creation;
		
	private MyUser myUser;
	private Collection<MyFile> myFiles;
	
	public MySpace() {
		super();
		myFiles = new HashSet();
	}	
	
	public MySpace(String name, String description, Date creation) {
		super();
		this.name = name;
		this.description = description;
		this.creation = creation;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreation() {
		return creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="my_user_id")
	public MyUser getMyUser() {
		return myUser;
	}

	public void setMyUser(MyUser myUser) {
		this.myUser = myUser;
	}
	
	@OneToMany(mappedBy = "mySpace")
	public Collection<MyFile> getMyFiles() {
		return myFiles;
	}

	public void setMyFiles(Collection<MyFile> myFiles) {
		this.myFiles = myFiles;
	}

	@Override
	public String toString() {
		return "MySpace [id=" + id + ", name=" + name + "]";
	}
	
}
