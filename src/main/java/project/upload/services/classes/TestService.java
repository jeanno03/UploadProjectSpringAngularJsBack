package project.upload.services.classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.upload.dtos.MyUserDto;
import project.upload.models.MyFile;
import project.upload.models.MyRole;
import project.upload.models.MySpace;
import project.upload.models.MyUser;
import project.upload.repositories.MyFileRepository;
import project.upload.repositories.MyRoleRepository;
import project.upload.repositories.MySpaceRepository;
import project.upload.repositories.MyUserRepository;
import project.upload.services.interfaces.TestServiceInterface;
import project.upload.tools.classes.Credential;
import project.upload.tools.interfaces.MyConstant;
import project.upload.tools.interfaces.MyStatic;
import project.upload.transformers.interfaces.TestTransformerInterface;

@Service
public class TestService implements TestServiceInterface{
	
	@Autowired
	private MyUserRepository myUserRepository;
	
	@Autowired
	private MySpaceRepository mySpaceRepository;
	
	@Autowired
	private MyFileRepository myFileRepository;
	
	@Autowired
	private MyRoleRepository myRoleRepository;
	
	@Autowired
	private TestTransformerInterface testTransformer;

	@Override
	public String getDataTest() {
		
//		String path = MyConstant.PROP.getProperty("path_directory");
		
		try {
			
			Date myDate = new Date();
			
		String password = "12345678";
		String passwordSha3 = getStringSha3(password);

		MyUser myUser1 = new MyUser ("Albator" , passwordSha3, "albaltor@gmail.com");
		MyUser myUser2 = new MyUser ("Dartagnan" , passwordSha3, "artos@gmail.com");
		
		MySpace mySpace1 = new MySpace("Professionnel", "espace de stockage pro.", myDate);
		MySpace mySpace2 = new MySpace("Personnel", "espace de stockage preso.", myDate);
		
		MySpace mySpace3 = new MySpace("Manga", "espace pour mes mangas.", myDate);
		MySpace mySpace4 = new MySpace("Comics", "espace dpour mes comics.", myDate);
		
		mySpace1.setMyUser(myUser1);
		mySpace2.setMyUser(myUser1);
		
		mySpace3.setMyUser(myUser2);
		mySpace4.setMyUser(myUser2);

		MyFile myFile1 = new MyFile ("fiche de paie octobre 2018.pdf", MyConstant.PATH+"Albator-005.pdf", "Albator-005.pdf", myDate);
		MyFile myFile2 = new MyFile ("fiche de paie décembre 2018.pdf", MyConstant.PATH+"Albator-006.pdf", "Albator-006.pdf", myDate);
		MyFile myFile3 = new MyFile ("mon dessin de chien.jpeg", MyConstant.PATH+"Albator-007.jpeg", "Albator-007.jpeg"
				+ "", myDate);
		MyFile myFile4 = new MyFile ("ma photo de phoque.jpeg", MyConstant.PATH+"Albator-008.jpeg", "Albator-008.jpeg", myDate);
		
		MyFile myFile5 = new MyFile ("Naruto 1.pdf", MyConstant.PATH+"Dartagnan-001.pdf", "Dartagnan-001.pdf", myDate);
		MyFile myFile6 = new MyFile ("Naruto 2.pdf", MyConstant.PATH+"Dartagnan-002.pdf", "Dartagnan-002.pdf", myDate);
		MyFile myFile7 = new MyFile ("Serval 1.pdf", MyConstant.PATH+"Dartagnan-003.pdf", "Dartagnan-003.pdf", myDate);
		MyFile myFile8 = new MyFile ("Serval 2.pdf", MyConstant.PATH+"Dartagnan-004.pdf", "Dartagnan-004.pdf", myDate);
		
		MyRole myRole1 = new MyRole("utilisateur");
		MyRole myRole2 = new MyRole("gestionaire");
		MyRole myRole3 = new MyRole("administrateur");

		myFile1.setMySpace(mySpace1);
		myFile2.setMySpace(mySpace1);
		myFile3.setMySpace(mySpace2);
		myFile4.setMySpace(mySpace2);
		
		myFile5.setMySpace(mySpace3);
		myFile6.setMySpace(mySpace3);
		myFile7.setMySpace(mySpace4);
		myFile8.setMySpace(mySpace4);
	
		myUserRepository.save(myUser1);
		myUserRepository.save(myUser2);
	
		mySpaceRepository.save(mySpace1);
		mySpaceRepository.save(mySpace2);
		myFileRepository.save(myFile1);
		myFileRepository.save(myFile2);
		myFileRepository.save(myFile3);
		myFileRepository.save(myFile4);
		
		mySpaceRepository.save(mySpace3);
		mySpaceRepository.save(mySpace4);
		myFileRepository.save(myFile5);
		myFileRepository.save(myFile6);
		myFileRepository.save(myFile7);
		myFileRepository.save(myFile8);
		
		myRoleRepository.save(myRole1);
		myRoleRepository.save(myRole2);
		myRoleRepository.save(myRole3);
				
		MyUser bddMyUser1 = myUserRepository.findByLoginIgnoreCase("Albator");
		MyUser bddMyUser2 = myUserRepository.findByLoginIgnoreCase("Dartagnan");
		
		List<MyRole> bddMyRoles1 = (List<MyRole>) myRoleRepository.findAll();
		
		MyRole bddMyRole1 = myRoleRepository.findByName("utilisateur");
		List<MyRole> bddMyRoles2 = new ArrayList();
		bddMyRoles2.add(bddMyRole1);
		
		bddMyUser1.setMyRoles(bddMyRoles1);
		bddMyUser2.setMyRoles(bddMyRoles2);
		
		myUserRepository.save(bddMyUser1);
		myUserRepository.save(bddMyUser2);
		
		return "success";

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Exception";
		}
	}

	@Override
	public MyUserDto getMyUserDtoFullDatas(Credential credential) throws Exception {
		MyUserDto myUserDto = null;
		String credentialSha3 = getStringSha3(credential.getPassword());
		try {
			
			MyUser myUser = myUserRepository.findByLoginIgnoreCase(credential.getLogin());
			
			if(testConnection(credential, myUser)) {
				myUserDto = testTransformer.getMyUserDtoFullDatas(myUser);
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return myUserDto;		
	}
	
	@Override
	public boolean authorizeTest(String bearer) {
		
		boolean test = false;
		try {
		
		if (bearer.equals("1234")) {
			test = true;
		}else {
			test=false;
		}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return test;
	}
	
	//return password hashé
	private String getStringSha3(String password) throws Exception { 
	    SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512(); 
	    byte[] digest = digestSHA3.digest(password.getBytes()); 
	    return Hex.toHexString(digest);
	} 
	
	private boolean testConnection(Credential credential, MyUser myUser) throws Exception {
		
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

}
