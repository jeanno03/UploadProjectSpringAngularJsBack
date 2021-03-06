package project.upload.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import project.upload.dtos.MyFileDto;
import project.upload.services.interfaces.MyFileServiceInterface;
import project.upload.tools.interfaces.HttpBuilder;
import project.upload.tools.interfaces.MyConstant;

@RestController
@CrossOrigin("*")
public class MyFileController {

	//Inutile si pas utilisation de myFileRepository dans controller
	//	private MyFileRepository myFileRepository;

	@Autowired
	private MyFileServiceInterface myFileService;
	
	final static Logger logger = Logger.getLogger(MyFileController.class);

	//Inutile si pas utilisation de myFileRepository dans controller
	//	public MyFileController(MyFileRepository myFileRepository) {
	//		super();
	//		this.myFileRepository = myFileRepository;
	//	}

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
			HashMap<String,String> httpResponse = HttpBuilder.getHttpResponse(MyConstant.STATUS, MyConstant.FORBIDDEN);
			return new ResponseEntity<>(httpResponse, HttpStatus.FORBIDDEN);
		}

	}

	//http://localhost:8080/MyFile/downloadingMyFile?fichier= myFileId
	//https://stackoverflow.com/questions/35680932/download-a-file-from-spring-boot-rest-service  
	@RequestMapping(path = "/MyFile/downloadingMyFile", method = RequestMethod.GET)
	public ResponseEntity<Resource> downloadingMyFile(@RequestHeader String token, @RequestParam("fichier") Long id) throws IOException{

		MyFileDto myFileDto = myFileService.getDownLoadingMyFileDto(token, id);

		File file = new File(myFileDto.getPath());

		HttpHeaders header = new HttpHeaders();
		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + myFileDto.getName());
		header.add("Cache-Control", "no-cache, no-store, must-revalidate");
		header.add("Pragma", "no-cache");
		header.add("Expires", "0");

		Path path = Paths.get(file.getAbsolutePath());
		
		Resource resource = null;
		try {
		resource = new UrlResource(path.toUri());
		}catch(MalformedURLException ex) {
			logger.error("MalformedURLException : " + ex);
		}
		return ResponseEntity.status(HttpStatus.OK)
				.headers(header)
				.contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.body(resource);
	}
	
	//méthode fonctionnel mais pas utilisé car la taille des fichiers est limitée
	//conversion du multipartfile en byte...
	//http://localhost:8080/MyFile/downloadingMyFile2?fichier= myFileId
	@RequestMapping(path = "/MyFile/downloadingMyFile2", method = RequestMethod.GET)
	public ResponseEntity<Resource> downloadingMyFile2(@RequestHeader String token, @RequestParam("fichier") Long id) throws IOException{

		MyFileDto myFileDto = myFileService.getDownLoadingMyFileDto(token, id);

		File file = new File(myFileDto.getPath());

		HttpHeaders header = new HttpHeaders();
		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + myFileDto.getName());
		header.add("Cache-Control", "no-cache, no-store, must-revalidate");
		header.add("Pragma", "no-cache");
		header.add("Expires", "0");

		Path path = Paths.get(file.getAbsolutePath());
		ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

		return ResponseEntity.status(HttpStatus.OK)
				.headers(header)
				.contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.body(resource);
	}

}
