package project.upload.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.JsonWebKeySet;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import project.upload.tools.Credential;
import project.upload.tools.MyConstant;
import project.upload.tools.MyStatic;
import project.upload.dtos.MyRoleDto;
import project.upload.dtos.MyUserDto;
import project.upload.services.MyUserService;
import project.upload.services.MyUserServiceInterface;

@Service
public class JwtService implements JwtServiceInterface{
	
	@Autowired
	MyUserServiceInterface myUserService;
	
	//MyStatics.jsonWebKeys est la list qui va controller les jwt
	//se lance au démarrage de l'application
	static {
	
		for(int i=0;i<3;i++) {
			
			JsonWebKey jsonWebKey=null;
			
			try {
				int kid=i;

				jsonWebKey=RsaJwkGenerator.generateJwk(2048);
				jsonWebKey.setKeyId(String.valueOf(kid));
				System.out.println(i);
				MyStatic.jsonWebKeys.add(jsonWebKey);
				
			}catch(JoseException ex) {
				ex.printStackTrace();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	@Override
	public String getConnectJwt(Credential credential){
		String jwt = null;
		try {
			MyUserDto myUserDto = myUserService.getJwtConnect(credential);
			
			List<MyRoleDto> myRolesDto = (List<MyRoleDto>) myUserDto.getMyRolesDto();
			List<String>rolesString=new ArrayList();
			myRolesDto.forEach(m->{
				rolesString.add(m.getName());
			});
			int kidRandom = generateRandmoKid();
			
			RsaJsonWebKey rsaJsonWebKey = (RsaJsonWebKey) MyStatic.jsonWebKeys.get(kidRandom);
//			rsaJsonWebKey.setKeyId(String.valueOf(kidRandom));
			
			JwtClaims jwtClaims = new JwtClaims();
			// Create the Claims, which will be the content of the JWT
			//émetteur
			jwtClaims.setIssuer(MyConstant.DOMAIN);
			jwtClaims.setExpirationTimeMinutesInTheFuture(10);
			jwtClaims.setGeneratedJwtId();
			jwtClaims.setIssuedAtToNow();
			jwtClaims.setNotBeforeMinutesInThePast(2);
			jwtClaims.setSubject(myUserDto.getLogin());
			jwtClaims.setStringListClaim(MyConstant.ROLES, rolesString);

			JsonWebSignature jsonWebSignature = new JsonWebSignature();
			jsonWebSignature.setPayload(jwtClaims.toJson());

			jsonWebSignature.setKeyIdHeaderValue(rsaJsonWebKey.getKeyId());
			jsonWebSignature.setKey(rsaJsonWebKey.getPrivateKey());

			jsonWebSignature.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

	
			try {
				jwt=jsonWebSignature.getCompactSerialization();
			}catch(JoseException ex) {
	
			}catch(Exception ex) {
	
			}

		}catch(Exception ex) {
			ex.printStackTrace();
		}
//		System.out.println("to Erase : *************************");
//		testJwt(jwt);
//		System.out.println("to Erase : *************************");
		return jwt;
	}
	
	@Override
	public JwtClaims testJwt(String token) {
//		boolean test = false;
		JwtClaims jwtClaims = null;
		try {
		
		//je recherche le kid
		String[] tokenTab = decodeToken(token);
		String headerEncoded = tokenTab[0];
		System.out.println("headerEncoded : " + headerEncoded);
		byte[] decodeBytesHeader = Base64.getUrlDecoder().decode(headerEncoded);
		String decodeHeader = new String(decodeBytesHeader);
		System.out.println("decodeHeader : " + decodeHeader);
		
		//parcours du dom
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode;
		JsonNode idNode;
		String kidV1=null;
		try {
			rootNode = objectMapper.readValue(decodeHeader, JsonNode.class);
			idNode = rootNode.path("kid");
			kidV1 = idNode.asText();
			System.out.println("kidV1 : " + kidV1);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		//Je parcours MyStatic.jsonWebKeys en fonction du kid 
		JsonWebKeySet jsonWebKeySet = new JsonWebKeySet(MyStatic.jsonWebKeys); 
		JsonWebKey jsonWebKey = jsonWebKeySet.findJsonWebKey(kidV1, null,  null,  null);
		System.out.println("JWK (" + kidV1 + ") ===> " + jsonWebKey.toJson());
		// Validate Token's authenticity and check claims
		JwtConsumer jwtConsumer = new JwtConsumerBuilder()
				.setRequireExpirationTime() 
				.setAllowedClockSkewInSeconds(30)
				.setRequireSubject()
				.setExpectedIssuer(MyConstant.DOMAIN)
				.setVerificationKey(jsonWebKey.getKey())
				.build();
				
		//Validate the JWT and process it to the Claims
//		JwtClaims jwtClaims = null;
		
		try {
			//contient tout le payload
			jwtClaims = jwtConsumer.processToClaims(token);

			System.out.println("JWT validation succeeded! " + jwtClaims);
			
			//jai besion du sub soit le login
			String login = jwtClaims.getSubject();
			System.out.println("login : " + login);
		} catch (InvalidJwtException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return jwtClaims;
	}
	
	private int generateRandmoKid() {
		Random rand = new Random();
		int randomKid = rand.nextInt(2);
		return randomKid;

	}
	
	private String[] decodeToken(String token) {
		String[] tokenTab = token.split("\\.");
		return tokenTab;
	}

}
