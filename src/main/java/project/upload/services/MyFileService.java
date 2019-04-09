package project.upload.services;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.jose4j.jwt.JwtClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import project.upload.dtos.MyFileDto;
import project.upload.models.MyFile;
import project.upload.models.MySpace;
import project.upload.models.MyUser;
import project.upload.reporitories.MyFileRepository;
import project.upload.reporitories.MySpaceRepository;
import project.upload.reporitories.MyUserRepository;
import project.upload.tools.MyConstant;
import project.upload.transformers.MyFileTransformer;

@Service
public class MyFileService implements MyFileServiceInterface{

	@Autowired
	private MyFileRepository myFileRepository;

	@Autowired
	private MySpaceRepository mySpaceRepository;

	@Autowired
	private MyUserRepository myUserRepository;

	@Autowired
	private JwtServiceInterface jwtService;

	@Autowired
	private MyFileTransformer myFileTransformer;


	@Override
	public List<MyFileDto> saveMyFiles(String token, Long id, MultipartFile[] multipartFile) {


		List<MyFileDto> myFilesDto = null;

		Date myDate = new Date();

		try {
			JwtClaims jwtClaims = jwtService.testJwt(token);
			String login = jwtClaims.getSubject();

			//requête native ==> pb impossible de faire set avec un objet obtenu depuis requete native
			//			MySpace mySpace = mySpaceRepository.selectMySpaceById(id);

			Optional<MySpace> mySpaceOptional = mySpaceRepository.findById(id);

			MySpace mySpace = mySpaceOptional.get();

			for(MultipartFile uploadedFile : multipartFile) {

				//1 j'enregistre le fichier
				MyFile myFile = new MyFile();
				myFile.setName(uploadedFile.getOriginalFilename());

				//voir si pas d'exception
				myFile.setMySpace(mySpace);         	
				myFileRepository.save(myFile);

				//2 je récupère le dernier fichier enregistré par l'utilisateur pour créer reName + path
				MyFile myLastFile = myFileRepository.selectMyLastFileFromMyUserLogin(login);

				String reName = generateRename(login, mySpace, myLastFile);
				myLastFile.setReName(reName);

				String path = MyConstant.PATH_DIRECTORY + reName;
				myLastFile.setPath(path);
				myLastFile.setUploadDate(myDate);

				//3 je sauvegarde dans bdd
				myFileRepository.save(myLastFile);

				//4 je sauvegarde dans disk dur

				File file = new File(path);
				uploadedFile.transferTo(file);

				//5 Pause for 4 seconds
				Thread.sleep(4000);

			}

			//6 je récupère les fichiers sauvegardé par mySpace.id
			List<MyFile> myFiles = myFileRepository.selectMyFilesByMySpaceId(id);

			//7 je créé myFilesDto
			myFilesDto = myFileTransformer.getMySavedFilesDto(myFiles);

		}catch(Exception ex) {
			ex.printStackTrace();
			
		}

		return myFilesDto;

	}
	
	@Override
	public MyFileDto getDownLoadingMyFileDto(String token, Long id) {
		
		MyFileDto myFileDto = null;
		
		try {
			
			JwtClaims jwtClaims = jwtService.testJwt(token);
			MyFile myFile = myFileRepository.selectMyFileById(id);
			myFileDto = myFileTransformer.getMyUploadingFileDto(myFile);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return myFileDto;
	}

	private String generateRename(String login, MySpace mySpace, MyFile myLastFile) {

		MyUser myUser = myUserRepository.selectMyUserByLogin(login);

		//pour avoir l'extension
		String extension = "";

		try {
			
			String[] parts = myLastFile.getName().split("\\.");
			int size = parts.length;
			extension = "." + parts[size-1];


		}catch(Exception ex) {
			ex.printStackTrace();
		}

		String reName = "my_user_"+myUser.getId()+"_my_space_"+mySpace.getId()+"_my_file_"+myLastFile.getId()+""+extension;

		return reName;
	}

}
