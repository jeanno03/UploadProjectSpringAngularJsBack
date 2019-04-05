package project.upload.transformers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import project.upload.dtos.MyFileDto;
import project.upload.dtos.MyRoleDto;
import project.upload.dtos.MySpaceDto;
import project.upload.dtos.MyUserDto;
import project.upload.models.MyFile;
import project.upload.models.MyRole;
import project.upload.models.MySpace;
import project.upload.models.MyUser;

@Service
public class MyUserTransformer implements MyUserTransformerInterface{

	//get all the informations of connected user
	public MyUserDto getMyUserDtoFullDatas(MyUser myUser) {
		
		MyUserDto myUserDto = new MyUserDto(myUser.getId(), myUser.getLogin(), myUser.getEmail());
		try {
			List<MyRole> myRoles = (List<MyRole>) myUser.getMyRoles();
			List<MyRoleDto> myRolesDto = new ArrayList();

			myRoles.forEach(m->{
				MyRoleDto myRoleDto = new MyRoleDto (m.getId(), m.getName());
				myRolesDto.add(myRoleDto);
			});		
			
			List<MySpace> mySpaces = (List<MySpace>) myUser.getMySpaces();
			List<MySpaceDto> mySpacesDto = new ArrayList();
			
			mySpaces.forEach(m->{
				
				MySpaceDto mySpaceDto = new MySpaceDto(m.getId(),m.getName(), m.getDescription(), m.getCreation());
				
				List<MyFile> myFiles = (List<MyFile>) m.getMyFiles();
				List<MyFileDto> myFilesDto = new ArrayList();
				
				myFiles.forEach(m2->{
					MyFileDto myFileDto = new MyFileDto(m2.getId(),m2.getName(),m2.getPath(),m2.getReName(),m2.getUploadDate());
					myFilesDto.add(myFileDto);
					
				});
				
				mySpaceDto.setMyFilesDto(myFilesDto);
				
				mySpacesDto.add(mySpaceDto);
			});
			
			myUserDto.setMyRolesDto(myRolesDto);
			myUserDto.setMySpacesDto(mySpacesDto);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return myUserDto;
	}
	
	//on va juste retourner MyUserDto avec les infos pour le jwt
	@Override
	public MyUserDto getJwtConnect(MyUser myUser) {
		MyUserDto myUserDto = new MyUserDto(myUser.getId(),myUser.getLogin(),myUser.getEmail());
		try {
			List<MyRole> myRoles = (List<MyRole>)myUser.getMyRoles();
			List <MyRoleDto> myRoleDtos = new ArrayList() ;
			myRoles.forEach(m->{
				MyRoleDto myRoleDto = new MyRoleDto(m.getId(),m.getName());
				myRoleDtos.add(myRoleDto);
			});
			myUserDto.setMyRolesDto(myRoleDtos);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return myUserDto;
	}
	
	//on va juste retourner MyUserDto avec les infos pour le mySpace
	@Override
	public MyUserDto getMyUserMySpaceJwt(MyUser myUser) {
		MyUserDto myUserDto = new MyUserDto(myUser.getId(),myUser.getLogin(),myUser.getEmail());
		try {
			List<MySpace> mySpaces = (List<MySpace>)myUser.getMySpaces();
			List <MySpaceDto> mySpacesDto = new ArrayList() ;
			mySpaces.forEach(m->{
				MySpaceDto mySpaceDto = new MySpaceDto(m.getId(),m.getName(), m.getDescription(), m.getCreation());
				
				List<MyFile> myFiles = (List<MyFile>) m.getMyFiles();
				List<MyFileDto> myFilesDto = new ArrayList();
				
					myFiles.forEach(m2->{
					MyFileDto myFileDto = new MyFileDto(m2.getId(),m2.getName(),m2.getPath(),m2.getReName(),m2.getUploadDate());
					myFilesDto.add(myFileDto);
					});
					mySpaceDto.setMyFilesDto(myFilesDto);				

				mySpacesDto.add(mySpaceDto);
			});
			myUserDto.setMySpacesDto(mySpacesDto);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return myUserDto;
	}
	
	
	

}
