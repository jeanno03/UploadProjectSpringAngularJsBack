package project.upload;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import project.upload.singleton.ClassSingleton;


@SpringBootApplication
public class UploadProjectSpringAngularJsBackApplication {
	
	static ClassSingleton classSingleton1 = ClassSingleton.getInstance();
	
	final static Logger logger = Logger.getLogger(UploadProjectSpringAngularJsBackApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(UploadProjectSpringAngularJsBackApplication.class, args);
		String classSingletonStart = classSingleton1.getInfo();
		logger.info(classSingletonStart);
		classSingleton1.generateJsonWebKeys();
		
	}

}
