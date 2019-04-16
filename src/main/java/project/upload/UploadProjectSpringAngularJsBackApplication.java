package project.upload;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import project.upload.tools.interfaces.MyConstant;
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


	static ClassSingleton classSingleton = ClassSingleton.getInstance();

	final static Logger logger = Logger.getLogger(UploadProjectSpringAngularJsBackApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(UploadProjectSpringAngularJsBackApplication.class, args);
		String classSingletonStart = classSingleton.getInfo();

		logger.info(classSingletonStart);
		classSingleton.generateJsonWebKeys();
		classSingleton.loadPropertiesFile();

		String test = MyConstant.PROP.getProperty("test");
		logger.info(test);



	}

}
