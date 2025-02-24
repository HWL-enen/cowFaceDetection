import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class persentRectangle {
    public double width;
    public double height;
    public double x;
    public double y;
    public Rectangle background;
    public Rectangle roll;
    public Rectangle offRoll;

    public Pane getRoot() {
        return root;
    }

    public Pane root = new Pane();
    public double maxline,minline,qwe,raidus1,raidus2;
    public String backColor,rollColor,fontColor;
    public Text Tips = new Text("%");


    public persentRectangle(double width, double height, double x, double y,int qwe,double raidus1,double raidus2,String backColor,String rollColor,String fontColor,String offRollColor) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        background   = new Rectangle(0, 0, width, height);
        roll = new Rectangle(qwe,qwe,0.9*(width-2*qwe),height-2*qwe);
        offRoll = new Rectangle(qwe,qwe,0.9*(width-2*qwe),height-2*qwe);
        maxline = 0.9*(width-2*qwe);
        root.setLayoutX(x);
        root.setLayoutY(y);
        this.qwe = qwe;
        this.raidus1 = raidus1;
        this.raidus2 = raidus2;
        this.backColor = backColor;
        this.rollColor = rollColor;
        this.fontColor = fontColor;
        background.setArcWidth(raidus1); // 设置水平方向的圆角半径
        background.setArcHeight(raidus1); // 设置垂直方向的圆角半径
        background.setFill(Color.web(backColor));

        offRoll.setArcWidth(raidus2); // 设置水平方向的圆角半径
        offRoll.setArcHeight(raidus2); // 设置垂直方向的圆角半径
        offRoll.setFill(Color.web(offRollColor));

        roll.setArcWidth(raidus2); // 设置水平方向的圆角半径
        roll.setArcHeight(raidus2); // 设置垂直方向的圆角半径
        roll.setFill(Color.web(rollColor));


        root.getChildren().add(background);
        root.getChildren().add(offRoll);
        root.getChildren().add(roll);


        Tips.setLayoutX(width-49-qwe);
        Tips.setLayoutY(height/2+6);
        Tips.setStyle("-fx-background-color: transparent;");
        Tips.setFont(myFont.Outfit_Medium_14);
        Tips.setFill(Color.web(fontColor));
        root.getChildren().add(Tips);
    }


    public void start(double num){
        if (num!=roll.getWidth()/maxline) {

            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(0), new KeyValue(roll.widthProperty(), roll.getWidth())),
                    new KeyFrame(Duration.millis(200), new KeyValue(roll.widthProperty(), maxline * num))
            );
            timeline.setCycleCount(1);
            timeline.play();
            Tips.setText(String.format("%.2f",num*100)+"%");
        }
    }







}
