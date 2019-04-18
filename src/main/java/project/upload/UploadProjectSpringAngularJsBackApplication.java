package project.upload;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.lang.JoseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import project.upload.tools.interfaces.MyConstant;
import project.upload.tools.interfaces.MyStatic;
import project.upload.tools.singleton.ClassSingleton;

//pour déployer sous tomcat extends SpringBootServletInitializer
@SpringBootApplication
public class UploadProjectSpringAngularJsBackApplication extends SpringBootServletInitializer{

	static {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			System.setProperty("current.date.time", dateFormat.format(new Date()));
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

//	static ClassSingleton classSingleton = ClassSingleton.getInstance();
	final static Logger logger = Logger.getLogger(UploadProjectSpringAngularJsBackApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(UploadProjectSpringAngularJsBackApplication.class, args);
//		String classSingletonStart = classSingleton.getInfo();
//
//		logger.info(classSingletonStart);
//		classSingleton.generateJsonWebKeys();
//		classSingleton.loadPropertiesFile();

//		String test = prop.getProperty("test");
//		logger.info("test : " + test);
//		String path = prop.getProperty("path_directory");
//		logger.info("path : " + path);


	}


}
