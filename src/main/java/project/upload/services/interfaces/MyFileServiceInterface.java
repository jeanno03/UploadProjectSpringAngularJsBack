package project.upload.services.interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import project.upload.dtos.MyFileDto;
import project.upload.models.MyFile;

public interface MyFileServiceInterface {
	
	List<MyFileDto> saveMyFiles(String token,Long id,MultipartFile[] multipartFile);
	MyFileDto getDownLoadingMyFileDto(String token, Long id);

}
