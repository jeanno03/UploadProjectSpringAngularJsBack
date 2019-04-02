package project.upload.reporitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.upload.models.MyRole;

@Repository
public interface MyRoleRepository extends JpaRepository<MyRole,Long>{

	MyRole findByName(String name);
}
