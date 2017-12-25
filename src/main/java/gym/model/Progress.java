package gym.model;

import java.util.Date;

public class Progress {

    private long height;
    private long weight;
    private Date check_date;

    public Progress(long height, long weight, Date check_date) {
        this.height = height;
        this.weight = weight;
        this.check_date = check_date;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public Date getCheck_date() {
        return check_date;
    }

    public void setCheck_date(Date check_date) {
        this.check_date = check_date;
    }
}
