package gym.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import gym.database.DAOMembership;
import gym.model.Membership;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private AnchorPane home_pane;

    @FXML
    private JFXButton homeButton;

    @FXML
    private AnchorPane members_pane;

    @FXML
    private JFXButton memebers_button;

    @FXML
    private AnchorPane membership_pane;

    @FXML
    private JFXButton membership_button;

    @FXML
    private AnchorPane booking_pane;

    @FXML
    private JFXButton booking_button;

    @FXML
    private AnchorPane settings_pane;

    @FXML
    private JFXButton settings_button;

    @FXML
    private BorderPane main_border_pane;
    @FXML
    private Font x3;

    @FXML
    private Color x4;


    @FXML
    private AnchorPane main_content;
    @FXML
    void setBookingView(ActionEvent event) {

    }

    @FXML
    void setHomeView(ActionEvent event) {

    }

    @FXML
    void setMembersView(ActionEvent event) {

    }

    @FXML
    void setMembershipView(ActionEvent event) {

        try {
            membership_pane.setStyle("-fx-background-color:#222831");
            main_content.getChildren().clear();
            main_content.getChildren().add(FXMLLoader.load(getClass().getResource("/membership.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
