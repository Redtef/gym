package gym.Controllers.Membership;

import com.jfoenix.controls.JFXTextField;
import gym.database.DAOMembership;
import gym.model.Membership;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import java.util.EventListener;

public class DialogAddMembership {
    @FXML
    JFXTextField name;
    @FXML
    JFXTextField weeks;
    @FXML
    JFXTextField days;
    @FXML
    JFXTextField price;

    @FXML
    void save(ActionEvent e) {


        new Thread(()->{
            new DAOMembership().insert(
                    new Membership(null,
                            name.getText(),
                            Integer.valueOf(weeks.getText()),
                            Integer.valueOf(days.getText()),
                            Double.valueOf(price.getText())));

        }).start();
    }


}
