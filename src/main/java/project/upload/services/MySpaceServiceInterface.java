package project.upload.services;

import java.util.List;

import project.upload.dtos.MySpaceDto;
import project.upload.models.MySpace;

public interface MySpaceServiceInterface {
	
	List<MySpace> getAllMySpaceFromUser(String token);
	List<MySpace> createMySpaceFromMyUser(String token, MySpace mySpace);
	MySpaceDto getMySpaceDtoByNameJwt(String token, String name);

}
