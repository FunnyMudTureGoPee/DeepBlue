

package src.Scene;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainSceneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label start;
    @FXML
    private Label notice;

    @FXML
    void exit(MouseEvent event) {
        notice.setVisible(true);
    }

    @FXML
    void toHelp(MouseEvent event) {
        Platform.runLater(()->{
            Stage primaryStage = (Stage)start.getScene().getWindow();
            primaryStage.hide();
            try {
                new Help().start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        );
    }

    @FXML
    void toScore(MouseEvent event) {
        Platform.runLater(()->{
            Stage primaryStage = (Stage)start.getScene().getWindow();
            primaryStage.hide();
            try {
                new Score().start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        );
    }

    @FXML
    void toStart(MouseEvent event) {
        Platform.runLater(()->{
            Stage primaryStage = (Stage)start.getScene().getWindow();
            primaryStage.hide();
            try {
                new save().start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        );
    }




    @FXML
    void initialize() {
        assert start != null : "fx:id=\"start\" was not injected: check your FXML file 'MainScene.fxml'.";

    }


}
