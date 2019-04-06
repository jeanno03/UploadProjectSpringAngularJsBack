package project.upload.dtos;

import java.util.Collection;
import java.util.HashSet;

public class MyRoleDto {
	
	private Long id;
	private String name;
	
	private Collection<MyUserDto> myUsersDto;
	
	public MyRoleDto() {
		super();
		myUsersDto = new HashSet();
	}

	public MyRoleDto(Long id, String name) {
		this();
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

	public Collection<MyUserDto> getMyUsersDto() {
		return myUsersDto;
	}

	public void setMyUsersDto(Collection<MyUserDto> myUsersDto) {
		this.myUsersDto = myUsersDto;
	}

	@Override
	public String toString() {
		return "MyRoleDto [id=" + id + ", name=" + name + "]";
	}

}
