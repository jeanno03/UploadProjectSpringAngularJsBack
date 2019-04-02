package project.upload.services;

import org.jose4j.jwt.JwtClaims;

import project.upload.dtos.MyUserDto;
import project.upload.models.MyUser;
import project.upload.tools.Credential;

public interface MyUserServiceInterface {

//	String getDataTest();
	boolean testConnection(Credential credential, MyUser myUser) throws Exception ;
//	MyUserDto getMyUserDtoFullDatas(Credential credential) throws Exception;
	MyUserDto getJwtConnect(Credential credential) throws Exception;
	MyUserDto getMyUserMySpaceJwt(JwtClaims jwtClaims) ;
}
