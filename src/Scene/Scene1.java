package src.Scene;

import java.io.File;
import java.io.FileOutputStream;

import javafx.animation.AnimationTimer;
// 导入JavaFX相关的包
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Scene1 extends Application {
    private long lastUpdate = 0; // 上一帧的时间

    public void start(Stage primaryStage) throws Exception {
        // 加载fxml文件
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene1.fxml"));
        // 获取根节点
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 1080, 720);
        primaryStage.setScene(scene);

        Scene1Controller controller = (Scene1Controller) loader.getController();
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

                controller.ship_moved(scene);
                controller.victory();
            }
        };
        timer.start();
        primaryStage.setResizable(false);

        // 显示舞台
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
