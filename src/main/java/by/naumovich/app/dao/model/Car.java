package by.naumovich.app.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import by.naumovich.app.dao.model.car.CarFuelType;
import by.naumovich.app.dao.model.car.CarTransmission;
import by.naumovich.app.dao.model.car.CarType;
import by.naumovich.app.dao.validation.ModelExists;

@Entity
@Table(name = "cars")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler"})
public class Car extends IdAwareObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ModelExists
    private Integer model;

    @NotNull
    private CarType type;

    private Integer odometer;

    @Column(name = "VIN", unique = true)
    @Length(min = 17, max = 17)
    private String vin;

    @NotNull
    private Integer year;

    private String color;
    @NotNull
    private CarTransmission transmission;
    @NotNull
    private CarFuelType fuel;
    @NotNull
    private Integer price;
    @NotNull
    private Integer rent;

    private Integer engine;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getModel() {
        return model;
    }

    public void setModel(Integer model) {
        this.model = model;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public Integer getOdometer() {
        return odometer;
    }

    public void setOdometer(Integer odometer) {
        this.odometer = odometer;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public CarTransmission getTransmission() {
        return transmission;
    }

    public void setTransmission(CarTransmission transmission) {
        this.transmission = transmission;
    }

    public CarFuelType getFuel() {
        return fuel;
    }

    public void setFuel(CarFuelType fuel) {
        this.fuel = fuel;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getRent() {
        return rent;
    }

    public void setRent(Integer rent) {
        this.rent = rent;
    }

    public Integer getEngine() {
        return engine;
    }

    public void setEngine(Integer engine) {
        this.engine = engine;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

}
