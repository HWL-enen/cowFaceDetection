import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class RoundedWindow extends Application {
    @Override
    public void start(Stage primaryStage) {

                Button button = new Button("Click Me");
                button.getStyleClass().add("btn-11");


                // 创建一个矩形作为闪亮元素
                Rectangle shinyRect = new Rectangle(30, 0);
                shinyRect.getStyleClass().add("shiny-rect");


                // 将矩形添加到按钮上
                button.setGraphic(shinyRect);


                StackPane root = new StackPane(button);


                // 加载 CSS 文件
                Scene scene = new Scene(root, 300, 250);
                scene.getStylesheets().add(getClass().getResource("test/test.css").toExternalForm());


                primaryStage.setScene(scene);
                primaryStage.show();




    }

    public static void main(String[] args) {
        launch(args);
    }
}