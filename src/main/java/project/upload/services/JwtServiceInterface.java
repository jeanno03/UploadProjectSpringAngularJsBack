package project.upload.services;

import org.jose4j.jwt.JwtClaims;

import project.upload.tools.Credential;

public interface JwtServiceInterface {

	String getConnectJwt(Credential credential);
	JwtClaims testJwt(String token);
}
