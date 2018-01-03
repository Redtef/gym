package gym.Controllers.members;

import com.jfoenix.controls.JFXComboBox;
import gym.database.DAOMembership;
import gym.database.DBSingleton;
import gym.model.Member;
import gym.model.Membership;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SetMembership implements Initializable {

   private int memberid;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DAOMembership all = new DAOMembership();
        new Thread(()->{
            while (memberid==0)
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        });
        memberships.setItems(FXCollections.observableList(
                all.findAll()));
    }

    @FXML
    private JFXComboBox<Membership> memberships;

    @FXML
    void canceloperation(ActionEvent event) {

    }

    @FXML
    void setMembership(ActionEvent event) {

        try {
            DBSingleton.getInstance()
                    .getConnection()
                    .createStatement()
                    .executeQuery("INSERT INTO BOOKING(MEMBER_ID,MEMBRESHIP_ID)VALUES ("+memberid+","+memberships.getSelectionModel().getSelectedItem().getId()+")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initVariable(int member) {
        this.memberid=member;
    }
}
