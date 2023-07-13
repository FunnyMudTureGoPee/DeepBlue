package src;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import src.GameObject.EnemyShip;
import src.GameObject.GameObject;
import src.Scene.BattleSceneController;

public class Others {


    public static double getTime(double a,double c, double d, double e) {
        double delta = (c / a) * (c / a) - 4 * ((d / a) + (e / a));
        // 如果判别式小于零，说明方程无实数解
        if (delta < 0) {
            //System.out.println("The equation has no real roots."+c+","+d+","+e);
        } else {
            // 如果判别式大于等于零，说明方程有实数解
            // 计算 y 的两个根
            double y1 = (-a + Math.sqrt(delta)) / 2;
            double y2 = (-a - Math.sqrt(delta)) / 2;
            // 计算 x 的四个根
            double x1 = Math.sqrt(y1);
            double x2 = -Math.sqrt(y1);
            double x3 = Math.sqrt(y2);
            double x4 = -Math.sqrt(y2);
            //System.out.println(x1);
            return x1;
        }
        return 0.0;
    }

    public static double getAlphe(double X, double Y) {
        double L = Math.sqrt(X * X + Y * Y);
        double cos = X / L;
        double sin = Y / L;
        double radian = Math.atan2(sin, cos); // 弧度制的角
        double degree = Math.toDegrees(radian) + 90;
        return degree;
    }

    public static void reFreshEnemy(List<GameObject> enemyList,Label l){
        if(enemyList.size()<2){
            Label nel = new Label(null);
            EnemyShip newEnemyShip = new EnemyShip(nel);
            AppData.enemyList.add(newEnemyShip);
            AppData.gameLabelList.add(nel);
            AnchorPane root = (AnchorPane) l.getScene().getRoot();
            root.getChildren().add(nel);
        }
    }
}
