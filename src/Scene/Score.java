package src.Scene;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Score extends Application {

    public void start(Stage primaryStage) throws Exception {
        // 加载fxml文件
        FXMLLoader loader = new FXMLLoader(getClass().getResource("score.fxml"));
        // 获取根节点
        AnchorPane root = loader.load();
        scoreController controller = (scoreController)loader.getController();
        controller.displayScore();

        Scene scene = new Scene(root, 1080, 720);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}