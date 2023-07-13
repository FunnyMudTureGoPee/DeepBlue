package src.GameObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javafx.print.Collation;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import src.AppData;
import src.Scene.BattleSceneController;

public class EnemyShip extends GameObject {
    protected boolean isEnemy;

    AttackModule attModule;
    PowerModule powerModule;
    SearchModule searchModule;

    public EnemyShip(Label label) {
        super(10, 10, label);
        isEnemy = true;
        attModule = new AttackModule("测试导弹", this, (new Missile(AppData.friendlyList, "fship", new Label("敌人的弹"))),
                3000);
        powerModule = new PowerModule("测试发动机", "安装在敌人身上的测发动机", 0.2, 1, 100000, 1000, this);
        searchModule = new SearchModule("测试雷达", "test", "主动", 1000, 10, this);
        this.setId("eShip");
        iv1 = new ImageView("image/ship1.png");
        iv2 = new ImageView("image/ship1_move.png");

    }

    @Override
    public void changeAcceleration() {
        Node n = searchModule.lockOn(AppData.friendlyList, "fship");
        powerModule.wanderingMode(n, this);
        gameLabel.setRotate(this.getDirection());

    }

    @Override
    public String toString() {
        return this.hPString() + "\n" + this.coordinateString() + "\n" + this.directionString() + "\n"
                + this.velocityString() + "\n";
    }

    @Override
    public void death() {
        if (hull <= 0) {
            AppData.enemyList.remove(this);
            RCS = 0;
            //gameLabel.setGraphic(null);
            gameLabel.setText("");
            gameLabel.setGraphic(this.iv2);
            gameLabel = null;
            this.setId(null);
        }
    }

    public String search() {
        Node n = searchModule.lockOn(AppData.friendlyList, "fship");
        if (n != null) {
            return n.toString();
        }
        return null;
    }
    //敌人自主攻击
    public GameObject attack() {
        Node n = searchModule.lockOn(AppData.friendlyList, "fship");
        if (n != null) {
            Double distance = Math.sqrt(Math.pow(n.getLayoutX() - this.getCoordinateX(), 2)
                    + Math.pow(n.getLayoutY() - this.getCoordinateY(), 2));

            if (distance <= 350) {
                long temp = System.currentTimeMillis() - attModule.timer;
                if (temp >= this.attModule.CD) {
                    Missile missile = (Missile) attModule.attackMissile("fship");
                    missile.getLabel().setLayoutX(this.getCoordinateX());
                    missile.getLabel().setLayoutY(this.getCoordinateY());
                    missile.setCoordinate(this.getCoordinateX(), this.getCoordinateY());
                    missile.setDirection(this.getDirection());
                    missile.setVelocity(this.getVelocityX(), this.getVelocityY());
                    missile.changeAcceleration(KeyCode.W);
                    AnchorPane root = (AnchorPane) gameLabel.getScene().getRoot();
                    root.getChildren().add(missile.getLabel());
                    AppData.gameLabelList.add(missile.getLabel());
                    root.getChildren().add(missile);
                    AppData.missileList.add(missile);
                    missile.changeAcceleration();
                    return missile;
                }
            }

        }
        return null;
    }

}
