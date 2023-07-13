package src.GameObject;

import javafx.scene.control.Label;

public class AttackModule extends Module {

    // 所依赖的游戏物体
    GameObject o;
    // 产生的攻击物
    Missile attackGameObject;

    long CD;

    long timer;

    public AttackModule(String name, String introduce, GameObject o, Missile attGO, long CD) {
        this.name = name;
        this.type = "Attack_Module";
        this.introduce = introduce;
        attackGameObject = attGO;
        this.o = o;
        this.CD = CD;
        timer = System.currentTimeMillis();
    }

    // missle
    public AttackModule(String name, GameObject o, Missile attGO, long CD) {
        this(name, "missile",  o,attGO, CD);
    }

    public Missile attackMissile(String target) {

        Missile missile = new Missile(attackGameObject.targetList, attackGameObject.target, new Label(attackGameObject.getLabel().getText()));
        timer = System.currentTimeMillis();
        return missile;
    }

    public Missile attackMissile(String target, PowerModule pm, SearchModule sm) {
        Missile missile = new Missile(attackGameObject.targetList, attackGameObject.target, new Label(attackGameObject.getLabel().getText()),pm,sm);
        timer = System.currentTimeMillis();
        return missile;

    }

}
