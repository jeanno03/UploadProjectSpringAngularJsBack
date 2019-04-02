package project.upload.dtos;

import java.util.Collection;
import java.util.HashSet;

public class MySpaceDto {

	private Long id;
	private String name;
	
	private Collection<MyFileDto> myFilesDto;
	
	public MySpaceDto() {
		super();
		myFilesDto = new HashSet();
	}
	
	public MySpaceDto(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
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
