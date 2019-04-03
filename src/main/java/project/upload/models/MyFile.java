package project.upload.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class MyFile {
	
	private Long id;
	private String name;
	private String path;
	private String reName;
	private Date uploadDate;
	
	private MySpace mySpace;
	
	public MyFile() {
		super();
	}

//	public MyFile(String name, String path, String reName) {
//		super();
//		this.name = name;
//		this.path = path;
//		this.reName = reName;
//	}
	
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public MyFile(String name, String path, String reName, Date uploadDate) {
		super();
		this.name = name;
		this.path = path;
		this.reName = reName;
		this.uploadDate = uploadDate;
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getReName() {
		return reName;
	}

	public void setReName(String reName) {
		this.reName = reName;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="my_space_id")
	public MySpace getMySpace() {
		return mySpace;
	}

	public void setMySpace(MySpace mySpace) {
		this.mySpace = mySpace;
	}

	@Override
	public String toString() {
		return "MyFile [id=" + id + ", name=" + name + ", path=" + path + ", reName=" + reName + ", uploadDate="
				+ uploadDate + "]";
	}

}
