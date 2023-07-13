package src.Scene;


import javafx.animation.AnimationTimer;
// 导入JavaFX相关的包
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;

// 定义一个类继承自Application
public class Battle extends Application {
    private long lastUpdate = 0; // 上一帧的时间
    // 重写start方法

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 加载fxml文件
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BattleScene.fxml"));
        // 获取根节点
        AnchorPane root = loader.load();
        BattleSceneController controller = (BattleSceneController) loader.getController();

        // 创建一个场景用于显示根节点
        Scene scene = new Scene(root, 1080, 720);
        
        // 设置舞台的标题和场景
        primaryStage.setTitle("深蓝");
        primaryStage.setScene(scene);
        
        controller.addObject();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // 计算时间差（以秒为单位）
                double delta = (now - lastUpdate) / 1_000_000_000.0;
                // 更新上一帧的时间
                lastUpdate = now;
                // 更新FPS（每秒更新一次）

                controller.reFPS(1 / delta);
                controller.mouse_moved(primaryStage);
                controller.ship_moved(scene);
            }
        };
        timer.start();
        primaryStage.setResizable(false);

        // 显示舞台
        primaryStage.show();
        
    }

    // 主方法，启动应用程序
    public static void main(String[] args) {
        launch(args);

    }
}
