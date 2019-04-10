package project.upload.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import project.upload.dtos.MyUserDto;
import project.upload.models.MySpace;
import project.upload.models.MyUser;
import project.upload.reporitories.MySpaceRepository;
import project.upload.reporitories.MyUserRepository;
import project.upload.services.JwtServiceInterface;
import project.upload.services.TestServiceInterface;
import project.upload.tools.Credential;
import project.upload.tools.MyConstant;
import project.upload.tools.UploadFormTest;

@RestController
@CrossOrigin("*")
public class TestController {
	
	private MyUserRepository myUserRepository;
	
	private MySpaceRepository mySpaceRepository;
	
	@Autowired
	private TestServiceInterface testService;
	
	@Autowired
	private JwtServiceInterface jwtService;

	public TestController(MyUserRepository myUserRepository, MySpaceRepository mySpaceRepository) {
		super();
		this.myUserRepository = myUserRepository;
		this.mySpaceRepository = mySpaceRepository;
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

	//http://localhost:8080/Test/uploadingPost
	//https://o7planning.org/fr/11673/le-exemple-de-upload-file-avec-spring-boot-rest-et-angularjs
    @RequestMapping(value = "/Test/uploadingPost", method = RequestMethod.POST)
    public String uploadingPost(@ModelAttribute UploadFormTest form) throws IOException{
		String uploadingDir = MyConstant.PATH_DIRECTORY;
		
		System.out.println("API /Test/uploadingPost");
		System.out.println("description : " + form.getDescription());
		
        for(MultipartFile uploadedFile : form.getFiles()) {
            File file = new File(uploadingDir + uploadedFile.getOriginalFilename());
            uploadedFile.transferTo(file);
        }
		
		return "success";
	}
    
	//http://localhost:8080/Test/getTestCustomQueryMySpace
    @RequestMapping(value="/Test/getTestCustomQueryMySpace", method=RequestMethod.GET) 
    public List<MySpace> getTestCustomQueryMySpace(){
    	List<MySpace> mySpaces = mySpaceRepository.selectMySpaceFromMyUser("dartagnan");
    	return mySpaces;
    }
    
    //http://localhost:8080/Test/download
    @RequestMapping(path = "/Test/download", method = RequestMethod.GET)
    public ResponseEntity<Resource> download(String param) throws IOException {

        // ...
    	File file = new File("/home/jeanno/UploadProject/my_user_2_my_space_9_my_file_18.pdf");
    	file.renameTo(file);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
   
        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
    
    //http://localhost:8080/Test/download2?image=Albator-007
    @RequestMapping(path = "/Test/download2", method = RequestMethod.GET)
    public ResponseEntity<Resource> download2(@RequestParam("image") String image) throws IOException {
    	
        String EXTENSION = ".jpeg";
        String SERVER_LOCATION = "/home/jeanno/UploadProject";
        
        
        File file = new File(SERVER_LOCATION + File.separator + image + EXTENSION);

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+image+EXTENSION);
//        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=img.jpg");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
	

}
