package project.upload.controllers;


import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.upload.services.interfaces.JwtServiceInterface;
import project.upload.tools.classes.Credential;
import project.upload.tools.interfaces.HttpBuilder;
import project.upload.tools.interfaces.MyConstant;

//inactif spring-boot-starter-data-rest ==> service rest de spring annulé
//http://localhost:8080/myUsers
@RestController
@CrossOrigin("*")
public class MyUserController {
	
	//inutile 
//	private MyUserRepository myUserRepository;
	
	@Autowired
	private JwtServiceInterface jwtService;
	
//	@Autowired
//	private HttpBuilder httpService;
	
	final static Logger logger = Logger.getLogger(MyUserController.class);
	
	//constructeur inutile
	//a utiliser seulement si on utilise myUserRepository
//	public MyUserController(MyUserRepository myUserRepository) {
//		super();
//		this.myUserRepository = myUserRepository;
//	}
//		
	//si connection ok return token
	//http://localhost:8080/MyUser/getConnectJwt
	@RequestMapping(value="MyUser/getConnectJwt",method=RequestMethod.POST)
	public ResponseEntity<?> getConnectJwt(@RequestBody Credential credential) throws Exception {
		
		logger.info("Api : MyUser/getConnectJwt");
		
//		String jwt = jwtService.getConnectJwt(credential);
		String jwt = jwtService.getConnectReturnToken(credential);
		if(jwt==null) {
			HashMap<String, String> httpResponse = HttpBuilder.getHttpResponse(MyConstant.STATUS, MyConstant.UNAUTHORIZED);
			return new ResponseEntity<>(httpResponse, HttpStatus.UNAUTHORIZED);
		}
		else {
		
			HashMap<String, String> httpResponse = HttpBuilder.getHttpResponse(MyConstant.TOKEN, jwt);
			return new ResponseEntity<>(httpResponse, HttpStatus.OK);

		}
	}
	
}
