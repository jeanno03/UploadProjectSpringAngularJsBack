package project.upload.transformers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import project.upload.dtos.MySpaceDto;
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
			MySpaceDto mySpaceDto = new MySpaceDto(m.getId(),m.getName());
			mySpacesDto.add(mySpaceDto);
		});
		
		return mySpacesDto;
	}

}
