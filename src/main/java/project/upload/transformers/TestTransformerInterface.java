package project.upload.transformers;

import project.upload.dtos.MyUserDto;
import project.upload.models.MyUser;

public interface TestTransformerInterface {
	
	MyUserDto getMyUserDtoFullDatas(MyUser myUser) ;

}
