package project.upload.dtos;

import java.util.Date;

public class MyFileDto extends SuperDto{
	

	private String name;
	private String path;
	private String reName;
	private Date uploadDate;
	
	public MyFileDto() {
		super();
	}

	public MyFileDto(Long id, String name, String path, String reName, Date uploadDate) {
		super(id);
		this.name = name;
		this.path = path;
		this.reName = reName;
		this.uploadDate = uploadDate;
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

	@Override
	public String toString() {
		return "MyFileDto [id=" + id + "name=" + name + ", path=" + path + ", reName=" + reName + ", uploadDate=" + uploadDate+ "]";
	}


}
