import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.ini4j.Ini;

import java.io.File;
import java.io.IOException;


public class msgBlock {
    public Pane root = new Pane();


    public static final Boolean IMSG = true;
    public static final Boolean noIMSG = false;

    public String msg;

    public Boolean type;
    public msgBlock(Double x,Double y, Boolean type, String msg) {
        this.type = type;

        root.setPrefWidth(779);
        root.setPrefHeight(93);
        root.setLayoutX(x);
        root.setLayoutY(y);

        this.msg = msg;

        if (type){
            IMsg_init();
        }else {
            noIMsg_init();
        }
    }

    public void IMsg_init(){

        Rectangle background   = new Rectangle(0, 0, 779, 68);
        background.setArcWidth(40); // 设置水平方向的圆角半径
        background.setArcHeight(40); // 设置垂直方向的圆角半径
        background.setFill(Color.web(myColor.OnchooseGroundColor));

        Text msgText = new Text(msg);
        msgText.setLayoutX(19);
        msgText.setLayoutY(23+18);
        msgText.setStyle("-fx-background-color: transparent;");
        msgText.setFont(myFont.Outfit_Bold_18);
        msgText.setFill(Color.web("#FFFFFF"));


        root.getChildren().add(background);
        root.getChildren().add(msgText);
    }

    public void noIMsg_init(){
        Rectangle background   = new Rectangle(0, 0, 779, 68);
        background.setArcWidth(40); // 设置水平方向的圆角半径
        background.setArcHeight(40); // 设置垂直方向的圆角半径
        background.setFill(Color.web(myColor.homeBackColor));

        Text msgText = new Text(msg);
        msgText.setLayoutX(19);
        msgText.setLayoutY(23+18);
        msgText.setStyle("-fx-background-color: transparent;");
        msgText.setFont(myFont.Outfit_Bold_18);
        msgText.setFill(Color.web("#FFFFFF"));


        Image exitButtonImage = new Image("res/images/home/exit.png");
        ImageView exitButtonImageView = new ImageView(exitButtonImage);
        exitButtonImageView.setLayoutX(731);
        exitButtonImageView.setLayoutY(18);
        exitButtonImageView.setFitWidth(32);
        exitButtonImageView.setFitHeight(32);
        //backgroundImageView.setPreserveRatio(true);
        exitButtonImageView.setSmooth(true);

        Button exitButton = new Button();
        exitButton.setText("");
        exitButton.setOnAction(e -> {
            exitButton.setOpacity(0.6);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {
                        exitButton.setOpacity(0.3);
                        Main.msgBlocks.remove(this);
                        Home.showMsg(Home.msgVBox);
                        Ini ini = Main.msgIni;
                        ini.clear();int i = 0;
                        for (msgBlock block : Main.msgBlocks) {
                            ini.put(String.format("msg%d",i),"msg",block.msg);
                            ini.put(String.format("msg%d",i),"IM",block.type);
                            i++;
                        }
                        try {
                            ini.store(new File("msg.ini"));
                        } catch (IOException ex) {
                            System.out.println("failed to load ini");
                        }
                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            // 开始播放动画
            timeline.play();

        });
        exitButton.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;",myColor.homeBackColor));
        exitButton.setOpacity(0.3);
        exitButton.setPrefWidth(32);
        exitButton.setPrefHeight(32);
        exitButton.setLayoutX(731);
        exitButton.setLayoutY(18);

        root.getChildren().add(background);
        root.getChildren().add(msgText);
        root.getChildren().add(exitButtonImageView);
        root.getChildren().add(exitButton);


    }
}
