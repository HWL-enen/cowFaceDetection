import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class CustomTimePickerExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 创建小时的下拉框
        ComboBox<Integer> hourComboBox = new ComboBox<>();
        for (int i = 0; i < 24; i++) {
            hourComboBox.getItems().add(i);
        }
        hourComboBox.setValue(0);

        // 创建分钟的下拉框
        ComboBox<Integer> minuteComboBox = new ComboBox<>();
        for (int i = 0; i < 60; i++) {
            minuteComboBox.getItems().add(i);
        }
        minuteComboBox.setValue(0);

        // 监听选择事件
        hourComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("选择的时间是: " + newValue + ":" + minuteComboBox.getValue());
        });
        minuteComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("选择的时间是: " + hourComboBox.getValue() + ":" + newValue);
        });

        // 创建标签
        Label timeLabel = new Label("选择时间:");

        // 创建垂直布局
        VBox vbox = new VBox(timeLabel, hourComboBox, minuteComboBox);
        // 创建场景
        Scene scene = new Scene(vbox, 200, 150);

        // 设置舞台标题
        primaryStage.setTitle("自定义时间选择器示例");
        // 设置场景
        primaryStage.setScene(scene);
        // 显示舞台
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}