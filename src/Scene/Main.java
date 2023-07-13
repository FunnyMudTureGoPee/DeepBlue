package src.Scene;

// 导入JavaFX相关的包
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static String USERNAME;
    public static int USESCORE;

    public void start(Stage primaryStage) throws Exception {
        // 加载fxml文件
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScene.fxml"));
        // 获取根节点
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 1080, 720);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
