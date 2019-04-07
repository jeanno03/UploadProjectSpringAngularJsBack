package project.upload.transformers;

import java.util.List;

import project.upload.dtos.MyFileDto;
import project.upload.models.MyFile;

public interface MyFileTransformerInterface {

	List<MyFileDto> getMySavedFilesDto(List<MyFile> myFiles);
	
}
