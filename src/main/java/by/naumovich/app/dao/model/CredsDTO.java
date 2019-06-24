package by.naumovich.app.dao.model;

public class CredsDTO {

	private String userName;

	private String passHash;

	public CredsDTO() {
		super();
	}

	public CredsDTO(String userName, String passHash) {
		super();
		this.userName = userName;
		this.passHash = passHash;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassHash() {
		return passHash;
	}

	public void setPassHash(String passHash) {
		this.passHash = passHash;
	}

}
