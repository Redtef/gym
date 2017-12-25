package gym.Controllers;

import com.jfoenix.controls.JFXDialog;
import gym.Main;
import gym.database.DAOMembership;
import gym.model.Membership;
import gym.util.WindowUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MembershipController implements Initializable {



    @FXML
    private TableColumn<Membership, Integer> membership_id;

    @FXML
    private TableColumn<Membership, String> membership_name;

    @FXML
    private TableColumn<Membership, Integer> membership_days;

    @FXML
    private TableColumn<Membership, Integer> membership_weeks;

    @FXML
    private TableColumn<Membership, Double> membership_price;

    @FXML
    private TableView<Membership> memberships;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        membership_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        membership_id.prefWidthProperty().bind(memberships.widthProperty().divide(5));
        membership_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        membership_name.prefWidthProperty().bind(memberships.widthProperty().divide(5));
        membership_days.setCellValueFactory(new PropertyValueFactory<>("days"));
        membership_days.prefWidthProperty().bind(memberships.widthProperty().divide(5));
        membership_weeks.setCellValueFactory(new PropertyValueFactory<>("weeks"));
        membership_weeks.prefWidthProperty().bind(memberships.widthProperty().divide(5));
        membership_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        membership_price.prefWidthProperty().bind(memberships.widthProperty().divide(5));
        ObservableList<Membership> olist = FXCollections.observableList(new DAOMembership().findAll());
        memberships.setItems(olist);
    }
    @FXML
    void showAddMembership(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText("I have a great message for you!");

        alert.showAndWait();
        WindowUtil.loadWindow(getClass().getResource("/dialog_add_membership.fxml"),"Add New Membership",null);
    }
}
