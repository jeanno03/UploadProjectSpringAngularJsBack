package project.upload.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.upload.dtos.MyUserDto;
import project.upload.models.MyUser;
import project.upload.reporitories.MyUserRepository;
import project.upload.services.JwtServiceInterface;
import project.upload.services.MyUserServiceInterface;
import project.upload.services.TestServiceInterface;
import project.upload.tools.Credential;
import project.upload.tools.MyConstant;

@RestController
@CrossOrigin("*")
public class TestController {
	
	private MyUserRepository myUserRepository;

	@Autowired
	private MyUserServiceInterface myUserService;
	
	@Autowired
	private TestServiceInterface testService;
	
	@Autowired
	private JwtServiceInterface jwtService;

	public TestController(MyUserRepository myUserRepository) {
		super();
		this.myUserRepository = myUserRepository;
	}
	
	//http://localhost:8080/Test/getDataTest
	@RequestMapping(value="Test/getDataTest", method= RequestMethod.GET)
	public String getDataTest(@RequestHeader String bearer) {
		if(testService.authorizeTest(bearer)) {
			String result = testService.getDataTest();
			return "{\"return\":\""+result+"\"}";
		}	
		
		return "{\"return\":\""+MyConstant.FORBIDDEN+"\"}";
	}
	
	//http://localhost:8080/Test/getShowDataTest
	@RequestMapping(value="Test/getShowDataTest", method= RequestMethod.GET)
	public Optional<MyUser> getShowDataTest(@RequestHeader String bearer) {
		return myUserRepository.findById(1L);
	}
	
	//http://localhost:8080/Test/getAllUsersTest
	@RequestMapping(value="Test/getAllUsersTest", method=RequestMethod.GET)
	public List<MyUser> getAllUsersTest(@RequestHeader String bearer){
		return myUserRepository.findAll();
	}
	
	// getMyUserDtoFullDatas => non adapté
	//http://localhost:8080/Test/getConnect
	@RequestMapping(value="Test/getConnect",method=RequestMethod.POST)
	public ResponseEntity<?> getConnect(@RequestBody Credential credential) throws Exception {
		
		MyUserDto myUserDto = testService.getMyUserDtoFullDatas(credential);
		if(myUserDto==null) {
			return new ResponseEntity<String>("{\"return\":\"Unauthorized\"}", HttpStatus.UNAUTHORIZED);
		}
		else {
			return new ResponseEntity<>(myUserDto, HttpStatus.OK);
		}
	}
	

}
