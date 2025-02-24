import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class switchButton {

    public Rectangle background1;
    public Rectangle background2;
    public boolean isopen;
    public Pane root = new Pane();
    public Button switchButton = new Button();
    public double width;
    public double height;
    public double qwe;
    public ImageView switchButton_cilkImageView;

    public switchButton(boolean isopen,double layoutX,double layoutY,double width,double height,double qwe,double radius1) {
        this.isopen = isopen;
        this.qwe = qwe;
        root.setLayoutX(layoutX);
        root.setLayoutY(layoutY);
        root.setPrefWidth(width);
        root.setPrefHeight(height);

        Rectangle clip = new Rectangle(root.getPrefWidth(), root.getPrefHeight());
        clip.setArcWidth(radius1);
        clip.setArcHeight(radius1);

        // 将矩形设置为 Pane 的裁剪区域
        root.setClip(clip);

        this.width = width;
        this.height = height;

        background1   = new Rectangle(0, 0, 0, height);
        background2   = new Rectangle(0, 0, width, height);
        /*background1.setArcWidth(radius1); // 设置水平方向的圆角半径
        background1.setArcHeight(radius1); // 设置垂直方向的圆角半径*/
        background1.setFill(Color.web(myColor.OnchooseGroundColor));

        /*background2.setArcWidth(radius1);
        background2.setArcHeight(radius1);*/
        background2.setFill(Color.web(myColor.OnchooseGroundColor1));

        Image switchButton_cilkImage = new Image("res/images/control/cv/switchButton_cilk.png");
        switchButton_cilkImageView = new ImageView(switchButton_cilkImage);
        if (isopen){
            switchButton_cilkImageView.setLayoutX(width-height+qwe);
            switchButton_cilkImageView.setLayoutY(qwe);
            switchButton_cilkImageView.setFitWidth(height-qwe*2);
            switchButton_cilkImageView.setFitHeight(height-qwe*2);
            switchButton_cilkImageView.setPreserveRatio(true);
            switchButton_cilkImageView.setSmooth(true);
            background1.setWidth(width);
            background2.setWidth(width);
        }else {
            switchButton_cilkImageView.setLayoutX(qwe);
            switchButton_cilkImageView.setLayoutY(qwe);
            switchButton_cilkImageView.setFitWidth(height-qwe*2);
            switchButton_cilkImageView.setFitHeight(height-qwe*2);
            //backgroundImageView.setPreserveRatio(true);
            switchButton_cilkImageView.setSmooth(true);
            background1.setWidth(0);
            background2.setWidth(width);
        }


        switchButton.setText("");
        switchButton.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;",myColor.backgroundColor));
        switchButton.setOpacity(0);
        switchButton.setPrefWidth(width);
        switchButton.setPrefHeight(height);
        switchButton.setLayoutX(0);
        switchButton.setLayoutY(0);

        double initialRightX = background2.getLayoutX() + background2.getWidth();

        // 监听按钮宽度的变化
        background2.widthProperty().addListener((observable, oldValue, newValue) -> {
            // 计算新的布局 x 坐标，以保持右边界固定
            double newX = initialRightX - newValue.doubleValue();
            background2.setLayoutX(newX);
        });

        root.getChildren().add(background2);
        root.getChildren().add(background1);

        root.getChildren().add(switchButton_cilkImageView);
        root.getChildren().add(switchButton);
    }

    public void tap(){
        if(isopen){
            isopen = false;
            anima.translateTransition_G(switchButton_cilkImageView.getTranslateX(),0,switchButton_cilkImageView.getTranslateX()-width+height,0,200,10,switchButton_cilkImageView);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(0), new KeyValue(background2.widthProperty(), background2.getWidth())),
                    new KeyFrame(Duration.millis(0), new KeyValue(background1.widthProperty(), background1.getWidth())),
                    new KeyFrame(Duration.millis(200), new KeyValue(background2.widthProperty(), width)),
                    new KeyFrame(Duration.millis(200), new KeyValue(background1.widthProperty(), 0))
            );
            timeline.setCycleCount(1);
            timeline.play();



        }else {
            isopen = true;
            anima.translateTransition_G(switchButton_cilkImageView.getTranslateX(),0,switchButton_cilkImageView.getTranslateX()+width-height,0,200,10,switchButton_cilkImageView);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(0), new KeyValue(background1.widthProperty(), background1.getWidth())),
                    new KeyFrame(Duration.millis(0), new KeyValue(background2.widthProperty(), background2.getWidth())),
                    new KeyFrame(Duration.millis(200), new KeyValue(background1.widthProperty(), width)),
                    new KeyFrame(Duration.millis(200), new KeyValue(background2.widthProperty(), 0))
            );
            timeline.setCycleCount(1);
            timeline.play();

        }

    }



}
