package project.upload.services;

import java.util.List;

import project.upload.models.MySpace;

public interface MySpaceServiceInterface {
	
	List<MySpace> createMySpaceFromMyUser(String login, MySpace mySpace);

}
