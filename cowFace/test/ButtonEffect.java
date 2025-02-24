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


public class ButtonEffect extends Application {


    @Override
    public void start(Stage primaryStage) {
        // 创建按钮
        Button button = new Button("Click Me");


        // 设置按钮的样式
        button.setStyle("-fx-border: none; " +
                "-fx-background-color: linear-gradient(from 0% 0% to 0% 100%, #fb2175 0%, #ea4c89 100%); " +
                "-fx-text-fill: white; " +
                "-fx-background-radius: 0;");


        // 鼠标悬停时的样式
        button.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                button.setOpacity(0.7);
            } else {
                button.setOpacity(1);
            }
        });


        // 鼠标点击时的样式
        button.setOnMousePressed(event -> {
            button.setEffect(new javafx.scene.effect.DropShadow(6, 4, 4, Color.rgb(255, 255, 255, 0.3)));
        });


        button.setOnMouseReleased(event -> {
            button.setEffect(null);
        });


        // 创建一个白色矩形作为闪亮动画的元素
        Rectangle shinyRect = new Rectangle(30, 0);
        shinyRect.setFill(Color.WHITE);
        shinyRect.setVisible(false);


        // 将矩形添加到按钮上
        button.setGraphic(shinyRect);


        // 创建闪亮动画
        Timeline shinyAnimation = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(shinyRect.translateYProperty(), -180), new KeyValue(shinyRect.opacityProperty(), 0), new KeyValue(shinyRect.scaleXProperty(), 0), new KeyValue(shinyRect.scaleYProperty(), 0)),
                new KeyFrame(Duration.seconds(3), new KeyValue(shinyRect.translateYProperty(), button.getHeight()), new KeyValue(shinyRect.opacityProperty(), 0), new KeyValue(shinyRect.scaleXProperty(), 0), new KeyValue(shinyRect.scaleYProperty(), 0)),
                new KeyFrame(Duration.seconds(3).subtract(Duration.millis(300)), new KeyValue(shinyRect.translateYProperty(), button.getHeight()), new KeyValue(shinyRect.opacityProperty(), 0.5), new KeyValue(shinyRect.scaleXProperty(), 0), new KeyValue(shinyRect.scaleYProperty(), 0)),
                new KeyFrame(Duration.seconds(3).subtract(Duration.millis(299)), new KeyValue(shinyRect.translateYProperty(), button.getHeight()), new KeyValue(shinyRect.opacityProperty(), 1), new KeyValue(shinyRect.scaleXProperty(), 4), new KeyValue(shinyRect.scaleYProperty(), 4)),
                new KeyFrame(Duration.seconds(3), new KeyValue(shinyRect.translateYProperty(), button.getHeight()), new KeyValue(shinyRect.opacityProperty(), 0), new KeyValue(shinyRect.scaleXProperty(), 50), new KeyValue(shinyRect.scaleYProperty(), 50))
        );


        shinyAnimation.setCycleCount(Animation.INDEFINITE);


        // 鼠标悬停时开始动画
        button.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                shinyRect.setVisible(true);
                shinyAnimation.playFromStart();
            } else {
                shinyRect.setVisible(false);
                shinyAnimation.stop();
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