package src.GameObject;

import java.text.DateFormat.Field;

import javax.accessibility.AccessibleValue;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import src.AppData;
import src.Scene.BattleSceneController;

public class GameObject extends Label {
    protected int hull;// 船体值
    protected int armor;// 装甲值
    protected double direction;// 方向角度
    protected double[] coordinate;// 坐标
    protected double[] velocity_vector;// 速度矢量
    protected double[] acceleration_vector;// 加速度矢量
    protected double RCS;// 雷达散射截面，该值越大啊，越易被探测到
    protected double weight;// 质量
    public Label gameLabel;
    protected ImageView iv1;
    protected ImageView iv2;

    /*
     * Cx x坐标，Cy y轴坐标，Vx 速度在x轴的分速度，Vy 速度在y轴的分速度， Ax 加速度在x轴的分加速度， Ay 加速度在x轴的分加速度
     */
    public GameObject(int hull, int armor, double Cx, double Cy, double direction, double Vx, double Vy, double Ax,
            double Ay, double R, double weight, Label label) {
        this.hull = hull;
        this.armor = hull;
        coordinate = new double[] { Cx, Cy };
        this.setLayoutX(Cx);
        this.setLayoutY(Cy);
        this.direction = direction;
        velocity_vector = new double[] { Vx, Vy };
        acceleration_vector = new double[] { Ax, Ay };
        RCS = R;
        this.weight = weight;
        gameLabel = label;

    }

    public GameObject(Label label) {
        this(0, 0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 5.0, 0.0, label);
    }

    public GameObject(int hull, int armor, Label label) {
        this(hull, armor, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 5.0, 1000, label);
    }

    public GameObject(double weight, Label label) {
        this(0, 0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 5.0, weight, label);
    }

    public void setDirection(double d) {
        direction = d;
    }

    public void setCoordinate(double x, double y) {
        coordinate[0] = x;
        coordinate[1] = y;
        this.setLayoutX(coordinate[0]);
        this.setLayoutY(coordinate[1]);
    }

    public void setVelocity(double x, double y) {
        velocity_vector[0] = x;
        velocity_vector[1] = y;
    }

    public void setLabel(Label label) {
        gameLabel = label;
    }

    // 移动
    public void move() {
        coordinate[0] = coordinate[0] + velocity_vector[0];
        coordinate[1] = coordinate[1] + velocity_vector[1];
        gameLabel.setLayoutX(this.getCoordinateX());
        gameLabel.setLayoutY(this.getCoordinateY());
        this.setLayoutX(coordinate[0]);
        this.setLayoutY(coordinate[1]);

    }

    // 由加速度改变速度
    public void changeSpeed() {
        velocity_vector[0] = velocity_vector[0] + acceleration_vector[0];
        velocity_vector[1] = velocity_vector[1] + acceleration_vector[1];
        acceleration_vector[0] = 0;
        acceleration_vector[1] = 0;
    }

    public void changeAcceleration() {
    }

    // 改变加速度以及角度
    public void changeAcceleration(KeyCode key) {
        if (key == KeyCode.W) {
            acceleration_vector[0] = (0.5 * Math.sin(Math.toRadians(direction)));
            acceleration_vector[1] = (-0.5 * Math.cos(Math.toRadians(direction)));
            changeSpeed();

        }
        if (key == KeyCode.A) {
            acceleration_vector[0] = (-0.01 * Math.cos(Math.toRadians(direction)));
            acceleration_vector[1] = (-0.01 * Math.sin(Math.toRadians(direction)));
            changeSpeed();
        }
        if (key == KeyCode.S) {
            acceleration_vector[0] = (-0.01 * Math.sin(Math.toRadians(direction)));
            acceleration_vector[1] = (0.01 * Math.cos(Math.toRadians(direction)));
            changeSpeed();
        }
        if (key == KeyCode.D) {
            acceleration_vector[0] = (0.01 * Math.cos(Math.toRadians(direction)));
            acceleration_vector[1] = (0.01 * Math.sin(Math.toRadians(direction)));
            changeSpeed();
        }
        if (key == KeyCode.Q) {
            direction = direction - 3;
        }
        if (key == KeyCode.E) {
            direction = direction + 3;
        }

    }


    public void death() {

    }

    public void lostHP(int damage) {
        hull = hull - damage;
        if (hull <= 0) {
            death();
        }
    }

    public void positionDetection() {
        if (this.coordinate[0] < -150) {
            this.coordinate[0] = 1230;
        }
        if (this.coordinate[1] < -150) {
            this.coordinate[1] = 870;
        }
        if (this.coordinate[0] > 1230) {
            this.coordinate[0] = -150;
        }
        if (this.coordinate[1] > 870) {
            this.coordinate[1] = -150;
        }

        gameLabel.setLayoutX(this.getCoordinateX());
        gameLabel.setLayoutY(this.getCoordinateY());
    }

    // 打印信息
    public String hPString() {
        return "装甲" + armor + "\n" + "船体" + hull;
    }

    public String directionString() {
        return "方位角" + direction % 360;
    }

    public String coordinateString() {
        return "坐标" + coordinate[0] + "," + coordinate[1];
    }

    public String velocityString() {
        return "速度矢量" + velocity_vector[0] + "," + velocity_vector[1];
    }

    public double getDistance(GameObject o) {
        return Math
                .sqrt(
                        (Math.pow(this.getLayoutX() - o.getCoordinateX(), 2)
                                + Math.pow(this.getLayoutY() - o.getCoordinateY(), 2)));
    }

    public double getDirection() {
        return direction;
    }

    public double getCoordinateX() {
        return coordinate[0];
    }

    public double getCoordinateY() {
        return coordinate[1];
    }

    public double getVelocityX() {
        return velocity_vector[0];
    }

    public double getVelocityY() {
        return velocity_vector[1];
    }

    public double getAccelerationX() {
        return acceleration_vector[0];
    }

    public double getAccelerationY() {
        return acceleration_vector[1];
    }

    public Label getLabel() {
        return gameLabel;
    }

    public GameObject getSelf() {
        return this;
    }

    public void boom() {
    }
}
