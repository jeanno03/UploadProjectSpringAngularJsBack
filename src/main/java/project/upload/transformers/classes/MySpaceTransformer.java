package project.upload.transformers.classes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import project.upload.dtos.MyFileDto;
import project.upload.dtos.MySpaceDto;
import project.upload.models.MyFile;
import project.upload.models.MySpace;
import project.upload.repositories.MySpaceRepository;
import project.upload.transformers.interfaces.TransformerInterface;

@Service
@Qualifier("my-space")
public class MySpaceTransformer implements TransformerInterface{
	
	@Autowired
	MySpaceRepository mySpaceRepository;

	@Override
	public MySpaceDto getSimpleDto(String mySpaceName) {
		
		//on utilise query de springboot car mySpace ne comporte pas bcp de dependance
		MySpace mySpace = mySpaceRepository.findByNameIgnoreCase(mySpaceName);
		
		MySpaceDto mySpaceDto = null;
		
		try {
			mySpaceDto = new MySpaceDto(mySpace.getId(),mySpace.getName(),mySpace.getDescription(),mySpace.getCreation());	

			List<MyFile> myFiles = (List<MyFile>) mySpace.getMyFiles();
			List<MyFileDto> myFilesDto = new ArrayList();
			myFiles.forEach(m->{		
				MyFileDto myFileDto = new MyFileDto(m.getId(),m.getName(),m.getPath(),m.getReName(),m.getUploadDate());
				myFilesDto.add(myFileDto);
			});
			
			mySpaceDto.setMyFilesDto(myFilesDto);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}

		return mySpaceDto;
	}

	@Override
	public List<MySpaceDto> getMyListDto(String myUserLogin) {
		
		List<MySpace> mySpaces = mySpaceRepository.selectMySpaceFromMyUser(myUserLogin);
		
		List<MySpaceDto> mySpacesDto = new ArrayList();
		
		mySpaces.forEach(m->{
			MySpaceDto mySpaceDto = new MySpaceDto(m.getId(), m.getName(), m.getDescription(), m.getCreation());
			mySpacesDto.add(mySpaceDto);
		});
		
		return mySpacesDto;
	}

}
