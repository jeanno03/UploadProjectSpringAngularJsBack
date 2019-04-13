package project.upload.tools.singleton;

import org.apache.log4j.Logger;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.lang.JoseException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import project.upload.UploadProjectSpringAngularJsBackApplication;
import project.upload.tools.interfaces.MyConstant;
import project.upload.tools.interfaces.MyStatic;

//https://www.baeldung.com/java-singleton
public class ClassSingleton {
	
	private static ClassSingleton INSTANCE;
	private String info="ClassSingleton start";
	
	final static Logger logger = Logger.getLogger(ClassSingleton.class);

	private ClassSingleton() {
	}
	
    public static ClassSingleton getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ClassSingleton();
        }
         
        return INSTANCE;
    }

	public static ClassSingleton getINSTANCE() {
		return INSTANCE;
	}

	public static void setINSTANCE(ClassSingleton iNSTANCE) {
		INSTANCE = iNSTANCE;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	public static void generateJsonWebKeys() {

		for(int i=0;i<3;i++) {

			JsonWebKey jsonWebKey=null;

			try {
				int kid=i;

				jsonWebKey=RsaJwkGenerator.generateJwk(2048);
				jsonWebKey.setKeyId(String.valueOf(kid));
				
				MyStatic.jsonWebKeys.add(jsonWebKey);
				logger.info("JsonWebKeys number : " + i + " generate");

			}catch(JoseException ex) {
				ex.printStackTrace();
			}catch(Exception ex) {
				ex.printStackTrace();
			}

		}

	}
	
	public static void loadPropertiesFile(){

		try {
			FileInputStream propFile = new FileInputStream("FileInputStream.properties");
			MyConstant.PROP.load(propFile);
			propFile.close();
			
		} catch (FileNotFoundException ex) {

			logger.error(ex.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
