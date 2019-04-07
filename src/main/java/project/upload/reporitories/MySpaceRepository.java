package project.upload.reporitories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import project.upload.models.MySpace;

@Repository
public interface MySpaceRepository extends JpaRepository<MySpace,Long>{
	
	List<MySpace> findByMyUserLogin(String login);
	MySpace findByNameIgnoreCase(String name);
	
	@Query(value = "select * from my_space ms join my_user mu on  ms.my_user_id=mu.id where mu.login ilike :paramLogin",
			nativeQuery=true)
	List<MySpace> selectMySpaceFromMyUser(@Param("paramLogin") String login);
	
	@Query(value = "select * from my_space ms where ms.id = :paramId", nativeQuery=true)
	MySpace selectMySpaceById(@Param("paramId") Long id);
	
	Optional<MySpace> findById(Long id);
}
