package project.upload.controllers;

import java.util.HashMap;
import java.util.List;

import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.upload.models.MySpace;
import project.upload.reporitories.MySpaceRepository;
import project.upload.services.HttpServiceInterface;
import project.upload.services.JwtServiceInterface;
import project.upload.services.MySpaceServiceInterface;
import project.upload.tools.MyConstant;
import project.upload.transformers.MySpaceTransformerInterface;

@RestController
@CrossOrigin("*")
public class MySpaceController {
	
	private MySpaceRepository mySpaceRepository;
	
	@Autowired
	private JwtServiceInterface jwtService;
	
	@Autowired
	private HttpServiceInterface httpService;
	
	@Autowired
	private MySpaceTransformerInterface mySpaceTransformer;
	
	@Autowired
	private MySpaceServiceInterface mySpaceService;

	public MySpaceController(MySpaceRepository mySpaceRepository) {
		super();
		this.mySpaceRepository = mySpaceRepository;
	}
	
	//va retourner la liste de tous les mySpaces
	//http://localhost:8080/MySpace/createMySpace
	@RequestMapping(produces="application/json", value="MySpace/createMySpace", method=RequestMethod.POST)
	public ResponseEntity<?>createMySpace(@RequestHeader String token, @RequestBody String mySpaceName){
	
		try {
			JwtClaims jwtClaims = jwtService.testJwt(token)	;		
			
			String login = jwtClaims.getSubject();
			List<MySpace> mySpaces = mySpaceService.createMySpaceFromMyUser(login, mySpaceName);
			
			return new ResponseEntity<>(mySpaces,HttpStatus.OK);

		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		HashMap<String,String> httpResponse = httpService.getHttpResponse(MyConstant.STATUS, MyConstant.UNAUTHORIZED);
		return new ResponseEntity<>(httpResponse, HttpStatus.UNAUTHORIZED);
	}
	
	

}
