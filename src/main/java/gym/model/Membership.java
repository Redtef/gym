package gym.model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import java.util.Date;

public class Membership extends RecursiveTreeObject<Membership> {



    private Integer id;
    private String name;
    private Integer weeks;
    private Integer days;
    private Double price;
    private Date start_at;
    private Date end_at;
    private Payment payment;

    public Membership(Integer id, String name, Integer weeks, Integer days, Double price) {
        this.id = id;
        this.name = name;
        this.weeks = weeks;
        this.days = days;
        this.price = price;
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

    public Payment getPayment() {
        return payment;
    }

    public void setStart_at(Date start_at) {
        this.start_at = start_at;
    }

    public void setEnd_at(Date end_at) {
        this.end_at = end_at;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

}
