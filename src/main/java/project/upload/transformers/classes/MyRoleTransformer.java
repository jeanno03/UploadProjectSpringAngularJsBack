package project.upload.transformers.classes;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import project.upload.dtos.MyRoleDto;
import project.upload.models.MyRole;
import project.upload.repositories.MyRoleRepository;
import project.upload.transformers.interfaces.TransformerInterface;

@Service
@Qualifier("my-role")
public class MyRoleTransformer implements TransformerInterface{

	@Autowired
	MyRoleRepository myRoleRepository;

	@Override
	public MyRoleDto getSimpleDto(String input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MyRoleDto> getMyListDto(String login) {		
		List<MyRoleDto> myRolesDto = new ArrayList();	
		//requete native
		List<MyRole> myRoles = myRoleRepository.selectMyRolesFromMyUser(login);

		myRoles.forEach(m->{
			MyRoleDto myRoleDto = new MyRoleDto(m.getId(),m.getName());
			myRolesDto.add(myRoleDto);
		});
		return myRolesDto;
	}

}
