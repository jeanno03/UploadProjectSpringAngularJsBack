package project.upload.controllers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.jose4j.jwt.JwtClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import project.upload.dtos.MyFileDto;
import project.upload.models.MyFile;
import project.upload.reporitories.MyFileRepository;
import project.upload.services.HttpServiceInterface;
import project.upload.services.JwtServiceInterface;
import project.upload.services.MyFileServiceInterface;
import project.upload.tools.MyConstant;
import project.upload.tools.UploadFormTest;

@RestController
@CrossOrigin("*")
public class MyFileController {
	
	private MyFileRepository myFileRepository;
	
	@Autowired
	private MyFileServiceInterface myFileService;
	
	@Autowired
	private HttpServiceInterface httpService;

	public MyFileController(MyFileRepository myFileRepository) {
		super();
		this.myFileRepository = myFileRepository;
	}
	

	//http://localhost:8080/MyFile/uploadingMyFilesPut?id= mySpaceId
	//https://o7planning.org/fr/11673/le-exemple-de-upload-file-avec-spring-boot-rest-et-angularjs
	//Important a bien utiliser ce nom pour le post client ==> multipartFile
	//voir https://hellokoding.com/uploading-multiple-files-example-with-spring-boot/
    @RequestMapping(value = "/MyFile/uploadingMyFilesPut", method = RequestMethod.PUT)
    public ResponseEntity<?> uploadingMyFilesPut(@RequestHeader String token, @RequestParam("multipartFiles") MultipartFile[] multipartFiles, Long id) throws IOException{
    	
		List<MyFileDto> myFilesDto=  myFileService.saveMyFiles(token, id, multipartFiles);
		
		if(myFilesDto!=null) {
			return new ResponseEntity<>(myFilesDto,HttpStatus.OK);
		}else {
			HashMap<String,String> httpResponse = httpService.getHttpResponse(MyConstant.STATUS, MyConstant.FORBIDDEN);
			return new ResponseEntity<>(httpResponse, HttpStatus.FORBIDDEN);
		}

	}


}
