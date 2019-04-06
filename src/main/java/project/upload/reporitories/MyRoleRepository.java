package project.upload.reporitories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import project.upload.models.MyRole;

@Repository
public interface MyRoleRepository extends JpaRepository<MyRole,Long>{
	
	MyRole findByName(String name);
	
	@Query(value = "select * from my_role mr join my_user_my_roles mm on mr.id =mm.my_roles_id join my_user mu on mm.my_users_id=mu.id where mu.login ilike :paramLogin",
			nativeQuery=true)
	List<MyRole> selectMyRolesFromMyUser(@Param("paramLogin") String login);

	
}
