package project.upload.singleton;

import org.apache.log4j.Logger;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.lang.JoseException;

import project.upload.UploadProjectSpringAngularJsBackApplication;
import project.upload.tools.MyStatic;

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


}
