package project.upload.services;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import project.upload.dtos.MyFileDto;
import project.upload.models.MyFile;
import project.upload.models.MySpace;
import project.upload.models.MyUser;
import project.upload.reporitories.MyFileRepository;
import project.upload.reporitories.MySpaceRepository;
import project.upload.reporitories.MyUserRepository;
import project.upload.tools.MyConstant;

@Service
public class MyFileService implements MyFileServiceInterface{

//@Autowired
//private MyUserRepository myUserRepository;

@Autowired
private MyFileRepository myFileRepository;

@Autowired
private MySpaceRepository mySpaceRepository;

	@Override
	public List<MyFile> saveMyFile(String login, String name, MultipartFile[] multipartFile) {
		
		MySpace mySpace =null;
		List<MyFile> myFiles = null;
		try {
			
			//save to disk
            File file = new File(MyConstant.PATH_DIRECTORY + multipartFile[1].getOriginalFilename());
            multipartFile[1].transferTo(file);

			//save to disk
//            for(MultipartFile uploadedFile : multipartFile) {
//                File file = new File(MyConstant.PATH_DIRECTORY + uploadedFile.getOriginalFilename());
//                uploadedFile.transferTo(file);
//            }
            

	        //save to bdd
            mySpace = mySpaceRepository.findByNameIgnoreCase(name);
			
			Date date = new Date();
			MyFile myFile = new MyFile(multipartFile[0].getOriginalFilename(), mySpace.getName() +""+login, login+"001",date);
			myFile.setMySpace(mySpace);
			
			myFileRepository.save(myFile);
			
			//return myFile
			myFiles = myFileRepository.findByMySpaceNameIgnoreCase(name);
			
			 
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return myFiles;
		
	}

}
