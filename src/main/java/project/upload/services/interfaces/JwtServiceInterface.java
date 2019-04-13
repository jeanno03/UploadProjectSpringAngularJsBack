package project.upload.services.interfaces;

import org.jose4j.jwt.JwtClaims;

import project.upload.tools.classes.Credential;

public interface JwtServiceInterface {

	String getConnectReturnToken(Credential credential);
	JwtClaims testJwt(String token);
}
