import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class indexBlock {


    public Pane root = new Pane();

    public indexBlock(int index) {
        this.index = index;
    }

    public int index;

    public int getIndex() {
        return index;
    }
    public void init(){
        root.setLayoutX(0);
        root.setLayoutY(0);
        root.setPrefWidth(122);
        root.setPrefHeight(80);

        Image BackgroundImage = new Image("res/images/control/add/indexBlock.png");
        ImageView BackgroundImageView = new ImageView(BackgroundImage);
        BackgroundImageView.setLayoutX(0);
        BackgroundImageView.setLayoutY(0);
        BackgroundImageView.setFitWidth(122);
        BackgroundImageView.setFitHeight(80);
        //backgroundImageView.setPreserveRatio(true);
        BackgroundImageView.setSmooth(true);

        Text indexText = new Text(String.valueOf(index));
        indexText.setLayoutX(48);
        indexText.setLayoutY(45);
        indexText.setStyle("-fx-background-color: transparent;");
        indexText.setFill(Color.web(myColor.OnchooseGroundColor));
        indexText.setFont(myFont.Outfit_Bold_32);

        Button getButton = new Button();
        getButton.setText("");
        getButton.setOnAction(e -> {
            getButton.setOpacity(0.6);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {

                        getButton.setOpacity(0.1);
                        control.nowIndex = index;
                        control.indexText.setText(String.valueOf(control.nowIndex));
                        control.cameraTest.setCom(index);
                        control.indexVbox.getChildren().clear();
                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            // 开始播放动画
            timeline.play();


        });
        getButton.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;",myColor.backgroundColor));
        getButton.setOpacity(0.1);
        getButton.setPrefWidth(122);
        getButton.setPrefHeight(80);
        getButton.setLayoutX(0);
        getButton.setLayoutY(0);

        root.getChildren().add(BackgroundImageView);
        root.getChildren().add(indexText);
        root.getChildren().add(getButton);
    }
}
