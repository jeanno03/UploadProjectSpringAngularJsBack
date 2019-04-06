package project.upload.reporitories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import project.upload.models.MyFile;

@Repository
public interface MyFileRepository extends JpaRepository<MyFile,Long>{

	List <MyFile> findByMySpaceNameIgnoreCase(String name);
	
	//méthode qui l'utilise a tété détruit sinon fonctionnel
	@Query(value ="select * from my_file mf join my_space ms on mf.my_space_id=ms.id where ms.name ilike :paramName",
			nativeQuery=true)
	List<MyFile> selectMyFilesFromMySpace(@Param("paramName") String name);
}
