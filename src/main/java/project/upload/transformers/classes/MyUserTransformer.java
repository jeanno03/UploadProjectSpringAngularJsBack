package project.upload.transformers.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import project.upload.dtos.MyUserDto;
import project.upload.transformers.interfaces.TransformerInterface;

@Service
@Qualifier("my-user")
public class MyUserTransformer implements TransformerInterface{

	@Override
	public MyUserDto getSimpleDto(String input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MyUserDto> getMyListDto(String input) {
		// TODO Auto-generated method stub
		return null;
	}

}
