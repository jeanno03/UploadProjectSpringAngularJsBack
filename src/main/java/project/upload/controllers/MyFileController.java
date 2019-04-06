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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	private JwtServiceInterface jwtService;
	
	@Autowired
	private HttpServiceInterface httpService;

	public MyFileController(MyFileRepository myFileRepository) {
		super();
		this.myFileRepository = myFileRepository;
	}
	
	//http://localhost:8080/MyFile/uploadingPost
	//https://o7planning.org/fr/11673/le-exemple-de-upload-file-avec-spring-boot-rest-et-angularjs
	//Important a bien utiliser ce nom pour le post client ==> multipartFile
    @RequestMapping(value = "/MyFile/uploadingPost", method = RequestMethod.POST)
    public String uploadingPost(@ModelAttribute MultipartFile[] multipartFile) throws IOException{
		String uploadingDir = MyConstant.PATH_DIRECTORY;

		List<MyFile> myFiles =  myFileService.saveMyFile("Albator", "Personnel", multipartFile);

		return "success";
	}

	
	

}
