package project.upload.dtos;

import java.util.Collection;
import java.util.HashSet;

public class MyUserDto {

	private Long id;
	private String login;
	private String email;
	
	private Collection<MyRoleDto> myRolesDto;
	private Collection<MySpaceDto> mySpacesDto;
	
	public MyUserDto() {
		super();
		myRolesDto = new HashSet();
		mySpacesDto = new HashSet();
	}

	public MyUserDto(Long id, String login, String email) {
		super();
		this.id = id;
		this.login = login;
		this.email = email;
	}

	public Long getId() {
		return id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<MyRoleDto> getMyRolesDto() {
		return myRolesDto;
	}

	public void setMyRolesDto(Collection<MyRoleDto> myRolesDto) {
		this.myRolesDto = myRolesDto;
	}

	public Collection<MySpaceDto> getMySpacesDto() {
		return mySpacesDto;
	}

	public void setMySpacesDto(Collection<MySpaceDto> mySpacesDto) {
		this.mySpacesDto = mySpacesDto;
	}

	@Override
	public String toString() {
		return "MyUserDto [id=" + id + ", login=" + login + ", email=" + email + "]";
	}

	
}
