package project.upload.services;

import java.io.IOException;

import javax.ws.rs.core.MultivaluedMap;

import project.upload.dtos.MyUserDto;
import project.upload.models.MyUser;
import project.upload.tools.Credential;

public interface TestServiceInterface {
	
	String getDataTest();
//	boolean testConnection(Credential credential, MyUser myUser) throws Exception ;
	MyUserDto getMyUserDtoFullDatas(Credential credential) throws Exception;
	boolean authorizeTest(String bearer);

}
