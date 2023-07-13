package src.Scene;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import src.GameObject.FriendlyShip;
import src.GameObject.GameObject;
import src.GameObject.Missile;

public class Scene1Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label friendlyShip;

    @FXML
    private Label label3;

    @FXML
    private Label label4;
    @FXML
    private AnchorPane scene;

    @FXML
    private Line line;

    public FriendlyShip fship;

    long timer;

    @FXML
    void addObject() {
        fship.setCoordinate(friendlyShip.getLayoutX(), friendlyShip.getLayoutY());

        scene.getChildren().add(fship);
        timer = System.currentTimeMillis();

    }

    @FXML

    void reFPS(double fps) {
        label3.setText("FPS: " + String.format("%.2f", fps));
        line.setEndX(fship.getCoordinateX() - 540);
        line.setEndY(fship.getCoordinateY() - 360);

    }

    public void victory() {
        if (fship.getCoordinateX() > 833 && fship.getCoordinateX() < 883 && fship.getCoordinateY() > 126
                && fship.getCoordinateY() < 166 && fship.getVelocityX() < 0.01 && fship.getVelocityX() > -0.01
                && fship.getVelocityY() < 0.01 && fship.getVelocityY() > -0.01) {
            System.out.println("V");
            fship.gameLabel.setLayoutX(0);
            Stage primaryStage = (Stage) label3.getScene().getWindow();
            primaryStage.hide();
            try {
                new save().start(primaryStage);
                String filepath = "save";
                File file = new File(filepath + "\\" + Main.USERNAME);
                FileOutputStream fos = new FileOutputStream(file, true);
                fos.write("#".getBytes());
                fos.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
    }

    @FXML
    void ship_moved(Scene s) {
        fship.setCoordinate(friendlyShip.getLayoutX(), friendlyShip.getLayoutY());

        // iv2.setTranslateY(-12);
        s.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            fship.changeAcceleration(code);

        });
        fship.positionDetection();
        fship.move();
        label4.setText("飞船坐标" + friendlyShip.getLayoutX() + "," + friendlyShip.getLayoutY() + "\n"
                + String.format(fship.toString()));
    }

    @FXML
    void initialize() {
        fship = new FriendlyShip(friendlyShip);
        assert friendlyShip != null : "fx:id=\"friendlyShip\" was not injected: check your FXML file 'Scene1.fxml'.";
        assert label4 != null : "fx:id=\"label4\" was not injected: check your FXML file 'Scene1.fxml'.";
        assert line != null : "fx:id=\"line\" was not injected: check your FXML file 'Scene1.fxml'.";

    }

}
