package src.Scene;

import java.io.File;
import java.io.FileInputStream;

/**
 * Sample Skeleton for 'score.fxml' Controller Class
 */

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Pair;

public class scoreController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="back"
    private Label back; // Value injected by FXMLLoader

    @FXML // fx:id="label"
    private Label label; // Value injected by FXMLLoader

    @FXML // fx:id="label1"
    private Label label1; // Value injected by FXMLLoader

    @FXML // fx:id="label2"
    private Label label2; // Value injected by FXMLLoader

    @FXML // fx:id="label3"
    private Label label3; // Value injected by FXMLLoader

    @FXML // fx:id="label4"
    private Label label4; // Value injected by FXMLLoader

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
    public void displayScore() {
        String URL = "save";
        File file = new File(URL);
        File[] fs = file.listFiles();
        List<Pair<String,Integer>> list = new ArrayList<>();
        for (File file2 : fs) {
            try {
                if (!file2.isDirectory()) {
                    byte[] data = new byte[1024];
                    FileInputStream fis = new FileInputStream(file2);
                    // 读取文件的内容，并存储到字节数组中
                    int len = fis.read(data);
                    // 关闭输入流
                    fis.close();
                    String content = new String(data, 0, len);

                    list.add(new Pair<>(file2.getName(), content.length()));
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

        list.sort(Comparator.comparing(p -> -p.getValue()));
        List<Label> labels =new ArrayList<>();
        labels.add(label);
        labels.add(label1);
        labels.add(label2);
        labels.add(label3);
        labels.add(label4);


        for(int i=0;i<list.size()&&i<5;i++){
            Label l=labels.get(i);
            l.setText(list.get(i).toString());
            labels.set(i, l);
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert back != null : "fx:id=\"back\" was not injected: check your FXML file 'score.fxml'.";
        assert label != null : "fx:id=\"label\" was not injected: check your FXML file 'score.fxml'.";
        assert label1 != null : "fx:id=\"label1\" was not injected: check your FXML file 'score.fxml'.";
        assert label2 != null : "fx:id=\"label2\" was not injected: check your FXML file 'score.fxml'.";
        assert label3 != null : "fx:id=\"label3\" was not injected: check your FXML file 'score.fxml'.";
        assert label4 != null : "fx:id=\"label4\" was not injected: check your FXML file 'score.fxml'.";

    }

}
