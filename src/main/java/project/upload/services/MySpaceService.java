package project.upload.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.upload.models.MySpace;
import project.upload.models.MyUser;
import project.upload.reporitories.MySpaceRepository;
import project.upload.reporitories.MyUserRepository;

@Service
public class MySpaceService implements MySpaceServiceInterface{

	@Autowired
	private MySpaceRepository mySpaceRepository;
	
	@Autowired
	private MyUserRepository myUserRepository;

	public List<MySpace> createMySpaceFromMyUser(String login, String name){
		MyUser myUser =null;
		try {

			myUser = myUserRepository.findByLoginIgnoreCase(login);

			MySpace mySpace = new MySpace(name);

			mySpace.setMyUser(myUser);

			mySpaceRepository.save(mySpace);

			List<MySpace> mySpaces = mySpaceRepository.findByMyUserLogin(login);

			return mySpaces;

		}catch(Exception ex) {

			ex.printStackTrace();
		}
		return null;

	}

}
