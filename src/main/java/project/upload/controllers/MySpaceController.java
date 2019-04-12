package project.upload.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.upload.dtos.MySpaceDto;
import project.upload.models.MySpace;
import project.upload.repositories.MySpaceRepository;
import project.upload.services.MySpaceServiceInterface;
import project.upload.tools.interfaces.HttpBuilder;
import project.upload.tools.interfaces.MyConstant;

@RestController
@CrossOrigin("*")
public class MySpaceController {

	//inutile si on utilise pas les requete directement dans le controlleur
	private MySpaceRepository mySpaceRepository;

//	@Autowired
//	private HttpBuilder httpService;

	@Autowired
	private MySpaceServiceInterface mySpaceService;

	//inutile si on utilise pas les requete directement dans le controlleur
	public MySpaceController(MySpaceRepository mySpaceRepository) {
		super();
		this.mySpaceRepository = mySpaceRepository;
	}

	//si creation ok va retourner la liste de tous les mySpaces requête native
	//http://localhost:8080/MySpace/createMySpace
	@RequestMapping(produces="application/json", value="MySpace/createMySpace", method=RequestMethod.POST)
	public ResponseEntity<?>createMySpace(@RequestHeader String token, @RequestBody MySpace mySpace){

		List<MySpace> mySpaces = mySpaceService.createMySpaceFromMyUser(token, mySpace);

		if(mySpaces!=null) {
			return new ResponseEntity<>(mySpaces,HttpStatus.OK);
		}
		else {
			HashMap<String,String> httpResponse = HttpBuilder.getHttpResponse(MyConstant.STATUS, MyConstant.UNAUTHORIZED);
			return new ResponseEntity<>(httpResponse, HttpStatus.UNAUTHORIZED);
		}

	}

	//va retourner tous les mySpace en fonction du loggin requete native
	//http://localhost:8080/MySpace/getAllMySpaceJwt
	@RequestMapping(produces="application/json", value="MySpace/getAllMySpaceJwt", method=RequestMethod.GET)
	public ResponseEntity<?>getAllMySpace(@RequestHeader String token) {


		List<MySpace> mySpaces = mySpaceService.getAllMySpaceFromUser(token);

		if(mySpaces!=null) {
			return new ResponseEntity<>(mySpaces,HttpStatus.OK);
		}else {
			HashMap<String,String> httpResponse = HttpBuilder.getHttpResponse(MyConstant.STATUS, MyConstant.FORBIDDEN);
			return new ResponseEntity<>(httpResponse, HttpStatus.FORBIDDEN);
		}

	}

	//va retourner mySpaceDto en fonction de son name
	//http://localhost:8080/MySpace/getMySpaceByName?name=
	@RequestMapping(produces="application/json", value="MySpace/getMySpaceByName", method=RequestMethod.GET)
	public ResponseEntity<?> getMySpaceByName(@RequestHeader String token, String name){

		MySpaceDto mySpaceDto = mySpaceService.getMySpaceDtoByNameJwt(token, name);

		if(mySpaceDto!=null) {
			return new ResponseEntity<>(mySpaceDto,HttpStatus.OK);
		}
		else {
			HashMap<String,String> httpResponse = HttpBuilder.getHttpResponse(MyConstant.STATUS, MyConstant.FORBIDDEN);
			return new ResponseEntity<>(httpResponse, HttpStatus.FORBIDDEN);
		}

	}


}
