import javafx.animation.*;

import javafx.scene.Node;
import javafx.util.Duration;



public class anima {
    public static void fadeT(double Start, double End, double time,Object item) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(time), (Node) item);
        fadeTransition.setFromValue(Start); // 初始透明度
        fadeTransition.setToValue(End); // 最终透明度
        //fadeTransition.setAutoReverse(true); // 自动反转，实现淡入淡出
        fadeTransition.play();
    }

    public static void translateTransition(double x, double y, double toX, double toY, double time, Object item) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(time), (Node) item);
        translateTransition.setFromX(x);
        translateTransition.setFromY(y);
        translateTransition.setToX(toX);
        translateTransition.setToY(toY);


        // 自定义动画控制
        translateTransition.setAutoReverse(false); // 不自动反转


        // 为动画添加事件处理器
        /*
        translateTransition.setOnFinished(event -> {

        });
        */

        // 开始动画
        translateTransition.play();
    }

    public static void translateTransition_G(double x,double y,double toX,double toY,double time,double jump,Object item) {
        double x0,x1,x2;
        double y0,y1,y2;
        x2 = toX;
        y2 = toY;

        if (toX>x){
            x0 = toX+jump;
            x1 = toX-jump/2;
        } else if (toX<x) {
            x0 = toX-jump;
            x1 = toX+jump/2;
        }else {
            x0= x1 = toX;
        }

        if (toY>y){
            y0 = toY+jump;
            y1 = toY-jump/2;
        } else if (toY<y) {
            y0 = toY-jump;
            y1 = toY+jump/2;
        }else {
            y0= y1 = toY;
        }
        double time0 = time*2/3;
        double time1 = (time-time0)*2/3;
        double time2 = (time-time0)/3;

        TranslateTransition translateTransition0 = new TranslateTransition(Duration.millis(time0), (Node) item);
        translateTransition0.setFromX(x);
        translateTransition0.setFromY(y);
        translateTransition0.setToX(x0);
        translateTransition0.setToY(y0);

        // 自定义动画控制
        translateTransition0.setAutoReverse(false); // 不自动反转
        translateTransition0.setOnFinished(event -> {
            TranslateTransition translateTransition1 = new TranslateTransition(Duration.millis(time1), (Node) item);
            translateTransition1.setFromX(x0);
            translateTransition1.setFromY(y0);
            translateTransition1.setToX(x1);
            translateTransition1.setToY(y1);

            // 自定义动画控制
            translateTransition1.setAutoReverse(false); // 不自动反转

            translateTransition1.setOnFinished(event1 -> {
                TranslateTransition translateTransition2 = new TranslateTransition(Duration.millis(time2), (Node) item);
                translateTransition2.setFromX(x1);
                translateTransition2.setFromY(y1);
                translateTransition2.setToX(x2);
                translateTransition2.setToY(y2);

                // 自定义动画控制
                translateTransition2.setAutoReverse(false); // 不自动反转

                translateTransition2.play();
            });

            translateTransition1.play();
        });
        translateTransition0.play();







    }

    public static void zoom_ratio (double x,double y,double toX,double toY,double time,Object item){
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(time), (Node) item);
        scaleTransition.setFromX(x);
        scaleTransition.setFromY(y);
        scaleTransition.setToX(toX);
        scaleTransition.setToY(toY);
        //scaleTransition.setCycleCount(ScaleTransition.INDEFINITE);
        //scaleTransition.setAutoReverse(true);

        scaleTransition.play();
    }

    public static void zoom_size (double x,double y,double toX,double toY,double time,Object item){
        double toXRatio = toX/x;
        double toYRatio = toY/y;
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(time), (Node) item);
        scaleTransition.setFromX(1.0);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToX(toXRatio);
        scaleTransition.setToY(toYRatio);
        //scaleTransition.setCycleCount(ScaleTransition.INDEFINITE);
        //scaleTransition.setAutoReverse(true);

        scaleTransition.play();
    }

    public static void Rotate(double angle,double toAngle,double time,Object item){
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(time), (Node) item);
        rotateTransition.setFromAngle(angle);
        rotateTransition.setToAngle(toAngle);
        //rotateTransition.setCycleCount(RotateTransition.INDEFINITE);
        //rotateTransition.setAutoReverse(true);

        rotateTransition.play();
    }

    public static void Loading(Object item){

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(300), event -> {
            Rotate(120,180,300,item);
        }));

        Timeline timeline1 = new Timeline(new KeyFrame(Duration.millis(600), event -> {
            Rotate(180,300,300,item);
        }));

        Timeline timeline2 = new Timeline(new KeyFrame(Duration.millis(900), event -> {
            Rotate(300,360,300,item);
        }));
        timeline.setCycleCount(1);
        timeline1.setCycleCount(1);
        timeline2.setCycleCount(1);
        Rotate(0,120,300,item);
        timeline.play();
        timeline1.play();
        timeline2.play();



    }

    public static void back(Node imageView){
        imageView.setTranslateX(0);
        imageView.setTranslateY(0);
        imageView.setTranslateZ(0);
        imageView.setRotate(0);
        imageView.setScaleX(1);
        imageView.setScaleY(1);
    }


}
