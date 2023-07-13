package src.Scene;

// 导入JavaFX相关的包
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class save extends Application {

    public void start(Stage primaryStage) throws Exception {
        // 加载fxml文件
        FXMLLoader loader = new FXMLLoader(getClass().getResource("save.fxml"));
        // 获取根节点
        AnchorPane root = loader.load();
        Scene scene = new Scene(root, 540, 360);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}