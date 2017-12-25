package gym.model;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Date;

public class Member {

    public static class Builder {
        private int id;
        private String name;
        private String cin;
        private Date birth_day;
        private Image avatar;
        private Boolean handicap;

        public Builder() {
        }

        public Member build() {
            return new Member(this);
        }

        public Builder id(int id) {
            this.id = id;
            return this;

        }

        public Builder name(String name) {
            this.name = name;
            return this;

        }

        public Builder cin(String cin) {
            this.cin = cin;
            return this;

        }

        public Builder birth_day(Date birth_day) {
            this.birth_day = birth_day;
            return this;

        }

        public Builder avatar(Image avatar) {
            this.avatar = avatar;
            return this;

        }



        public Builder handicap(Boolean handicap) {
            this.handicap = handicap;
            return this;
        }

    }

    private int id;
    private String name;
    private String cin;
    private Date birth_day;
    private Image avatar;
    private Boolean handicap;
    private ArrayList<Progress> progresses;
    private ArrayList<Date> attendance;
    private Membership membership;


    private Member(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.cin = builder.cin;
        this.birth_day = builder.birth_day;
        this.avatar = builder.avatar;
        this.handicap = builder.handicap;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCin() {
        return cin;
    }

    public Date getBirth_day() {
        return birth_day;
    }

    public Image getAvatar() {
        return avatar;
    }


    public Boolean getHandicap() {
        return handicap;
    }

    public ArrayList<Progress> getProgresses() {
        return progresses;
    }

    public ArrayList<Date> getAttendance() {
        return attendance;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setProgresses(ArrayList<Progress> progresses) {
        this.progresses = progresses;
    }

    public void setAttendance(ArrayList<Date> attendance) {
        this.attendance = attendance;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }
}
