package project.upload.services;

import org.jose4j.jwt.JwtClaims;

import project.upload.tools.classes.Credential;

public interface JwtServiceInterface {

//	String getConnectJwt(Credential credential);
	String getConnectReturnToken(Credential credential);
	JwtClaims testJwt(String token);
}
