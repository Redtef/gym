package gym.model;

import java.util.Date;

public class Payment {

    private Boolean paid = false;
    private int value;
    private int rest;
    private Date payment_date;

    public Payment(Boolean paid, int value) {
        this.paid = paid;
        this.value = value;
    }
}
