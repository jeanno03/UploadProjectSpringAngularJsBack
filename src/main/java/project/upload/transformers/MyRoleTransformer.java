package project.upload.transformers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import project.upload.dtos.MyRoleDto;
import project.upload.models.MyRole;

@Service
public class MyRoleTransformer implements MyRoleTransformerInterface{

	@Override
	public List<MyRoleDto> getMyRolesDtoFromMyUserLogin(List<MyRole> myRoles) {
		
		List<MyRoleDto> myRolesDto = new ArrayList();

		myRoles.forEach(m->{
			MyRoleDto myRoleDto = new MyRoleDto(m.getId(),m.getName());
			myRolesDto.add(myRoleDto);
		});

		return myRolesDto;

	}

}
