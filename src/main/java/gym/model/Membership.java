package gym.model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Membership {


    private Integer id;
    private String name;
    private Integer weeks;
    private Integer days;
    private Double price;
    private Date start_at;
    private Date end_at;
    private Boolean paid = false;
    private Date payment_date;

    public Membership(Integer id, String name, Integer weeks, Integer days, Double price) {
        this.id = id;
        this.name = name;
        this.weeks = weeks;
        this.days = days;
        this.price = price;
    }

    public Membership(Integer id, String name, String weeks, String days, String price) {
        this.id = id;
        this.name = name;
        this.weeks = Integer.valueOf(weeks);
        this.days = Integer.valueOf(days);
        this.price = Double.valueOf(price);
    }

    public Membership(ResultSet rs) {
        try {
            this.id = rs.getInt("id");
            this.name = rs.getString("MEMBERSHIP_NAME");
            this.weeks = rs.getInt("weeks");
            this.days = rs.getInt("days");
            this.price = rs.getDouble("price");
            this.paid=rs.getBoolean("paid");
            this.start_at=rs.getDate("booking_date");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Date getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(Date payment_date) {
        this.payment_date = payment_date;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeeks(Integer weeks) {
        this.weeks = weeks;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getWeeks() {
        return weeks;
    }

    public Integer getDays() {
        return days;
    }

    public Double getPrice() {
        return price;
    }

    public Date getStart_at() {
        return start_at;
    }

    public Date getEnd_at() {
        return end_at;
    }


    public void setStart_at(Date start_at) {
        this.start_at = start_at;
    }

    public void setEnd_at(Date end_at) {
        this.end_at = end_at;
    }


    @Override
    public String toString() {
        return this.name;
    }
}
