import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ScrollableImageViewExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 创建一个垂直布局容器
        VBox vbox = new VBox();

        // 加载一些图片并创建 ImageView 控件
        for (int i = 1; i <= 100; i++) {
            // 这里假设图片文件名为 image1.jpg, image2.jpg 等，你需要根据实际情况修改
            Image image = new Image("res/images/setting/name.png");
            ImageView imageView = new ImageView(image);
            vbox.getChildren().add(imageView);
        }

        // 创建一个滚动面板并将垂直布局容器添加到其中
        ScrollPane scrollPane = new ScrollPane(vbox);
        // 设置滚动面板的滚动方向为垂直
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        // 创建场景并将滚动面板添加到场景中
        Scene scene = new Scene(scrollPane, 400, 300);

        // 设置舞台的标题和场景
        primaryStage.setTitle("Scrollable ImageView Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}