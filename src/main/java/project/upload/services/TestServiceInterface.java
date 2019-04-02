package project.upload.services;

import project.upload.dtos.MyUserDto;
import project.upload.tools.Credential;

public interface TestServiceInterface {
	
	String getDataTest();
	MyUserDto getMyUserDtoFullDatas(Credential credential) throws Exception;
	boolean authorizeTest(String bearer);

}
