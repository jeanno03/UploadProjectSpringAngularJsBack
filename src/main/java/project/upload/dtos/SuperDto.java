package project.upload.dtos;

public class SuperDto {

	protected Long id;

	public SuperDto() {
		super();
	}

	public SuperDto(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "SuperDto [id=" + id + "]";
	}

}
