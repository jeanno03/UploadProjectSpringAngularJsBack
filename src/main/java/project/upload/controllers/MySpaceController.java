package project.upload.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import project.upload.reporitories.MySpaceRepository;

@RestController
@CrossOrigin("*")
public class MySpaceController {
	
	private MySpaceRepository mySpaceRepository;

//	public MySpaceController(MySpaceRepository mySpaceRepository) {
//		super();
//		this.mySpaceRepository = mySpaceRepository;
//	}
	
	

}
