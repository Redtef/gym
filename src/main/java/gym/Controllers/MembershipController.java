package gym.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import gym.database.DAOMembership;
import gym.model.Membership;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MembershipController implements Initializable {


    private DAOMembership daoMembership;

    private Boolean selected = false;
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

    @FXML
    private JFXButton saveMembershipbu;
    @FXML
    private JFXButton removeMembershipbu;

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

        daoMembership = new DAOMembership();

        new Thread(() -> memberships.setItems(FXCollections.observableList(
                daoMembership.findAll()))
        ).start();

        saveMembershipbu.setDisable(true);
        removeMembershipbu.setDisable(true);
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Input Required");

        JFXTextField array[] = {txName, txWeeks, txPrice, txDays};

        for (JFXTextField field : array) {
            field.getValidators().add(validator);
            field.focusedProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue) field.validate();

            });

        }

    }

    @FXML
    void saveMembership(ActionEvent event) {

        new Thread(() -> {
            if (selected)
                daoMembership.update(new Membership(memberships.getSelectionModel().getSelectedItem().getId(), txName.getText(), txWeeks.getText(), txDays.getText(), txPrice.getText()));
            else
                daoMembership.insert(new Membership(null, txName.getText(), txWeeks.getText(), txDays.getText(), txPrice.getText()));
            memberships.refresh();
        }).start();

    }

    @FXML
    void removeMembership(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Remove Membership confirmation");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            new Thread(() -> daoMembership.delete(memberships.getSelectionModel().getSelectedItem().getId())).start();
            memberships.setItems(FXCollections.observableList(
                    daoMembership.findAll()));
            memberships.refresh();
        }
    }

    @FXML
    private JFXTextField txName, txWeeks, txDays, txPrice;
    @FXML
    private Label membership_label;

    @FXML
    void rowClickeed(MouseEvent event) {
        if (event.getClickCount() == 2) //Checking double click
        {
            membership_label.setText("Edit Membership");
            txName.setText(memberships.getSelectionModel().getSelectedItem().getName());
            txWeeks.setText(String.valueOf(memberships.getSelectionModel().getSelectedItem().getWeeks()));
            txDays.setText(String.valueOf(memberships.getSelectionModel().getSelectedItem().getDays()));
            txPrice.setText(String.valueOf(memberships.getSelectionModel().getSelectedItem().getPrice()));
            selected = true;
            saveMembershipbu.setDisable(false);

        } else if (event.getClickCount() == 1) {
            removeMembershipbu.setDisable(false);

        }
    }
    @FXML
    void newMembership(ActionEvent event) {
        txName.clear();
        txWeeks.clear();
        txDays.clear();
        txPrice.clear();
        selected = false;
        saveMembershipbu.setDisable(false);

    }
}
