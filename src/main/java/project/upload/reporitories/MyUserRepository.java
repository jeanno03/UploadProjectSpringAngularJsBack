package project.upload.reporitories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import project.upload.models.MyUser;
import java.lang.Long;
import java.util.Optional;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser,Long>{
	
	Optional<MyUser> findById(Long id);
	MyUser findByLogin(String login);

}
