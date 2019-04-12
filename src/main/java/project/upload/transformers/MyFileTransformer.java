package project.upload.transformers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import project.upload.dtos.MyFileDto;
import project.upload.models.MyFile;
import project.upload.repositories.MyFileRepository;

@Service
@Qualifier("my-file")
public class MyFileTransformer implements TransformerInterface {

	@Autowired
	MyFileRepository myFileRepository;

	//récupère List<MyListDto> en fonction de MySpaceId
	@Override
	public List<MyFileDto> getMyListDto(String mySpaceId) {
		Long id = Long.valueOf(mySpaceId);
		
		//6 je récupère les fichiers sauvegardé par mySpace.id
		List<MyFile> myFiles = myFileRepository.selectMyFilesByMySpaceId(Long.valueOf(id));
		
		List<MyFileDto> myFilesDto = new ArrayList();
		
		myFiles.forEach(m->{
			MyFileDto myFileDto = new MyFileDto(m.getId(), m.getName(), m.getPath(), m.getReName(), m.getUploadDate());
			myFilesDto.add(myFileDto);
		});
		
		return myFilesDto;
	}

	@Override
	public MyFileDto getSimpleDto(String myFileId) {
		Long id = Long.valueOf(myFileId);
		MyFile myFile = myFileRepository.selectMyFileById(id);
		MyFileDto myFileDto = new MyFileDto(myFile.getId(), myFile.getName(), myFile.getPath(), myFile.getReName(), myFile.getUploadDate());
				return myFileDto;
	}
	
}
