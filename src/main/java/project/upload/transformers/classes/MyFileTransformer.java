package project.upload.transformers.classes;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import project.upload.UploadProjectSpringAngularJsBackApplication;
import project.upload.dtos.MyFileDto;
import project.upload.models.MyFile;
import project.upload.repositories.MyFileRepository;
import project.upload.transformers.interfaces.TransformerInterface;

@Service
@Qualifier("my-file")
public class MyFileTransformer implements TransformerInterface {

	final static Logger logger = Logger.getLogger(MyFileTransformer.class);

	@Autowired
	MyFileRepository myFileRepository;

	//récupère List<MyListDto> en fonction de MySpaceId
	@Override
	public List<MyFileDto> getMyListDto(String mySpaceId) {

		logger.info("Method : MyFileTransformer.getMyListDto()");

		List<MyFileDto> myFilesDto = new ArrayList();
		try {
			Long id = Long.valueOf(mySpaceId);

			//6 je récupère les fichiers sauvegardé par mySpace.id
			List<MyFile> myFiles = myFileRepository.selectMyFilesByMySpaceId(Long.valueOf(id));

			myFiles.forEach(m->{
				MyFileDto myFileDto = new MyFileDto(m.getId(), m.getName(), m.getPath(), m.getReName(), m.getUploadDate());
				myFilesDto.add(myFileDto);
			});

		}catch(NullPointerException ex) {

			logger.error("NullPointerException : " + ex);

		}

		return myFilesDto;
	}

	@Override
	public MyFileDto getSimpleDto(String myFileId) {

		logger.info("Method : MyFileTransformer.getSimpleDto()");

		MyFileDto myFileDto = null;

		try {
			Long id = Long.valueOf(myFileId);
			MyFile myFile = myFileRepository.selectMyFileById(id);
			myFileDto = new MyFileDto(myFile.getId(), myFile.getName(), myFile.getPath(), myFile.getReName(), myFile.getUploadDate());

		}catch(NullPointerException ex) {

			logger.error("NullPointerException : " + ex);

		}

		return myFileDto;
	}

}
