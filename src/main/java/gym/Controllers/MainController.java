package gym.Controllers;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
    private AnchorPane ban_pane;

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
    String c1 = " #444F5A";
    String c2 = "#222831";

    @FXML
    private AnchorPane main_content;

    @FXML
    void setBookingView(ActionEvent event) {

        try {
            ban_pane.setStyle("-fx-background-color:"+c1);

            membership_pane.setStyle("-fx-background-color:" + c1);
            members_pane.setStyle("-fx-background-color:" + c1);
            home_pane.setStyle("-fx-background-color:" + c1);
            booking_pane.setStyle("-fx-background-color:" + c2);

            main_content.getChildren().clear();
            main_content.getChildren().add(FXMLLoader.load(getClass().getResource("/booking.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void setHomeView(ActionEvent event) {
        try {
            ban_pane.setStyle("-fx-background-color:"+c1);

            membership_pane.setStyle("-fx-background-color:" + c1);
            members_pane.setStyle("-fx-background-color:" + c1);
            home_pane.setStyle("-fx-background-color:" + c2);
            booking_pane.setStyle("-fx-background-color:" + c1);
            main_content.getChildren().clear();
            main_content.getChildren().add(FXMLLoader.load(getClass().getResource("/main_content.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void setMembersView(ActionEvent event) {
        try {
            ban_pane.setStyle("-fx-background-color:"+c1);
            membership_pane.setStyle("-fx-background-color:"+c1);
            members_pane.setStyle("-fx-background-color:"+c2);
            home_pane.setStyle("-fx-background-color:"+c1);
            booking_pane.setStyle("-fx-background-color:"+c1);
            main_content.getChildren().clear();
            main_content.getChildren().add(FXMLLoader.load(getClass().getResource("/members_content.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void exit(ActionEvent event) {

        Platform.exit();
        System.exit(0);

    }
    @FXML
    void ban(ActionEvent event) {

        try {
            ban_pane.setStyle("-fx-background-color:"+c2);
            membership_pane.setStyle("-fx-background-color:"+c1);
            members_pane.setStyle("-fx-background-color:"+c1);
            home_pane.setStyle("-fx-background-color:"+c1);
            booking_pane.setStyle("-fx-background-color:"+c1);
            main_content.getChildren().clear();
            main_content.getChildren().add(FXMLLoader.load(getClass().getResource("/baned_members_content.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void setMembershipView(ActionEvent event) {

        try {
            ban_pane.setStyle("-fx-background-color:"+c1);
            membership_pane.setStyle("-fx-background-color:"+c2);
            members_pane.setStyle("-fx-background-color:"+c1);
            home_pane.setStyle("-fx-background-color:"+c1);
            booking_pane.setStyle("-fx-background-color:"+c1);
            main_content.getChildren().clear();
            main_content.getChildren().add(FXMLLoader.load(getClass().getResource("/membership.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            //membership_pane.setStyle("-fx-background-color:#222831");
            main_content.getChildren().clear();
            main_content.getChildren().add(FXMLLoader.load(getClass().getResource("/main_content.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
