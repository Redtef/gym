package gym.Controllers.members;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import gym.database.DAOMember;
import gym.model.Member;
import gym.util.Util;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class NewMemberController implements Initializable {
    @FXML
    private JFXButton avatarbu;
    @FXML
    private AnchorPane newmemberwindow;
    @FXML
    private JFXTextField txname;
    @FXML
    private JFXTextField txphone;
    @FXML
    private JFXTextField txcin;
    @FXML
    private JFXDatePicker birthdate;
    @FXML
    private JFXCheckBox handicap;

    private String avatarpath = "/avatars/athlete.png";

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }


    @FXML
    private ImageView imgview;

    @FXML
    public void insertnewuser(ActionEvent event) {
        LocalDate localDate = birthdate.getValue();
        Date date = java.sql.Date.valueOf(localDate);
        Member member = new Member(txname.getText(), txcin.getText(), avatarpath, date, handicap.isSelected());

        new Thread(() -> {
            new DAOMember().insert(member);
        }).start();
    }

    @FXML
    public void setAvatar(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter JPGFilter = new FileChooser.ExtensionFilter("*.JPG","*.JPG");
        FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("*.jpg","*.jpg");
        FileChooser.ExtensionFilter PNGFilter = new FileChooser.ExtensionFilter("*.PNG","*.PNG");
        FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("*.png","*.png");
        fileChooser.getExtensionFilters().addAll(JPGFilter,jpgFilter,PNGFilter,pngFilter);
        File file = fileChooser.showOpenDialog(avatarbu.getScene().getWindow());
        if (file != null) {


            File newFile = new File("src/main/resources/avatars/" + System.currentTimeMillis() + file.getName());
            try {
                Util.copy(file, newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (file.exists() && file.isFile()) {
                System.out.println("file exists, and it is a file");
            }
            imgview.setImage(new Image(newFile.toURI().toString()));
            avatarpath = newFile.getPath().substring(18).replace("\\", "/");



            System.out.println(avatarpath);


        }
    }
}
