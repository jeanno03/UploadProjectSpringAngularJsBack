package project.upload.reporitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import project.upload.models.MySpace;
import project.upload.models.MyUser;
import java.lang.Long;
import java.util.List;
import java.util.Optional;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser,Long>{
	
	Optional<MyUser> findById(Long id);
	MyUser findByLoginIgnoreCase(String login);
	
	
	@Query(value = "select * from my_user mu where mu.login ilike :paramLogin",
			nativeQuery=true)
	MyUser selectMyUserByLogin(@Param("paramLogin") String login);

}
