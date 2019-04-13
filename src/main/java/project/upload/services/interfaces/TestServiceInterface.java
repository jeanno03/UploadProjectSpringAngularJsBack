package project.upload.services.interfaces;

import project.upload.dtos.MyUserDto;
import project.upload.tools.classes.Credential;

public interface TestServiceInterface {
	
	String getDataTest();
	MyUserDto getMyUserDtoFullDatas(Credential credential) throws Exception;
	boolean authorizeTest(String bearer);

}
