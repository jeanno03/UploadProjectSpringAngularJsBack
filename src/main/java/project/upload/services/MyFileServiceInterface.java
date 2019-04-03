package project.upload.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import project.upload.dtos.MyFileDto;
import project.upload.models.MyFile;

public interface MyFileServiceInterface {
	
	List<MyFile> saveMyFile(String login, String name, MultipartFile[] multipartFile);

}
