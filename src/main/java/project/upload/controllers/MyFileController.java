package project.upload.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import project.upload.reporitories.MyFileRepository;

@RestController
@CrossOrigin("*")
public class MyFileController {
	
	private MyFileRepository myFileRepository;

//	public MyFileController(MyFileRepository myFileRepository) {
//		super();
//		this.myFileRepository = myFileRepository;
//	}
	
	

}
