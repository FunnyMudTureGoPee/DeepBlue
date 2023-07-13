package src.Scene;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HelpController {

    @FXML
    private Label back;

    @FXML
    private Pane ruler;

    @FXML
    private Pane story;
    @FXML
    private Label story1;
    @FXML
    private Label ruler1;

    @FXML
    void back(MouseEvent event) {
        Platform.runLater(() -> {
            Stage primaryStage = (Stage) back.getScene().getWindow();
            primaryStage.hide();
            try {
                new Main().start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    void disappear_ruler(MouseEvent event) {
        Platform.runLater(() -> {

            try {
                ruler.setOpacity(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    void disappear_story(MouseEvent event) {
        Platform.runLater(() -> {

            try {
                story.setOpacity(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    void display_ruler(MouseEvent event) {
        Platform.runLater(() -> {

            try {
                ruler.setOpacity(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    void display_story(MouseEvent event) {
        Platform.runLater(() -> {

            try {
                story.setOpacity(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    void initialize() {
        story1.setText(
            "暗蓝色的天空，以及在那之上的天空，人类的自由不再只拘泥于对未知空域的向往，将目光移向更高的天空——那片深蓝。\n随着太空电梯的建成，低成本高质量得将建材投送到同步轨道成为了可能。初代目行星际飞船驶出了太空船坞，尽管她的外貌跟跟二十一世纪初的太空站还是比较相似，但她还是是人类可以收集系内资源，探索行星。\n但是，资源分配问题照样引发了不公，一场阴谋正在酝酿……");
        ruler1.setText("游戏规则：\n在本游戏中，你将驾驶一架战舰通过燃烧燃料进行移动，并发射导弹击毁敌人的战舰来赢得游戏。\n移动系统：你可以通过按住键盘上的wasdqe键来移动战舰；w按住后会启动主推进器，会为你的战舰朝正前方提供较大的加速度，使得战舰能够迅速启动并保持高速行进状态；a和d键则可以为你的战舰进行向左和向右的平动操作；s键则可以为你的战舰向后方提供较小的加速度以便于战舰减速和停泊；而qe键则可以为你的战舰进行滚转操作，以便你在行进中改变方向。请注意：战舰的燃料是有限的，请谨慎节约的使用它们以确保你的燃料足够使用。\n攻击系统：按住空格键可以发射导弹来攻击敌方战舰，我方的导弹非常先进，拥有两节动力段：一级动力段的方向与你发射的方向相同，会以较缓慢的的速度靠近敌人；在进入到导弹自动搜索范围之后，导弹会启动二级动力段，以极高的速度锁定并撞向敌人完成攻击。\n防御系统：按住t键可以启动紫激光点防御系统，可以挡下敌人对你发射的导弹。\n注意事项：\n由于能源系统有限，在同一时间内你只可以进行一项操作，例如在启动防御系统时，你就不可以移动了。\n在游戏中，你拥有两条游戏辅助线：\n1.导弹锁定线：这条线连接了你的战舰与敌舰，方便你进行导弹的精确发射。\n2.领航线：为了防止你在太空中迷失，你的领航员以屏幕中心点的一颗行星作为基点并在你的战舰与此行星间建立了领航线，以便于你随时能知道你的战舰在宇宙中的相对位置。        ");
        assert back != null : "fx:id=\"back\" was not injected: check your FXML file 'help.fxml'.";
        assert ruler != null : "fx:id=\"ruler\" was not injected: check your FXML file 'help.fxml'.";
        assert story != null : "fx:id=\"story\" was not injected: check your FXML file 'help.fxml'.";

    }

}
