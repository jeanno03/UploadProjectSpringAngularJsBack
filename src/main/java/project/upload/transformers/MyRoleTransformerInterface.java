package project.upload.transformers;

import java.util.List;

import project.upload.dtos.MyRoleDto;
import project.upload.models.MyRole;

public interface MyRoleTransformerInterface {
	
	List<MyRoleDto> getMyRolesDtoFromMyUserLogin(List<MyRole> myRoles);

}
