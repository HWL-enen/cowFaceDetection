import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class ButtonEffect10 extends Application {


    @Override
    public void start(Stage primaryStage) {
        // 创建按钮
        Button button = new Button("Click Me");


        // 设置按钮的基本样式
        button.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 0% 100%, rgba(22,9,240,1) 0%, rgba(49,110,244,1) 100%);" +
                "-fx-text-fill: white;" +
                "-fx-border: none;" +
                "-fx-background-radius: 0;");


        // 创建一个矩形作为伪元素的效果
        Rectangle pseudoElement = new Rectangle(0, 0);
        pseudoElement.setFill(Color.TRANSPARENT);


        // 将矩形添加到按钮上
        button.setGraphic(pseudoElement);


        // 为按钮添加鼠标悬停效果
        button.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                // 改变按钮的背景为透明
                button.setStyle("-fx-background-color: transparent;" +
                        "-fx-text-fill: white;" +
                        "-fx-border: none;");


                // 改变伪元素的背景和大小
                pseudoElement.setFill(Color.web("rgba(0,3,255,1)"));
                Timeline timeline = new Timeline(
                        new KeyFrame(Duration.ZERO,
                                new KeyValue(pseudoElement.widthProperty(), 0),
                                new KeyValue(pseudoElement.heightProperty(), 0),
                                new KeyValue(pseudoElement.scaleXProperty(), 0.1),
                                new KeyValue(pseudoElement.scaleYProperty(), 0.1)
                        ),
                        new KeyFrame(Duration.seconds(0.3),
                                new KeyValue(pseudoElement.widthProperty(), button.getWidth()),
                                new KeyValue(pseudoElement.heightProperty(), button.getHeight()),
                                new KeyValue(pseudoElement.scaleXProperty(), 1),
                                new KeyValue(pseudoElement.scaleYProperty(), 1)
                        )
                );
                timeline.setOnFinished(event -> {
                    // 确保伪元素的尺寸和按钮完全匹配
                    pseudoElement.setWidth(button.getWidth());
                    pseudoElement.setHeight(button.getHeight());
                });
                timeline.play();
            } else {
                // 恢复按钮的原始样式
                button.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 0% 100%, rgba(22,9,240,1) 0%, rgba(49,110,244,1) 100%);" +
                        "-fx-text-fill: white;" +
                        "-fx-border: none;");


                // 重置伪元素的状态
                pseudoElement.setFill(Color.TRANSPARENT);
                pseudoElement.setScaleX(0.1);
                pseudoElement.setScaleY(0.1);
                pseudoElement.setWidth(0);
                pseudoElement.setHeight(0);
            }
        });


        StackPane root = new StackPane(button);
        Scene scene = new Scene(root, 300, 250);


        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}