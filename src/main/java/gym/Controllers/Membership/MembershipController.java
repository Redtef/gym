package gym.Controllers.Membership;

import gym.Controllers.MainController;
import gym.database.DAOMembership;
import gym.model.Membership;
import gym.util.WindowUtil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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



        new Thread(() -> memberships.setItems(FXCollections.observableList(new DAOMembership().findAll()))).start();
    }

    @FXML
    void showAddMembership(ActionEvent event) {
        WindowUtil.loadWindow(getClass().getResource("/Membership/dialog_add_membership.fxml"), "Add New Membership", null);
    }
    @FXML
    void removeMembership(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Remove Membership confirmation");
        alert.showAndWait();
        if (alert.getResult()==ButtonType.OK)
        {
            new Thread(() -> new DAOMembership().delete(memberships.getSelectionModel().getSelectedItem().getId())).start();

        }
    }

    @FXML
    public void updateMembership(ActionEvent e){
        try {
            FXMLLoader loader=new FXMLLoader();
            Parent parent =loader.load(getClass().getResource("/Membership/dialog_update_membership.fxml").openStream());
            DialogUpdateMembership updateMembership =(DialogUpdateMembership)loader.getController();
            updateMembership.setMembership(memberships.getSelectionModel().getSelectedItem());
            Stage stage = null;
            
                stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("updateMembership");
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.setScene(new Scene(parent));
            stage.setResizable(false);

            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
