package project.upload.reporitories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.upload.models.MySpace;

@Repository
public interface MySpaceRepository extends JpaRepository<MySpace,Long>{
	
	List<MySpace> findByMyUserLogin(String login);

}
