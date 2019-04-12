package project.upload.transformers;

import java.util.List;

import project.upload.dtos.MyFileDto;
import project.upload.dtos.MyRoleDto;
import project.upload.dtos.SuperDto;


public interface TransformerInterface <T extends SuperDto> {
	
	//get dto from string (id or login)
	T getSimpleDto(String input);
	
	//get List of dto from string (id or login)
	List<T> getMyListDto(String input);

}
