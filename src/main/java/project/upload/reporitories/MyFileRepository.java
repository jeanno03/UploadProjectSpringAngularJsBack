package project.upload.reporitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.upload.models.MyFile;

@Repository
public interface MyFileRepository extends JpaRepository<MyFile,Long>{

	
}
