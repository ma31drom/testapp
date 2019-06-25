package by.naumovich.app.dao.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import by.naumovich.app.dao.validation.CarExists;

@Entity
@Table(name = "car_picture")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class CarPicture extends IdAwareObject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@CarExists
	private Integer carId;

	private String fileName;

	@NotNull
	private byte[] valueBase64;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public byte[] getValueBase64() {
		return valueBase64;
	}

	public void setValueBase64(byte[] valueBase64) {
		this.valueBase64 = valueBase64;
	}

}
