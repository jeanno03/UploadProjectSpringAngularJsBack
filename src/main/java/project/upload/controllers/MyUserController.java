package project.upload.controllers;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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

import javassist.Modifier;
import project.upload.dtos.MyUserDto;
import project.upload.models.MyUser;
import project.upload.reporitories.MyUserRepository;
import project.upload.services.HttpServiceInterface;
import project.upload.services.JwtServiceInterface;
import project.upload.services.MyUserService;
import project.upload.services.MyUserServiceInterface;
import project.upload.tools.Credential;
import project.upload.tools.MyConstant;
import project.upload.transformers.MyUserTransformer;

//http://localhost:8080/myUsers
@RestController
@CrossOrigin("*")
public class MyUserController {
	
	private MyUserRepository myUserRepository;

	@Autowired
	private MyUserServiceInterface myUserService;
	
	@Autowired
	private JwtServiceInterface jwtService;
	
	@Autowired
	private HttpServiceInterface httpService;
	
//	public MyUserController(MyUserRepository myUserRepository) {
//		super();
//		this.myUserRepository = myUserRepository;
//	}
//	
	
	//si connection ok return token
	//http://localhost:8080/MyUser/getConnectJwt
	@RequestMapping(value="MyUser/getConnectJwt",method=RequestMethod.POST)
	public ResponseEntity<?> getConnectJwt(@RequestBody Credential credential) throws Exception {
		
		String jwt = jwtService.getConnectJwt(credential);
		if(jwt==null) {
			HashMap<String, String> httpResponse = httpService.getHttpResponse(MyConstant.STATUS, MyConstant.UNAUTHORIZED);
			return new ResponseEntity<>(httpResponse, HttpStatus.UNAUTHORIZED);
		}
		else {
		
			HashMap<String, String> httpResponse = httpService.getHttpResponse(MyConstant.TOKEN, jwt);
			return new ResponseEntity<>(httpResponse, HttpStatus.OK);

		}
	}
	
	//méthod consommant trop de ressource point de vue requete sql // a ne pas utiliser
	//si token ok ==> return myUser datas of mySpace
	//http://localhost:8080/MyUser/getMyUserMySpaceJwt
	@RequestMapping(produces ="application/json", value="MyUser/getMyUserMySpaceJwt", method=RequestMethod.GET)
	public ResponseEntity<?>getMyUserMySpaceJwt(@RequestHeader String token){
		
		try {
			
		JwtClaims jwtClaims = jwtService.testJwt(token);
		//méthod consommant trop de ressource point de vue requete sql // a ne pas utiliser
		MyUserDto myUserDto = myUserService.getMyUserMySpaceJwt(jwtClaims);
		System.out.println("jwtClaims.getSubject() : " + jwtClaims.getSubject());
		if(myUserDto!=null) {
			return new ResponseEntity<>(myUserDto,HttpStatus.OK);
		}
		
		} catch (MalformedClaimException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		HashMap<String, String> httpResponse = httpService.getHttpResponse(MyConstant.STATUS, MyConstant.FORBIDDEN);
		return new ResponseEntity<>(httpResponse, HttpStatus.FORBIDDEN);
		
	}
	

}
