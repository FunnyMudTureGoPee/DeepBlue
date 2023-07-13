package src.GameObject;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import src.AppData;
import src.Others;

public class PowerModule extends Module {
    // 主推进器单位时间输出动量
    protected double main_power;
    // 姿态控制器单位时间输出动量
    private double rcs_power;
    // 主推进器动量储备时间
    protected double main_power_reserve;
    // 姿态控制器动量储备时间
    private double rcs_power_reserve;

    double MAX_main_power_reserve;

    GameObject o;
    double ANGLE = 0.0;

    public PowerModule(String name, String introduce, double main_power, double rcs_power, double main_power_reserve,
            double rcs_power_reserve, GameObject o) {
        MAX_main_power_reserve = main_power_reserve;
        this.name = name;
        this.type = "power";
        this.introduce = introduce;
        this.main_power = main_power;
        this.rcs_power = rcs_power;
        this.main_power_reserve = main_power_reserve;
        this.rcs_power_reserve = rcs_power_reserve;
        this.o = o;
    }

    // 手动操作模式
    public void manualOperationMode(KeyCode key) {
        o.gameLabel.setGraphic(o.iv1);
        if (key == KeyCode.W) {
            if (main_power_reserve > 0) {
                o.acceleration_vector[0] = (main_power / o.weight * Math.sin(Math.toRadians(o.direction)));
                o.acceleration_vector[1] = (-main_power / o.weight * Math.cos(Math.toRadians(o.direction)));
                o.changeSpeed();
                main_power_reserve--;
                o.gameLabel.setGraphic(o.iv2);
            }

        }
        if (key == KeyCode.A) {
            if (rcs_power_reserve > 0) {
                o.acceleration_vector[0] = (-rcs_power / o.weight * Math.cos(Math.toRadians(o.direction)));
                o.acceleration_vector[1] = (-rcs_power / o.weight * Math.sin(Math.toRadians(o.direction)));
                o.changeSpeed();
                rcs_power_reserve--;
            }

        }
        if (key == KeyCode.S) {
            if (rcs_power_reserve > 0) {
                o.acceleration_vector[0] = (-rcs_power / o.weight * Math.sin(Math.toRadians(o.direction)));
                o.acceleration_vector[1] = (rcs_power / o.weight * Math.cos(Math.toRadians(o.direction)));
                o.changeSpeed();
                rcs_power_reserve--;
            }
        }
        if (key == KeyCode.D) {
            if (rcs_power_reserve > 0) {
                o.acceleration_vector[0] = (rcs_power / o.weight * Math.cos(Math.toRadians(o.direction)));
                o.acceleration_vector[1] = (rcs_power / o.weight * Math.sin(Math.toRadians(o.direction)));
                o.changeSpeed();
                rcs_power_reserve--;
            }
        }
        if (key == KeyCode.Q) {
            if (rcs_power_reserve > 0) {
                o.direction = o.direction - 300 * rcs_power / o.weight;
                rcs_power_reserve--;
            }

        }
        if (key == KeyCode.E) {
            if (rcs_power_reserve > 0) {
                o.direction = o.direction + 300 * rcs_power / o.weight;
                rcs_power_reserve--;
            }
        }
    }

    // 角追踪模式
    public void angleTrackMode(GameObject e, GameObject o) {
        if (e != null) {
            double a = -(Math.pow(o.getVelocityX() - e.getVelocityX(), 2)
                    + Math.pow(o.getVelocityY() - e.getVelocityY(), 2));
            double b = -(2 * ((e.getCoordinateX() - o.getCoordinateX()) * (o.getVelocityX() - e.getVelocityX())
                    + (e.getCoordinateY() - o.getCoordinateY()) * (o.getVelocityY() - e.getVelocityY())));
            double c = -(2 * (Math.pow(e.getCoordinateX() - o.getCoordinateX(), 2))
                    + 2 * (Math.pow(e.getCoordinateY() - o.getCoordinateY(), 2)));
            Double A = this.main_power/o.weight;
            Double time = Others.getTime(A, a, b, c);

            double X = e.getCoordinateX() - o.getCoordinateX() + 0.5 * e.getVelocityX() * time;
            double Y = e.getCoordinateY() - o.getCoordinateY() + 0.5 * e.getVelocityY() * time;
             
            if (ANGLE == 0.0) {
                ANGLE =-Others.getAlphe(X, Y)+e.getDirection();
            }else{
                System.out.println(e.getDirection()-ANGLE);
                o.setDirection(e.getDirection()-ANGLE);
                this.manualOperationMode(KeyCode.W);
            }
        }
    }

    // 笨弹制导模式
    public void easyTrackMode(Node n, GameObject o) {
        if (n != null) {
            double X = o.getCoordinateX() - n.getLayoutX();
            double Y = o.getCoordinateY() - n.getLayoutY();
            double L = Math.sqrt(X * X + Y * Y);
            double cos = X / L;
            double sin = Y / L;
            double radian = Math.atan2(sin, cos); // 弧度制的角
            double degree = Math.toDegrees(radian) - 90;
            o.setDirection(degree);

            this.manualOperationMode(KeyCode.W);
        }
    }

    // 二级动力段
    public void SecondardPowerTrackMode(GameObject n, GameObject o) {
        if (this.main_power_reserve >= MAX_main_power_reserve / 4) {
            this.easyTrackMode(n, o);
        }
        GameObject go = ((Missile) o).searchModule.lockOn(AppData.enemyList,"eShip", 100);
        if (go != null) {
            this.main_power=1;
            this.main_power_reserve=150;
            this.angleTrackMode(n, o);
        }
    }

    // 自主徘徊模式
    public void wanderingMode(Node n, GameObject o) {
        if (n != null) {
            double X = o.getCoordinateX() - n.getLayoutX();
            double Y = o.getCoordinateY() - n.getLayoutY();
            double L = Math.sqrt(X * X + Y * Y);
            double cos = X / L;
            double sin = Y / L;
            double radian = Math.atan2(sin, cos); // 弧度制的角
            double degree = Math.toDegrees(radian) + 90;
            if (L <= 300) {
                o.setDirection(degree);
            } else {
                o.setDirection(degree - 180);
            }

            this.manualOperationMode(KeyCode.W);
        }
    }

    public String toString() {
        return "主推进器剩余储备" + main_power_reserve + "\n" + "姿态控制器剩余储备" + rcs_power_reserve;
    }

}
