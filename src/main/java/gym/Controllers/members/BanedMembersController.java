package gym.Controllers.members;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import gym.Controllers.LoginController;
import gym.database.DAOMember;
import gym.model.Member;
import gym.util.Util;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BanedMembersController implements Initializable {
    private DAOMember daoMember;
    @FXML
    private TableColumn<Member, Integer> member_id;
    @FXML
    private TableColumn<Member, String> member_name;
    @FXML
    private TableColumn<Member, Date> member_birthdate;

    @FXML
    private TableColumn<Member, Boolean> handicape;
    @FXML
    private TableColumn<Member, String> membership_name;
    @FXML
    private TableColumn<Member, String> membre_cin;
    @FXML
    private TableColumn<Member, ImageView> membre_avatar;
    @FXML
    private TableView<Member> members;

    @FXML
    private JFXButton viewprofilebu, banbu,unban_b;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        viewprofilebu.setDisable(true);
        banbu.setDisable(true);
        unban_b.setDisable(true);
        daoMember = new DAOMember();
        member_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        member_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        member_birthdate.setCellValueFactory(new PropertyValueFactory<>("age"));
        handicape.setCellValueFactory(new PropertyValueFactory<>("handicap"));
        membre_cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        membre_avatar.setCellValueFactory(new PropertyValueFactory<>("avatar"));
        membership_name.setCellValueFactory(new PropertyValueFactory<>("membership"));
        member_id.prefWidthProperty().bind(members.widthProperty().divide(7));
        member_name.prefWidthProperty().bind(members.widthProperty().divide(7));
        member_birthdate.prefWidthProperty().bind(members.widthProperty().divide(7));
        handicape.prefWidthProperty().bind(members.widthProperty().divide(7));
        membre_cin.prefWidthProperty().bind(members.widthProperty().divide(7));
        membre_avatar.prefWidthProperty().bind(members.widthProperty().divide(7));
        membership_name.prefWidthProperty().bind(members.widthProperty().divide(7));

        members.setItems(FXCollections.observableList(
                daoMember.findAllWhere("baned", "=", "1")));

    }

    @FXML
    void onMouseClicked(MouseEvent event) {

        if (event.getClickCount() == 1) {
            viewprofilebu.setDisable(false);
            unban_b.setDisable(false);
        } else {
            members.getSelectionModel().clearSelection();
            viewprofilebu.setDisable(true);
            unban_b.setDisable(true);
        }
    }

    @FXML
    private AnchorPane newmemberwindow;

    @FXML
    private JFXButton avatarbu;

    @FXML
    private void unban(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Unban Membership confirmation");
        alert.setContentText("Are you sure you want to unban this member");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            daoMember.ban(false,members.getSelectionModel().getSelectedItem().getId());
            members.setItems(FXCollections.observableList(
                    daoMember.findAllWhere("baned", "=", "1")));
            members.refresh();
        }
    }

    @FXML
    private void viewprofile(ActionEvent event) {
        try {
            Stage st = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/member_profile.fxml"));

            Parent sceneMain = loader.load();

            MemberProfileController controller = loader.<MemberProfileController>getController();
            controller.initVariable(members.getSelectionModel().getSelectedItem());

            Scene scene = new Scene(sceneMain);
            st.setScene(scene);
            st.setMaximized(false);
            st.setTitle(members.getSelectionModel().getSelectedItem().getName());
            st.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void banmember(ActionEvent event) {

    }


    @FXML
    private void refresh(ActionEvent event) {
        daoMember.findAllWhere("baned", "=", "1");
        members.refresh();
    }

    @FXML
    private void print(ActionEvent event) {

    }

    @FXML
    private JFXTextField searchInput;

    @FXML
    private void searchByName(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            if (!searchInput.getText().isEmpty())
                members.setItems(FXCollections.observableList(daoMember.findBanedByName(searchInput.getText())));
            members.refresh();
        }

    }
}
