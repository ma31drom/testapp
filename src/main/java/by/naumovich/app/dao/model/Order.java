package by.naumovich.app.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import by.naumovich.app.dao.validation.CarExists;
import by.naumovich.app.dao.validation.RentForValidLicence;
import by.naumovich.app.dao.validation.UserExists;

@Entity
@Table(name = "orders")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler"})
@RentForValidLicence
public class Order extends IdAwareObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @CarExists
    private Integer carId;

    @NotNull
    @UserExists
    private Integer userId;

    @NotNull
    private OrderType type;

    @NotNull
    private Long summ;

    @NotNull
    @Column(name = "from_date")
    private Date from;
    @NotNull
    @Column(name = "to_date")
    private Date to;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public Long getSumm() {
        return summ;
    }

    public void setSumm(Long summ) {
        this.summ = summ;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

}
