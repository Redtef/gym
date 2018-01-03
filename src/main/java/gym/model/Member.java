package gym.model;

import gym.util.Util;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.io.*;
public class Member {

    private int id;
    private String name;
    private String cin;
    private Date birth_date;
    private Integer age;
    private ImageView avatar;
    private Boolean handicap;
    private ArrayList<Progress> progresses;
    private ArrayList<Date> attendance;
    private Membership membership;
    private String avatarPath;

    public Member(String name, String cin, String avatar, Date birth_date, Boolean handicap) {
        this.id = id;
        this.name = name;
        this.cin = cin;
        this.birth_date = birth_date;
        this.handicap = handicap;
        this.avatarPath = avatar;
    }

    public Member(ResultSet rs) {
        try {
            this.id = rs.getInt("id");
            this.name = rs.getString("name");
            this.cin = rs.getString("cin");
            this.birth_date = rs.getDate("birth_date");
            this.handicap = rs.getBoolean("handicap");
            this.avatarPath = rs.getString("avatar");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.age = Util.getDiffYears(birth_date, new Date());
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public Member(int id, String name, String cin, String avatar, Date birth_date, Boolean handicap) {
        this.id = id;
        this.name = name;
        this.cin = cin;
        this.birth_date = birth_date;
        this.handicap = handicap;
        this.avatarPath = avatar;
        this.age = Util.getDiffYears(birth_date, new Date());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public ImageView getAvatar() {
        if (avatar == null)
            avatar = new ImageView(new Image(new File("src/main/resources"+avatarPath).toURI().toString()));

        avatar.setFitHeight(64);
        avatar.setFitWidth(64);
        return avatar;
    }


    public Boolean getHandicap() {
        return handicap;
    }

    public void setHandicap(Boolean handicap) {
        this.handicap = handicap;
    }

    public ArrayList<Progress> getProgresses() {
        return progresses;
    }

    public void setProgresses(ArrayList<Progress> progresses) {
        this.progresses = progresses;
    }

    public ArrayList<Date> getAttendance() {
        return attendance;
    }

    public void setAttendance(ArrayList<Date> attendance) {
        this.attendance = attendance;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }
}
