package project.upload.transformers;

import java.util.List;

import project.upload.dtos.MySpaceDto;
import project.upload.models.MySpace;
import project.upload.models.MyUser;

public interface MySpaceTransformerInterface {

	//only name of list
	List<MySpaceDto> getMySpacesDtoFromMyUser(MyUser myUser);
	MySpaceDto getMySpaceDto(MySpace mySpace);	
	
}
