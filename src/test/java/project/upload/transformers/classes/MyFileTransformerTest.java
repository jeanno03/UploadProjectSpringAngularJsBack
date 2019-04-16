package project.upload.transformers.classes;
//
//import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


import project.upload.dtos.MyFileDto;
import project.upload.repositories.MyFileRepository;
import project.upload.transformers.interfaces.TransformerInterface;

class MyFileTransformerTest {
	
	@Autowired
	@Qualifier("my-file")
	TransformerInterface transformerInterface;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("Test MyFileTransformerTest start");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("Test MyFileTransformerTest stop");
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetMyListDto() {
		String idNull =null;
		Throwable exceptionNull1 = assertThrows(NullPointerException.class, () -> transformerInterface.getMyListDto(idNull));

		String idNotLong = "huit";
		Throwable exceptionNul2 = assertThrows(NullPointerException.class, () -> transformerInterface.getMyListDto(idNotLong));

	}

	@Test
	void testGetSimpleDto() {
		String idNull =null;
		Throwable exceptionNull1 = assertThrows(NullPointerException.class, () -> transformerInterface.getSimpleDto(idNull));

		String idNotLong = "huit";
		Throwable exceptionNull2 = assertThrows(NullPointerException.class, () -> transformerInterface.getSimpleDto(idNotLong));
	}

}
