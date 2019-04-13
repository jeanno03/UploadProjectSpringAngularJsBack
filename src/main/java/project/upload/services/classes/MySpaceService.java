package project.upload.services.classes;

import java.util.Date;
import java.util.List;

import org.jose4j.jwt.JwtClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import project.upload.dtos.MySpaceDto;
import project.upload.models.MySpace;
import project.upload.models.MyUser;
import project.upload.repositories.MySpaceRepository;
import project.upload.repositories.MyUserRepository;
import project.upload.services.interfaces.MySpaceServiceInterface;
import project.upload.transformers.classes.MySpaceTransformer;
import project.upload.transformers.interfaces.TransformerInterface;

@Service
public class MySpaceService implements MySpaceServiceInterface{

	@Autowired
	private MySpaceRepository mySpaceRepository;

	@Autowired
	private MyUserRepository myUserRepository;

	@Autowired
	private JwtService jwtService;

	@Autowired
	@Qualifier("my-space")
	private TransformerInterface transformerInterface;

	@Override
	public List<MySpaceDto> getAllMySpaceFromUser(String token){

		List<MySpaceDto> mySpacesDto = null;

		try {

			JwtClaims jwtClaims = jwtService.testJwt(token)	;

			String myUserLogin = jwtClaims.getSubject();

			mySpacesDto = transformerInterface.getMyListDto(myUserLogin);

		}catch(Exception ex) {

			ex.printStackTrace();
		}

		return mySpacesDto;
	}

	@Override
	public List<MySpaceDto> createMySpaceFromMyUser(String token, MySpace mySpace){

		List<MySpaceDto> mySpacesDto = null;

		try {

			JwtClaims jwtClaims = jwtService.testJwt(token)	;

			Date myDate = new Date();

			String myUserLogin = jwtClaims.getSubject();

			MyUser myUser = myUserRepository.findByLoginIgnoreCase(myUserLogin);

			MySpace mySpaceToSave = new MySpace(mySpace.getName(), mySpace.getDescription(), myDate);

			mySpaceToSave.setMyUser(myUser);

			mySpaceRepository.save(mySpaceToSave);

			mySpacesDto = transformerInterface.getMyListDto(myUserLogin);

		}catch(Exception ex) {

			ex.printStackTrace();
		}

		return mySpacesDto;
	}

	@Override
	public MySpaceDto getMySpaceDtoByNameJwt(String token, String mySpaceName) {

		MySpaceDto mySpaceDto = null;

		try {
			//si ok on continue
			JwtClaims jwtClaims = jwtService.testJwt(token);

			mySpaceDto = (MySpaceDto) transformerInterface.getSimpleDto(mySpaceName);

		}catch(Exception ex) {
			ex.printStackTrace();
		}

		return mySpaceDto;
	}

}
