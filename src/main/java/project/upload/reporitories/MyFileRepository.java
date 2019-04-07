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

	//méthode qui l'utilise a été détruite sinon fonctionnelle ==> problème==> pas d'unicité de mySpace.Name
	@Query(value = "select * from my_file mf join my_space ms on mf.my_space_id=ms.id where ms.name ilike :paramName",
			nativeQuery=true)
	List<MyFile> selectMyFilesFromMySpace(@Param("paramName") String name);

	//va rechercher le dernier fichier enregistré par l'utilisateur
	@Query(value = "select * from my_file mf join my_space ms on mf.my_space_id = ms.id join my_user mu on ms.my_user_id = mu.id "
			+ "where mu.login ilike :paramLogin order by mf.upload_date desc limit 1",
			nativeQuery=true)
	MyFile selectMyLastFileFromMyUserLogin(@Param("paramLogin") String login);

	//va chercher la list des fichiers en fonction de l'id de mySpace
	@Query(value = "select * from my_file mf where mf.my_space_id = :paramId ", nativeQuery = true)
	List<MyFile> selectMyFilesByMySpaceId(@Param("paramId") Long id);
}
