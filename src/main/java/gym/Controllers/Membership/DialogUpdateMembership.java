package gym.Controllers.Membership;

import com.jfoenix.controls.JFXTextField;
import gym.database.DAOMembership;
import gym.model.Membership;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class DialogUpdateMembership {
    @FXML
    JFXTextField name;
    @FXML
    JFXTextField weeks;
    @FXML
    JFXTextField days;
    @FXML
    JFXTextField price;

    @FXML
    void save() {


        new Thread(()-> new DAOMembership().update(
                new Membership(null,
                        name.getText(),
                        Integer.valueOf(weeks.getText()),
                        Integer.valueOf(days.getText()),
                        Double.valueOf(price.getText())))).start();
    }



    public void setMembership(Membership membership) {
        name.setText(membership.getName());
        days.setText(String.valueOf(membership.getDays()));
        weeks.setText(String.valueOf(membership.getWeeks()));
        price.setText(String.valueOf(membership.getPrice()));
    }

}
