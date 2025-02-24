import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.QuadCurve;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class myChart {


    public Map<Integer,Integer> points = new HashMap<>();
    public Pane root = new Pane();
    public int pointNum,qwe;
    public double width,height;
    public double intervalVal,pointSize;
    public ArrayList<Circle> pointList = new ArrayList<>();
    public ArrayList<Text> textList_x = new ArrayList<>();
    public ArrayList<Text> textList_y = new ArrayList<>();
    public ArrayList<Line> lineList = new ArrayList<>();
    public ArrayList<Integer> dataList = new ArrayList<>();
    public int maxData_x,minData_x;
    public int maxData_y = 0,minData_y = 0;
    public String xStr,yStr;
    public myChart(int pointNum,double x,double y,double width,double height,int qwe,double pointSize,int minData_x,int maxData_x,String xStr,String yStr) {
        this.pointNum = pointNum;
        root.setLayoutX(x);
        root.setLayoutY(y);
        root.setPrefWidth(width);
        root.setPrefHeight(height);

        this.qwe = qwe;
        this.width = width;
        this.height = height;
        intervalVal = (width-qwe)/pointNum;
        this.pointSize = pointSize;
        this.minData_x = minData_x;
        this.maxData_x = maxData_x;
        this.xStr = xStr;
        this.yStr = yStr;
        BendingChart_init();

    }


    private void BendingChart_init(){
        root.getChildren().clear();
        for (int i = 1; i <= pointNum; i++) {
            dataList.add(0);

            Circle dot = new Circle(2*qwe+intervalVal*(i-1), height-qwe, pointSize, Color.web(myColor.OnchooseGroundColor));
            pointList.add(dot);
            root.getChildren().add(dot);



            Text text_x = new Text(String.valueOf(minData_x+(i-1)*((maxData_x-minData_x)/(pointNum-1))));
            text_x.setLayoutX(2*qwe+intervalVal*(i-1)-2);
            text_x.setLayoutY(height);
            text_x.setStyle("-fx-background-color: transparent;");
            text_x.setFont(myFont.Outfit_Regular_9);
            text_x.setFill(Color.web(myColor.OnchooseGroundColor1));
            textList_x.add(text_x);
            root.getChildren().add(text_x);

            Text text_y = new Text(String.valueOf(minData_y));
            text_y.setLayoutX(0);
            text_y.setLayoutY(4+(i-1)*((height)/pointNum));
            text_y.setStyle("-fx-background-color: transparent;");
            text_y.setFont(myFont.Outfit_Regular_9);
            text_y.setFill(Color.web(myColor.OnchooseGroundColor1));
            textList_y.add(text_y);
            root.getChildren().add(text_y);

            if (i>1){
                Line line = new Line();
                line.setStartX(pointList.get(i-2).getCenterX());
                line.setStartY(pointList.get(i-2).getCenterY());
                // 设置终点
                line.setEndX(pointList.get(i-1).getCenterX());
                line.setEndY(pointList.get(i-1).getCenterY());

                // 设置曲线的颜色和宽度
                line.setStroke(Color.web(myColor.OnchooseGroundColor));
                line.setStrokeWidth(2);
                // 不填充曲线内部
                line.setFill(null);
                root.getChildren().add(line);

                lineList.add(line);
            }


        }

        Text text_xStr = new Text(xStr);
        text_xStr.setLayoutX(3*qwe+intervalVal*(pointNum-1));
        text_xStr.setLayoutY(height);
        text_xStr.setStyle("-fx-background-color: transparent;");
        text_xStr.setFont(myFont.Outfit_Regular_9);
        text_xStr.setFill(Color.web(myColor.OnchooseGroundColor));
        root.getChildren().add(text_xStr);

        Text text_yStr = new Text(yStr);
        text_yStr.setLayoutX(-4);
        text_yStr.setLayoutY(-4);
        text_yStr.setStyle("-fx-background-color: transparent;");
        text_yStr.setFont(myFont.Outfit_Regular_9);
        text_yStr.setFill(Color.web(myColor.OnchooseGroundColor));
        root.getChildren().add(text_yStr);




    }

    public void data_update(int data){
        dataList.add(data);
        if (data>maxData_y){
            maxData_y = data;
            y_update();
        }
        if (data<minData_y){
            minData_y = data;
            y_update();
        }
        dataList.remove(0);


        for (int i = 0; i < pointNum-1; i++) {
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(0), new KeyValue(pointList.get(i).centerYProperty(), pointList.get(i).getCenterY())),
                    new KeyFrame(Duration.millis(200), new KeyValue(pointList.get(i).centerYProperty(), pointList.get(i+1).getCenterY()))
            );
            timeline.setCycleCount(1);
            timeline.play();

            if (i<pointNum-2){
                Timeline timeline3 = new Timeline(
                        new KeyFrame(Duration.millis(0), new KeyValue(lineList.get(i).startYProperty(), lineList.get(i).getStartY())),
                        new KeyFrame(Duration.millis(0), new KeyValue(lineList.get(i).endYProperty(), lineList.get(i).getEndY())),
                        new KeyFrame(Duration.millis(200), new KeyValue(lineList.get(i).startYProperty(), lineList.get(i+1).getStartY())),
                        new KeyFrame(Duration.millis(200), new KeyValue(lineList.get(i).endYProperty(), lineList.get(i+1).getEndY()))
                );
                timeline3.setCycleCount(1);
                timeline3.play();
            }else {
                Timeline timeline3 = new Timeline(
                        new KeyFrame(Duration.millis(0), new KeyValue(lineList.get(i).startYProperty(), lineList.get(i).getStartY())),
                        new KeyFrame(Duration.millis(200), new KeyValue(lineList.get(i).startYProperty(), pointList.get(i+1).getCenterY()))

                );
                timeline3.setCycleCount(1);
                timeline3.play();
            }

        }

        Timeline  timeline2 = new Timeline(new KeyFrame(Duration.millis(200),e->{
            Timeline timeline1 = new Timeline(
                    new KeyFrame(Duration.millis(0), new KeyValue(pointList.get(pointList.size()-1).centerYProperty(), pointList.get(pointList.size()-1).getCenterY())),
                    new KeyFrame(Duration.millis(0), new KeyValue(lineList.get(lineList.size()-1).endYProperty(), lineList.get(lineList.size()-1).getEndY())),
                    new KeyFrame(Duration.millis(200), new KeyValue(pointList.get(pointList.size()-1).centerYProperty(), (height-qwe)*(1-(data-minData_y)/(maxData_y-minData_y+0.0001)))),
                    new KeyFrame(Duration.millis(200), new KeyValue(lineList.get(lineList.size()-1).endYProperty(), (height-qwe)*(1-(data-minData_y)/(maxData_y-minData_y+0.0001))))
            );
            timeline1.setCycleCount(1);
            timeline1.play();


        }));
        timeline2.setCycleCount(1);
        timeline2.play();


    }

    public void y_update(){
        for (int i = 0; i < pointNum; i++) {
            textList_y.get(i).setText(String.format("%.2f",((((double)maxData_y-(double) minData_y)/(double) (pointNum-1))*((pointNum-1)-i))));
            pointList.get(i).setCenterY((height-qwe)*(1- (double) (dataList.get(i) - minData_y) /(maxData_y-minData_y)));
            if (i<pointNum-1){
                lineList.get(i).setEndY((height-qwe)*(1- (double) (dataList.get(i+1) - minData_y) /(maxData_y-minData_y)));
            }

        }



    }



}
