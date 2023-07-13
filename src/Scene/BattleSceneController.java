package src.Scene;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.Light.Point;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import src.AppData;
import src.Others;
import src.GameObject.EnemyShip;
import src.GameObject.FriendlyShip;
import src.GameObject.GameObject;
import src.GameObject.Missile;
import javafx.fxml.*;

public class BattleSceneController implements Initializable {



    @FXML
    private AnchorPane scene;
    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private Label friendlyShip;

    @FXML
    private Label enemyShip;
    @FXML
    private Label enemyShip2;
    @FXML
    private Label enemyShip3;

    @FXML
    private Label missile;

    @FXML
    private Label label4;

    @FXML
    private Label label5;

    @FXML
    private Line line;
    @FXML
    public Line attline;
    @FXML
    public Line pointline;

    public FriendlyShip fship;

    public EnemyShip eShip;
    public EnemyShip eShip2;
    public EnemyShip eShip3;

    public Missile fox;

    @FXML
    void addObject() {
        fship.setCoordinate(friendlyShip.getLayoutX(), friendlyShip.getLayoutY());
        eShip.setCoordinate(enemyShip.getLayoutX(), enemyShip.getLayoutY());
        eShip2.setCoordinate(enemyShip2.getLayoutX(), enemyShip2.getLayoutY());
        eShip3.setCoordinate(enemyShip3.getLayoutX(), enemyShip3.getLayoutY());
        fox.setCoordinate(missile.getLayoutX(), missile.getLayoutY());
        scene.getChildren().add(fship);
        scene.getChildren().add(eShip);
        scene.getChildren().add(fox);
        scene.getChildren().add(eShip2);
        scene.getChildren().add(eShip3);
    }

    @FXML

    void reFPS(double fps) {
        label3.setText("FPS: " + String.format("%.2f", fps));
        label5.setText("锁定对象：" + fship.search());
        line.setEndX(fship.getCoordinateX() - 540);
        line.setEndY(fship.getCoordinateY() - 360);
        attline.setLayoutX(fship.getCoordinateX());
        attline.setLayoutY(fship.getCoordinateY());
        GameObject o = fship.search("");
        Others.reFreshEnemy(AppData.enemyList,label1);
        if (o != null) {
            attline.setEndX(o.getCoordinateX() - fship.getCoordinateX());
            attline.setEndY(o.getCoordinateY() - fship.getCoordinateY());
        } else {
            attline.setOpacity(0.0);
        }

    }

    @FXML
    void ship_moved(Scene s) {
        fship.setCoordinate(friendlyShip.getLayoutX(), friendlyShip.getLayoutY());
       
        
        //iv2.setTranslateY(-12);
        s.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            fship.changeAcceleration(code);
            
            
            if (code == KeyCode.T) {
                pointline.setOpacity(0);
                GameObject o = fship.pointDefense();
                if (o != null) {

                    pointline.setLayoutX(fship.getCoordinateX());
                    pointline.setLayoutY(fship.getCoordinateY());
                    pointline.setStartX(0);
                    pointline.setStartY(0);
                    pointline.setEndX(o.getCoordinateX() - fship.getCoordinateX());
                    pointline.setEndY(o.getCoordinateY() - fship.getCoordinateY());
                    pointline.setOpacity(50);

                }

            }

            if (code == KeyCode.SPACE) {
                GameObject o = fship.attack(code);
                if (o != null) {
                    AppData.missileList.add(o);
                }

            }
            
        });
        fship.positionDetection();
        try {
            for (GameObject missile : AppData.missileList) {
                missile.changeAcceleration();
                missile.move();
                missile.boom();

            }
            for (GameObject go : AppData.enemyList) {
                ((EnemyShip)go).attack();
                go.positionDetection();
                go.changeAcceleration();
                go.move();
                go.boom();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        fship.move();
        label4.setText("飞船坐标" + friendlyShip.getLayoutX() + "," + friendlyShip.getLayoutY() + "\n"
                + String.format(fship.toString()));
    }

    @FXML
    void mouse_moved(Stage s) {
        s.getScene().setOnMouseMoved(event -> {
            // 获取鼠标的x和y坐标
            double x = event.getX();
            double y = event.getY();

            // 更新标签的文本
            label1.setText("鼠标位置：" + x + ", " + y);

        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        AppData.enemyList = new ArrayList<>();
        AppData.gameLabelList = new ArrayList<>();
        AppData.friendlyList = new ArrayList<>();
        AppData.missileList = new ArrayList<>();

        fship = new FriendlyShip(friendlyShip);
        eShip = new EnemyShip(enemyShip);
        eShip2 = new EnemyShip(enemyShip2);
        eShip3 = new EnemyShip(enemyShip3);
        fox = new Missile(AppData.enemyList,"eShip", missile);

        eShip.setId("eShip");
        eShip2.setId("eShip");
        eShip3.setId("eShip");

        AppData.friendlyList.add(fship);
        AppData.enemyList.add(eShip);
        AppData.missileList.add(fox);
        AppData.enemyList.add(eShip2);
        AppData.enemyList.add(eShip3);

        AppData.gameLabelList.add(friendlyShip);
        AppData.gameLabelList.add(enemyShip);
        AppData.gameLabelList.add(enemyShip2);
        AppData.gameLabelList.add(enemyShip3);
        AppData.gameLabelList.add(missile);

        

    }

}
