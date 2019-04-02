package project.upload.services;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;
import org.hibernate.exception.ConstraintViolationException;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;

import project.upload.dtos.MyUserDto;
import project.upload.models.MyFile;
import project.upload.models.MyRole;
import project.upload.models.MySpace;
import project.upload.models.MyUser;
import project.upload.reporitories.MyFileRepository;
import project.upload.reporitories.MyRoleRepository;
import project.upload.reporitories.MySpaceRepository;
import project.upload.reporitories.MyUserRepository;
import project.upload.tools.Credential;
import project.upload.transformers.MyUserTransformerInterface;

@Service
public class MyUserService implements MyUserServiceInterface{
	
	@Autowired
	private MyUserRepository myUserRepository;

	@Autowired
	private MyUserTransformerInterface myUserTransformer;
	
	@Override
	public boolean testConnection(Credential credential, MyUser myUser) throws Exception {
		
		Boolean test = false;		
		String credentialSha3 = getStringSha3(credential.getPassword());
		
		try {
		
			if(credentialSha3.equals(myUser.getPassword())) {
				test = true;
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return test;	
	}
	
	@Override
	public MyUserDto getJwtConnect(Credential credential) throws Exception{
		MyUserDto myUserDto = null;
		try {
			MyUser myUser = myUserRepository.findByLogin(credential.getLogin());
			if(testConnection(credential, myUser)) {
				myUserDto = myUserTransformer.getJwtConnect(myUser);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return myUserDto;
	}
	
	@Override
	public MyUserDto getMyUserMySpaceJwt(JwtClaims jwtClaims) {
		String login;
		MyUserDto myUserDto = null;
		try {
			login = jwtClaims.getSubject();
			MyUser myUser = myUserRepository.findByLogin(login);
			myUserDto = myUserTransformer.getMyUserMySpaceJwt(myUser);
			
		} catch (MalformedClaimException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception ex) {
			ex.printStackTrace();
		}

		return myUserDto;
	}

	//return password hashé
	private String getStringSha3(String password) throws Exception { 
	    SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512(); 
	    byte[] digest = digestSHA3.digest(password.getBytes()); 
	    return Hex.toHexString(digest);
	}



}
