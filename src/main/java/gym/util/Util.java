package gym.util;

import com.jfoenix.controls.JFXButton;
import gym.Controllers.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

public class Util {

    public static int getDiffYears(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(YEAR) - a.get(YEAR);
        if (a.get(MONTH) > b.get(MONTH) ||
                (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
            diff--;
        }
        return diff;
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        cal.setTime(date);
        return cal;
    }

    private static final String IMAGE_LOC = "/resources/icon.png";
    public static String saveavatartodir(JFXButton updateavatar) {
        String avatarpath ="";
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter JPGFilter = new FileChooser.ExtensionFilter("*.JPG","*.JPG");
        FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("*.jpg","*.jpg");
        FileChooser.ExtensionFilter PNGFilter = new FileChooser.ExtensionFilter("*.PNG","*.PNG");
        FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("*.png","*.png");
        fileChooser.getExtensionFilters().addAll(JPGFilter,jpgFilter,PNGFilter,pngFilter);

        File file = fileChooser.showOpenDialog(updateavatar.getScene().getWindow());
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
//            avatar.setImage(new Image(newFile.toURI().toString()));
             avatarpath = newFile.getPath().substring(18).replace("\\", "/");

        }
        return avatarpath;
    }

    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
    public static void loadWindow(URL loc, String title, Stage parentStage, Object data) {
        try {
            Parent parent = FXMLLoader.load(loc);
            Stage stage = null;
            if (parentStage != null) {
                stage = parentStage;
            } else {
                stage = new Stage(StageStyle.DECORATED);
            }
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void copy(File src, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(src);
            os = new FileOutputStream(dest);
            // buffer size 1K
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buf)) > 0) {
                os.write(buf, 0, bytesRead);
            }
        } finally {
            is.close();
            os.close();
        }
    }


}
