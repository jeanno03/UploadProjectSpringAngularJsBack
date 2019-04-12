package project.upload.dtos;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

public class MySpaceDto extends SuperDto{

	private String name;
	private String description;
	private Date creation;
	
	private Collection<MyFileDto> myFilesDto;
	
	public MySpaceDto() {
		super();
		myFilesDto = new HashSet();
	}

	public MySpaceDto(Long id, String name, String description, Date creation) {
		super(id);
		this.id = id;
		this.name = name;
		this.description = description;
		this.creation = creation;
	}

	public Long getId() {
		return id;
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

	public Collection<MyFileDto> getMyFilesDto() {
		return myFilesDto;
	}

	public void setMyFilesDto(Collection<MyFileDto> myFilesDto) {
		this.myFilesDto = myFilesDto;
	}

	@Override
	public String toString() {
		return "MySpaceDto [id=" + id + ", name=" + name + "]";
	}
	
}
