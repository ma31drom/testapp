package by.naumovich.app.dao.model;

public class IdAwareObject {

	private Integer id;

	public IdAwareObject() {
		super();
	}

	public IdAwareObject(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
