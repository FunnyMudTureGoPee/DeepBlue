package src.Scene;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class saveController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField URL;

    @FXML
    private Label next;

    @FXML
    private Label re;

    @FXML
    void next(MouseEvent event) {
        Main.USERNAME = URL.getText();
        String filepath = "save";
        File file = new File(filepath + "\\" + Main.USERNAME);
        byte[] data = new byte[1024];
        try {
            // 如果文件不存在，就创建一个新的文件
            if (!file.exists()) {
                file.createNewFile();
                FileOutputStream fos = new FileOutputStream(file, true);
                fos.write("#".getBytes());
                // fos.write();
                fos.close();

            }
            // 创建一个FileInputStream对象，用于从文件中读取数据
            FileInputStream fis = new FileInputStream(file);
            // 读取文件的内容，并存储到字节数组中
            int len = fis.read(data);
            // 关闭输入流
            fis.close();
            String content = new String(data, 0, len);
            // 打印读取到的内容
            System.out.println("从文件中读取到的内容是：");
            System.out.println(content);
            FileOutputStream fost = new FileOutputStream(file, true);
            fost.close();
            if (content.contains("scene1#")) {
                FileOutputStream fos = new FileOutputStream(file, true);
                fos.write("scene2".getBytes());
                // fos.write();
                fos.close();
                Stage primaryStage = (Stage) next.getScene().getWindow();
                primaryStage.hide();
                try {
                    new Battle().start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else  {
                FileOutputStream fos = new FileOutputStream(file,true);
                fos.write("scene1".getBytes());
                fos.close();
                Stage primaryStage = (Stage) next.getScene().getWindow();
                primaryStage.hide();
                try {
                    new Scene1().start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        } catch (IOException e) {
            // 如果发生异常，打印异常信息
            e.printStackTrace();
        }
    }

    @FXML
    void re(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert URL != null : "fx:id=\"URL\" was not injected: check your FXML file 'save.fxml'.";

    }

}
