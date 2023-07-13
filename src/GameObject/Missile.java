package src.GameObject;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import src.AppData;
import src.Scene.BattleSceneController;

public class Missile extends GameObject {
    protected int damage;
    protected double range;
    PowerModule powerModule;
    SearchModule searchModule;
    List<GameObject> targetList;
    String target;

    public Missile(int hull, int armor, double Cx, double Cy, double direction, double Vx, double Vy, double Ax,
            double Ay, double R, double weight, Label label) {
        super(hull, armor, Cx, Cy, direction, Vx, Vy, Ax, Ay, R, weight, label);
        setId("missile0");
        iv1 = new ImageView("image/missile1.png");
        iv2 = new ImageView("image/missile1.png");
    }

    public Missile(List<GameObject> targetList, String target, Label label) {
        super(10.0, label);
        this.targetList = targetList;
        this.target = target;
        range = 20;
        damage = 2;
        hull = 3;
        setId("missile0");
        powerModule = new PowerModule("测试发动机", "安装在导弹身上的测发动机", 0.1, 1, 500, 1, this);
        searchModule = new SearchModule("测试雷达", "test", "主动", 1000, 10, this);
        iv1 = new ImageView("image/missile1.png");
        iv2 = new ImageView("image/missile1.png");
    }

    public Missile(List<GameObject> targetList, String target, Label label, PowerModule pm, SearchModule sm) {

        super(10.0, label);
        this.targetList = targetList;
        this.target = target;
        range = 20;
        damage = 2;
        setId("missile0");
        powerModule = pm;
        searchModule = sm;
        iv1 = new ImageView("image/missile1.png");
        iv2 = new ImageView("image/missile1.png");
    }

    @Override
    public void death() {
        if (hull <= 0) {
            AppData.missileList.remove(this);
            RCS = 0;
            this.setId(null);
            gameLabel.setText("");
            gameLabel.setGraphic(null);
            gameLabel = null;
        }
    }

    @Override
    public void changeAcceleration() {
        GameObject n = searchModule.lockOn(targetList, target);

        powerModule.SecondardPowerTrackMode(n, this);
        gameLabel.setRotate(this.getDirection());

    }

    // 爆炸功能
    @Override
    public void boom() {
        GameObject n = (GameObject) searchModule.lockOn(targetList, target, range);
        if (n != null) {
            n.lostHP(damage);
            n.death();
            // n.gameLabel.setText("");
            // MousePositionController.gameLabelList.remove(n.gameLabel);
            // MousePositionController.gamelList.remove(n);
            this.lostHP(10);
            this.death();
            this.gameLabel.setText("");
            AppData.gameLabelList.remove(this.gameLabel);
            AppData.missileList.remove(this);

            if (powerModule.main_power_reserve == 0) {
                n.death();
            }
            if (this.getCoordinateX() > 1330 || this.getCoordinateX() < -150) {
                n.death();
            }
            if (this.getCoordinateY() > 770 || this.getCoordinateY() < -150) {
                n.death();
            }
        }
    }

}
