package project.upload.transformers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import project.upload.dtos.MyFileDto;
import project.upload.dtos.MySpaceDto;
import project.upload.models.MyFile;
import project.upload.models.MySpace;
import project.upload.models.MyUser;

@Service
public class MySpaceTransformer implements MySpaceTransformerInterface{

	//non utilisé
	@Override
	public List<MySpaceDto> getMySpacesDtoFromMyUser(MyUser myUser) {
		List<MySpace> mySpaces = (List<MySpace>) myUser.getMySpaces();
		List<MySpaceDto> mySpacesDto = new ArrayList();

		mySpaces.forEach(m->{
			MySpaceDto mySpaceDto = new MySpaceDto(m.getId(),m.getName(), m.getDescription(), m.getCreation());
			mySpacesDto.add(mySpaceDto);
		});

		return mySpacesDto;
	}

	@Override
	public MySpaceDto getMySpaceDto(MySpace mySpace) {
		
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

}
