package project.upload.services;

import java.util.Date;
import java.util.List;

import org.jose4j.jwt.JwtClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.upload.dtos.MySpaceDto;
import project.upload.models.MySpace;
import project.upload.models.MyUser;
import project.upload.repositories.MySpaceRepository;
import project.upload.repositories.MyUserRepository;
import project.upload.transformers.MySpaceTransformer;

@Service
public class MySpaceService implements MySpaceServiceInterface{

	@Autowired
	private MySpaceRepository mySpaceRepository;
	
	@Autowired
	private MyUserRepository myUserRepository;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private MySpaceTransformer mySpaceTransformer;
	
	@Override
	public List<MySpace> getAllMySpaceFromUser(String token){
		
		List<MySpace> mySpaces = null;
		
		try {
			
			JwtClaims jwtClaims = jwtService.testJwt(token)	;
			
			String login = jwtClaims.getSubject();
			
			MyUser myUser = myUserRepository.findByLoginIgnoreCase(login);

			mySpaces = mySpaceRepository.selectMySpaceFromMyUser(login);

		}catch(Exception ex) {

			ex.printStackTrace();
		}
		
		return mySpaces;
	}
	
	@Override
	public List<MySpace> createMySpaceFromMyUser(String token, MySpace mySpace){
		
		List<MySpace> mySpaces = null;
		
		try {
			
			JwtClaims jwtClaims = jwtService.testJwt(token)	;

			Date myDate = new Date();
			
			String login = jwtClaims.getSubject();
			
			MyUser myUser = myUserRepository.findByLoginIgnoreCase(login);

			MySpace mySpaceToSave = new MySpace(mySpace.getName(), mySpace.getDescription(), myDate);

			mySpaceToSave.setMyUser(myUser);

			mySpaceRepository.save(mySpaceToSave);

			mySpaces = mySpaceRepository.selectMySpaceFromMyUser(login);

		}catch(Exception ex) {

			ex.printStackTrace();
		}
		
		return mySpaces;
	}
	
	@Override
	public MySpaceDto getMySpaceDtoByNameJwt(String token, String name) {
		
		MySpaceDto mySpaceDto = null;
		
		try {
			//si ok on continue
			JwtClaims jwtClaims = jwtService.testJwt(token);
			
			//on utilise query de springboot car mySpace ne comporte pas bcp de dependance
			MySpace mySpace = mySpaceRepository.findByNameIgnoreCase(name);
			
			mySpaceDto = mySpaceTransformer.getMySpaceDto(mySpace);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return mySpaceDto;
	}

}
