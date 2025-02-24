import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import java.io.File;

public class JavaFXDirectoryChooserExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button chooseDirectoryButton = new Button("选择目录");

        chooseDirectoryButton.setOnAction(e -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("选择目录");

            File selectedDirectory = directoryChooser.showDialog(primaryStage);

            if (selectedDirectory != null) {
                System.out.println("你选择的目录路径是：" + selectedDirectory.getAbsolutePath());
            } else {
                System.out.println("你取消了目录选择。");
            }
        });

        VBox vbox = new VBox(chooseDirectoryButton);
        Scene scene = new Scene(vbox, 200, 100);

        primaryStage.setTitle("JavaFX 目录选择示例");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}