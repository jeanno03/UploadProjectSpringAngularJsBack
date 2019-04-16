package project.upload.tools.interfaces;

import java.util.Properties;

public interface MyConstant {
	
	static final Properties PROP = new Properties();
	static final String TOKEN = "token";
	static final String DOMAIN = "https://myuploadapp.com";
	static final String ROLES = "roles";
	static final String STATUS = "status";
//	static final String PATH_DIRECTORY ="/home/jeanno/UploadProject/";
	static final String PATH_DIRECTORY = MyConstant.PROP.getProperty("path_directory");
	
	static final String UNAUTHORIZED = "401-unauthorized";
	static final String FORBIDDEN ="403-forbidden";

}
