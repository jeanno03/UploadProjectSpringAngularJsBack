package project.upload.transformers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import project.upload.dtos.MyFileDto;
import project.upload.models.MyFile;

@Service
public class MyFileTransformer implements MyFileTransformerInterface{

	@Override
	public List<MyFileDto> getMySavedFilesDto(List<MyFile> myFiles){
		
		List<MyFileDto> myFilesDto = new ArrayList();
		
		myFiles.forEach(m->{
			MyFileDto myFileDto = new MyFileDto(m.getId(), m.getName(), m.getPath(), m.getReName(), m.getUploadDate());
			myFilesDto.add(myFileDto);
		});
		
		return myFilesDto;
}
	
	@Override
	public MyFileDto getMyUploadingFileDto(MyFile myFile) {
		MyFileDto myFileDto = new MyFileDto(myFile.getId(), myFile.getName(), myFile.getPath(), myFile.getReName(), myFile.getUploadDate());
				return myFileDto;
	}
	
}
