package src.GameObject;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import src.AppData;
import src.Scene.BattleSceneController;

public class FriendlyShip extends GameObject {
    protected boolean isEnemy;
    AttackModule attModule;
    PowerModule powerModule;
    SearchModule searchModule;
    
   

    public FriendlyShip(Label label) {
        super(100, 100, label);
        isEnemy = false;
        attModule = new AttackModule("测试导弹", this, (new Missile(AppData.enemyList,"eShip", new Label("自己的弹"))),100);
        powerModule = new PowerModule("测试推进器", "test", 100, 10, 1000, 1000, this);
        searchModule = new SearchModule("测试雷达", "test", "主动", 5000, 10, this);
        this.setId("fship");
        iv1= new ImageView("image/ship2.png");
        iv2= new ImageView("image/ship2_move.png");
    }

    @Override
    public void changeAcceleration(KeyCode key) {
        powerModule.manualOperationMode(key);
        gameLabel.setRotate(this.getDirection());
    }

    @Override
    public String toString() {
        return this.hPString() + "\n" + this.directionString() + "\n" + this.velocityString() + "\n"
                + powerModule.toString();
    }

    public String search() {
        Node n = searchModule.lockOn(AppData.enemyList,"eShip");
        if (n != null) {
            return n.toString();
        }
        return null;
    }

    public GameObject search(String s) {
        GameObject n = searchModule.lockOn(AppData.enemyList,"eShip");
        if (n != null) {
            return n;
        }
        return null;
    }
    

    public GameObject attack(KeyCode key) {
        if (key == KeyCode.SPACE) {
            long temp=System.currentTimeMillis()-attModule.timer;
            System.out.println(temp);
            if (temp>=this.attModule.CD) {
                Missile missile = (Missile) attModule.attackMissile( "eShip");
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
                missile.changeAcceleration();
                return missile;
            }
        }  
        return null;
    }

    @Override
    public void death(){
        if (hull <= 0) {
            AppData.friendlyList.remove(this);
            RCS = 0;
            gameLabel.setGraphic(null);
            gameLabel =null;
            this.setId(null);
            gameLabel.setText("");

        }
    }

public GameObject pointDefense(){
    GameObject o = searchModule.lockOn(AppData.missileList,"missile0", 200);
    if (o!=null) {
        double distance=this.getDistance(o);
        System.out.println(distance);
        if(distance>10&&distance<200){
            o.lostHP(1);
            return o;
        }
    }
    return null;
}

}
