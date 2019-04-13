package project.upload.services.interfaces;

import java.util.List;

import project.upload.dtos.MySpaceDto;
import project.upload.models.MySpace;

public interface MySpaceServiceInterface {
	
	List<MySpaceDto> getAllMySpaceFromUser(String token);
	List<MySpaceDto> createMySpaceFromMyUser(String token, MySpace mySpace);
	MySpaceDto getMySpaceDtoByNameJwt(String token, String name);

}
