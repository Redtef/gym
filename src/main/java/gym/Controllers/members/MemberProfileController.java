package gym.Controllers.members;

import com.jfoenix.controls.*;
import gym.Controllers.LoginController;
import gym.database.DAOMember;
import gym.database.DBSingleton;
import gym.model.Member;
import gym.util.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import static gym.util.Util.saveavatartodir;

public class MemberProfileController implements Initializable {

    DAOMember daoMember;
    private Member member;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        DAOMember daoMember = new DAOMember();
        new Thread(() -> {
            while (member == null)
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            txname.setText(member.getName());
//            txphone.setText(member.getp());
            txcin.setText(member.getCin());
            birthdate.setValue(Util.LOCAL_DATE(member.getBirth_date().toString()));
            handicap.setSelected(member.getHandicap());
            avatar = member.getAvatar();
            if (member.getMembership().getName() == null) {
                membership.setText("No Membership");
                paid.setDisable(true);

            } else {
                membership.setText(member.getMembership().toString() + " Membership");
                setmembershipbu.setDisable(true);
            }
        }).start();
    }

    @FXML
    private JFXButton setmembershipbu;
    @FXML
    private ImageView avatar;
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

    @FXML
    private JFXButton updateavatar;

    @FXML
    private LineChart<?, ?> progress;

    @FXML
    private BarChart<?, ?> attendance;

    @FXML
    private Label membership;

    @FXML
    private JFXToggleButton paid;

    @FXML
    void addnewprogress(ActionEvent event) {
        try {
            Stage st = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/add_progress.fxml"));

            Parent sceneMain = loader.load();

            NewProgressController controller = loader.<NewProgressController>getController();
            controller.initVariable(member.getId());

            Scene scene = new Scene(sceneMain);
            st.setScene(scene);
            st.setMaximized(false);
            st.setTitle(member.getName());
            st.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void presentToday(ActionEvent event) {

    }

    @FXML
    void saveavatar(ActionEvent event) {


        String path = saveavatartodir(updateavatar);
        if (path != null && !path.equals("")) {
            try {
                DBSingleton.getInstance().getConnection().createStatement()
                        .executeQuery("UPDATE MEMBER SET AVATAR = '" + path + "' WHERE ID=" + member.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            File file = new File(path);
            new Thread(() ->
            {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    avatar.setImage(new Image(file.toURI().toString()));
                }
            }).start();

            System.out.println(path);
        }
    }


    @FXML
    void savedata(ActionEvent event) {


    }

    @FXML
    void setmembership(ActionEvent event) {
        try {
            Stage st = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/set_membership.fxml"));

            Parent sceneMain = loader.load();

            SetMembership controller = loader.<SetMembership>getController();
            controller.initVariable(member.getId());

            Scene scene = new Scene(sceneMain);
            st.setScene(scene);
            st.setMaximized(false);
            st.setTitle(member.getName());
            st.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void initVariable(Member member) {
        this.member = member;
    }
}
