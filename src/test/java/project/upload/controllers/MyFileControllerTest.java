package project.upload.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import project.upload.dtos.MyFileDto;
import project.upload.repositories.MyFileRepository;
import project.upload.services.interfaces.MyFileServiceInterface;

import static org.mockito.Mockito.when;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class MyFileControllerTest {
	
//    @MockBean
//    private MyFileRepository mockRepository;
//    
//    @MockBean
//    private MyFileServiceInterface myFileService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("MyFileControllerTest start ");
		Date date1 = new Date();

		List<MyFileDto> myFilesDto = new ArrayList() {
			MyFileDto myFileDto1 = new MyFileDto(1L, "name1", "path1", "reName", date1);
			MyFileDto myFileDto2 = new MyFileDto(2L, "name2", "path2", "reName", date1);
		};
	

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("MyFileControllerTest end ");
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testUploadingMyFilesPut() {
//		fail("Not yet implemented");
	}

	@Test
	void testDownloadingMyFile() {
//		fail("Not yet implemented");
	}

}
