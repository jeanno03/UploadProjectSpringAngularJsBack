package project.upload.transformers;

import project.upload.dtos.MyUserDto;
import project.upload.models.MyUser;

public interface MyUserTransformerInterface {
	
	MyUserDto getMyUserDtoFullDatas(MyUser myUser) ;
	MyUserDto getJwtConnect(MyUser myUser);
	MyUserDto getMyUserMySpaceJwt(MyUser myUser);

}
