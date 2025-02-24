import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class block {
    private static double xOffset = 0;
    private static double yOffset = 0;
    public static void PaneRoundedRectangle(Pane item, double radius) {
        try{
            String str = String.format("-fx-background-color: transparent; " +
                    "-fx-background-radius: %fpx; " +
                    "-fx-border-radius: %fpx; ", radius, radius);
            item.setStyle(str);


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void PanesetOnMouseMove(Pane root, Stage primaryStage) {
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });


        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });
    }

}
