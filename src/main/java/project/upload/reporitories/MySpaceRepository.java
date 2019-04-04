package project.upload.reporitories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import project.upload.models.MySpace;

@Repository
public interface MySpaceRepository extends JpaRepository<MySpace,Long>{
	
	List<MySpace> findByMyUserLogin(String login);
	MySpace findByName(String name);
	
	@Query(value = "select * from my_space ms join my_user mu on  ms.my_user_id=mu.id where mu.login ilike :paramLogin",
			nativeQuery=true)
	List<MySpace> selectMySpaceFromMyUser(@Param("paramLogin") String login);

}
