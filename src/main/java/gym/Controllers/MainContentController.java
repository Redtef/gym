package gym.Controllers;

import com.jfoenix.controls.JFXTextField;
import gym.database.DAOMembership;
import gym.database.DBSingleton;
import gym.model.Membership;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainContentController implements Initializable {

    @FXML
    private PieChart users_membership, members;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


            members_pie();
            users_membership_pie();
    }

    private void users_membership_pie() {
        ArrayList<PieChart.Data> data = new ArrayList<>();
        try {
            ResultSet set = DBSingleton.getInstance().getConnection().createStatement()
                    .executeQuery("SELECT count(MEMBER.ID) as count,MEMBERSHIP.NAME as name " +
                            "FROM MEMBER,BOOKING,MEMBERSHIP\n" +
                            "WHERE MEMBER.ID=BOOKING.MEMBER_ID(+) AND BOOKING.MEMBRESHIP_ID=MEMBERSHIP.ID(+) " +
                            "GROUP BY MEMBERSHIP.NAME");

            while (set.next()) {
                data.add(new PieChart.Data((set.getString("name")!=null)?set.getString("name"):"Without Membership", set.getInt("count")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        users_membership.setData(FXCollections.observableArrayList(data));
    }

    private void members_pie() {
        ArrayList<PieChart.Data> data = new ArrayList<>();
        try {
            ResultSet set = DBSingleton.getInstance().getConnection().createStatement().executeQuery("SELECT BANED,count(ID) as count FROM  MEMBER GROUP BY BANED");

            while (set.next()) {
                data.add(new PieChart.Data(set.getBoolean("baned") ? "Baned" : "Not Baned", set.getInt("count")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        members.setData(FXCollections.observableArrayList(data));
    }
}
