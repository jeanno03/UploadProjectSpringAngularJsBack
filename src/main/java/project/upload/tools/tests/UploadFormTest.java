package project.upload.tools.tests;

import org.springframework.web.multipart.MultipartFile;

public class UploadFormTest {

    private String description;
    private MultipartFile[] files;
    
	public UploadFormTest() {
		super();
	}

	public UploadFormTest(String description, MultipartFile[] files) {
		super();
		this.description = description;
		this.files = files;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}

}
