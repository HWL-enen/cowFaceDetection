import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.ini4j.Ini;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

public class roomblock {
    public Pane root = new Pane();
    public boolean type;
    public String roomName,numberHead,numberTail;
    public int com;
    public double avg;
    public Stage cvStage;
    public int num;
    public boolean isOpenCvPage = false;
    public ImageView cvImageView = new ImageView();
    public yoloStart yolo ;
    public boolean isDetection,isBround,isDiscover,isRtmpOpen;
    public CsvMng csv;
    public Integer chartNum = 0;


    public roomblock(double x, double y,boolean b,int num,String roomName) {
        root.setLayoutX(x);
        root.setLayoutY(y);
        type=b;
        this.num=num;
        this.roomName=roomName;
        csv = new CsvMng(roomName);
        yolo = new yoloStart(cvImageView,roomName,csv,chartNum);

    }

    public void init(){
        root.setPrefWidth(285);
        root.setPrefHeight(173);

        yolo.isDetection = isDetection;
        yolo.isBround = isBround;
        yolo.isDiscover = isDiscover;
        yolo.isRtmpOpen = isRtmpOpen;

        Image backgroundImage;ImageView backgroundImageView;

        Text roomNameText = new Text("房间名: "+roomName);
        Text numberHeadText = new Text("编号: "+numberHead);
        Text numberTailText = new Text(numberTail);
        Text comText = new Text("端口: "+com);
        Text avgNumText = new Text("avg: "+avg);
        Image onOpenImage = new Image("res/images/control/roomBlock/on.png");
        ImageView onOpenImageView = new ImageView(onOpenImage);
        Button openButton = new Button("");




        openButton.setText("");
        openButton.setOnAction(e -> {
            openButton.setOpacity(0.6);
            root.getChildren().remove(onOpenImageView);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {
                        openButton.setOpacity(0.1);
                        if (isOpenCvPage) {
                            cvStage.setAlwaysOnTop(true);
                            cvStage.setAlwaysOnTop(false);
                        }else {
                            isOpenCvPage = true;
                            cvPage();

                        }


                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            timeline.play();




        });
        openButton.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;",myColor.homeBackColor));
        openButton.setOpacity(0.1);
        openButton.setPrefWidth(295);
        openButton.setPrefHeight(188);
        openButton.setLayoutX(-3);
        openButton.setLayoutY(-3);
        openButton.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                root.getChildren().add(root.getChildren().indexOf(openButton)-1,onOpenImageView);

            }else {
                root.getChildren().remove(onOpenImageView);

            }
        });



        onOpenImageView.setLayoutX(-3);
        onOpenImageView.setLayoutY(-3);
        onOpenImageView.setFitWidth(295);
        onOpenImageView.setFitHeight(188);
        //backgroundImageView.setPreserveRatio(true);
        onOpenImageView.setSmooth(true);



        if(type){
            backgroundImage = new Image("res/images/control/roomBlock/background.png");
            backgroundImageView = new ImageView(backgroundImage);

            roomNameText.setFill(Color.web(myColor.OnchooseGroundColor));
            numberHeadText.setFill(Color.web(myColor.OnchooseGroundColor1));
            numberTailText.setFill(Color.web(myColor.OnchooseGroundColor));
            comText.setFill(Color.web(myColor.OnchooseGroundColor1));
            avgNumText.setFill(Color.web(myColor.OnchooseGroundColor1));
        }else{
            backgroundImage = new Image("res/images/control/roomBlock/background1.png");
            backgroundImageView = new ImageView(backgroundImage);

            roomNameText.setFill(Color.web(myColor.chooseGroundColor));
            numberHeadText.setFill(Color.web("#FFFFFF"));
            numberTailText.setFill(Color.web(myColor.chooseGroundColor));
            comText.setFill(Color.web("#FFFFFF"));
            avgNumText.setFill(Color.web("#FFFFFF"));
        }

        backgroundImageView.setLayoutX(0);
        backgroundImageView.setLayoutY(0);
        backgroundImageView.setFitWidth(285);
        backgroundImageView.setFitHeight(173);
        //backgroundImageView.setPreserveRatio(true);
        backgroundImageView.setSmooth(true);

        roomNameText.setLayoutX(15);
        roomNameText.setLayoutY(10+32);
        roomNameText.setStyle("-fx-background-color: transparent;");
        roomNameText.setFont(myFont.Outfit_Bold_32);

        numberHeadText.setLayoutX(24);
        numberHeadText.setLayoutY(65+14);
        numberHeadText.setStyle("-fx-background-color: transparent;");
        numberHeadText.setFont(myFont.Outfit_Medium_14);

        numberTailText.setLayoutX(numberHeadText.getLayoutBounds().getWidth()+24);
        numberTailText.setLayoutY(65+14);
        numberTailText.setStyle("-fx-background-color: transparent;");
        numberTailText.setFont(myFont.Outfit_Medium_14);

        comText.setLayoutX(24);
        comText.setLayoutY(94+14);
        comText.setStyle("-fx-background-color: transparent;");
        comText.setFont(myFont.Outfit_Medium_14);

        avgNumText.setLayoutX(24);
        avgNumText.setLayoutY(123+14);
        avgNumText.setStyle("-fx-background-color: transparent;");
        avgNumText.setFont(myFont.Outfit_Medium_14);




        root.getChildren().add(backgroundImageView);
        root.getChildren().add(roomNameText);
        root.getChildren().add(numberHeadText);
        root.getChildren().add(numberTailText);
        root.getChildren().add(comText);
        root.getChildren().add(avgNumText);
        root.getChildren().add(openButton);

    }

    private void cvPage(){
        cvStage = new Stage();

        Pane root = new Pane();
        Scene scene = new Scene(root, 1045, 634);

        block.PaneRoundedRectangle(root,30);
        block.PanesetOnMouseMove(root,cvStage);

        cvStage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        cvStage.initStyle(StageStyle.TRANSPARENT);
        cvPage_Init(root);
        cvStage.show();

    }

    private void cvPage_Init(Pane root){



        Image BackgroundImage = new Image("res/images/control/cv/backGround.png");
        ImageView BackgroundImageView = new ImageView(BackgroundImage);
        BackgroundImageView.setLayoutX(-30);
        BackgroundImageView.setLayoutY(0);
        BackgroundImageView.setFitWidth(1045);
        BackgroundImageView.setFitHeight(634);
        //backgroundImageView.setPreserveRatio(true);
        BackgroundImageView.setSmooth(true);


        cvImageView.setLayoutX(7);
        cvImageView.setLayoutY(5);
        cvImageView.setFitWidth(499);
        cvImageView.setFitHeight(440);
        //backgroundImageView.setPreserveRatio(true);
        cvImageView.setSmooth(true);



        Text roomNameText = new Text("房间名: "+roomName);
        Text numberHeadText = new Text("编号: "+numberHead);
        Text numberTailText = new Text(numberTail);
        Text comText = new Text("端口: "+com);
        Text avgNumText = new Text("avg: "+avg);
        Text fpsNumText = new Text(0+" fps");

        roomNameText.setLayoutX(25);
        roomNameText.setLayoutY(472+32);
        roomNameText.setStyle("-fx-background-color: transparent;");
        roomNameText.setFont(myFont.Outfit_Bold_32);

        numberHeadText.setLayoutX(80);
        numberHeadText.setLayoutY(524+14);
        numberHeadText.setStyle("-fx-background-color: transparent;");
        numberHeadText.setFont(myFont.Outfit_Medium_14);

        numberTailText.setLayoutX(numberHeadText.getLayoutBounds().getWidth()+80);
        numberTailText.setLayoutY(524+14);
        numberTailText.setStyle("-fx-background-color: transparent;");
        numberTailText.setFont(myFont.Outfit_Medium_14);

        comText.setLayoutX(226);
        comText.setLayoutY(524+14);
        comText.setStyle("-fx-background-color: transparent;");
        comText.setFont(myFont.Outfit_Medium_14);

        avgNumText.setLayoutX(535);
        avgNumText.setLayoutY(18+32);
        avgNumText.setStyle("-fx-background-color: transparent;");
        avgNumText.setFont(myFont.Outfit_Bold_32_num);

        fpsNumText.setLayoutX(984);
        fpsNumText.setLayoutY(216);
        fpsNumText.setStyle("-fx-background-color: transparent;");
        fpsNumText.setFont(myFont.Outfit_Bold_18);

        persentRectangle fpsPersent = new persentRectangle(500,28,530,224,8,30,10,myColor.rollBackColor,myColor.OnchooseGroundColor,myColor.OnchooseGroundColor1,myColor.offRollColor);
        persentRectangle netPersent = new persentRectangle(500,28,530,281,8,30,10,myColor.rollBackColor,myColor.OnchooseGroundColor,myColor.OnchooseGroundColor1,myColor.offRollColor);



        roomNameText.setFill(Color.web("#FFFFFF"));
        numberHeadText.setFill(Color.web("#FFFFFF"));
        numberTailText.setFill(Color.web("#FFFFFF"));
        comText.setFill(Color.web("#FFFFFF"));
        avgNumText.setFill(Color.web("#FFFFFF"));
        fpsNumText.setFill(Color.web("#FFFFFF"));

        myChart chart = new myChart(6,618,86,371,96,12,3,6,1,"小时前","个体数");
        yolo.chart = chart;




        Timeline updateTimeline = new Timeline(new KeyFrame(Duration.millis(1000), e1 -> {
            fpsPersent.start(yolo.fps);
            fpsNumText.setText(String.format("%d fps",(int)(yolo.fps * yoloClient.maxFps)));
            if (yolo.fps > 0.5 && yolo.fps <= 1){
                fpsNumText.setFill(Color.rgb((int) (445 - (400 * yolo.fps)), 245,35));
            }else {
                fpsNumText.setFill(Color.rgb(245, (int) (45 + (400 * yolo.fps)),35));
            }


        }));


        Button exitButton = new Button();
        exitButton.setText("");
        exitButton.setOnAction(e -> {
            exitButton.setOpacity(0.6);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {
                        exitButton.setOpacity(0.3);
                        isOpenCvPage = false;
                        cvStage.close();
                        try {
                            Main.ini.store(new File("setting.ini"));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            // 开始播放动画
            timeline.play();

        });
        exitButton.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;",myColor.backgroundColor));
        exitButton.setOpacity(0.3);
        exitButton.setPrefWidth(45);
        exitButton.setPrefHeight(45);
        exitButton.setLayoutX(980);
        exitButton.setLayoutY(4);


        Button startButton = new Button("");
        Image onStartImage = new Image("res/images/control/cv/onStart.png");
        ImageView onStartImageView = new ImageView(onStartImage);
        onStartImageView.setLayoutX(815-6);
        onStartImageView.setLayoutY(478-7);
        onStartImageView.setFitWidth(227);
        onStartImageView.setFitHeight(54);
        //backgroundImageView.setPreserveRatio(true);
        onStartImageView.setSmooth(true);



        startButton.setText("");
        startButton.setOnAction(e -> {
            startButton.setOpacity(0.6);
            root.getChildren().remove(onStartImageView);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {
                        startButton.setOpacity(0.1);
                        if (yolo.over){
                            yolo.over = false;
                            Thread thread = new Thread(yolo);
                            thread.start();
                            updateTimeline.play();
                        }

                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            timeline.play();




        });
        startButton.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;",myColor.homeBackColor));
        startButton.setOpacity(0.1);
        startButton.setPrefWidth(217);
        startButton.setPrefHeight(39);
        startButton.setLayoutX(815);
        startButton.setLayoutY(478);
        startButton.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                root.getChildren().add(root.getChildren().indexOf(startButton)-1,onStartImageView);

            }else {
                root.getChildren().remove(onStartImageView);

            }
        });

        Button stopButton = new Button("");
        Image onStopImage = new Image("res/images/control/cv/onStart.png");
        ImageView onStopImageView = new ImageView(onStopImage);
        onStopImageView.setLayoutX(593-6);
        onStopImageView.setLayoutY(478-7);
        onStopImageView.setFitWidth(227);
        onStopImageView.setFitHeight(54);
        //backgroundImageView.setPreserveRatio(true);
        onStopImageView.setSmooth(true);



        stopButton.setText("");
        stopButton.setOnAction(e -> {
            stopButton.setOpacity(0.6);
            root.getChildren().remove(onStopImageView);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {
                        stopButton.setOpacity(0.1);
                        yolo.stop();
                        updateTimeline.stop();
                        chart.data_update(50);
                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            timeline.play();




        });
        stopButton.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;",myColor.homeBackColor));
        stopButton.setOpacity(0.1);
        stopButton.setLayoutX(593);
        stopButton.setLayoutY(478);
        stopButton.setPrefWidth(217);
        stopButton.setPrefHeight(39);
        stopButton.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                root.getChildren().add(root.getChildren().indexOf(stopButton)-1,onStopImageView);

            }else {
                root.getChildren().remove(onStopImageView);

            }
        });


        Button deleteButton = new Button("");
        Image ondeleteImage = new Image("res/images/control/cv/onStart.png");
        ImageView ondeleteImageView = new ImageView(ondeleteImage);
        ondeleteImageView.setLayoutX(572-6);
        ondeleteImageView.setLayoutY(538-7);
        ondeleteImageView.setFitWidth(227);
        ondeleteImageView.setFitHeight(54);
        //backgroundImageView.setPreserveRatio(true);
        ondeleteImageView.setSmooth(true);



        deleteButton.setText("");
        deleteButton.setOnAction(e -> {
            deleteButton.setOpacity(0.6);
            root.getChildren().remove(ondeleteImageView);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {
                        deleteButton.setOpacity(0.1);
                        Main.roomblocks.remove(this);
                        Ini ini = Main.ini;
                        int i = 0;
                        while(ini.containsKey(String.format("roomblock%d",i))){
                            ini.remove(String.format("roomblock%d",i));
                            i++;
                        }
                        for (int qwe = 0; qwe < Main.roomblocks.size(); qwe++) {

                            ini.put(String.format("roomblock%d",qwe), "roomName", Main.roomblocks.get(qwe).roomName);
                            ini.put(String.format("roomblock%d",qwe), "numberHead", Main.roomblocks.get(qwe).numberHead);
                            ini.put(String.format("roomblock%d",qwe), "numberTail", Main.roomblocks.get(qwe).numberTail);
                            ini.put(String.format("roomblock%d",qwe), "com",String.valueOf(Main.roomblocks.get(qwe).com));
                            ini.put(String.format("roomblock%d",qwe), "avg",String.valueOf(Main.roomblocks.get(qwe).avg));

                        }

                        control.showRoomBlocks.clear();

                        for(int qwe = 0; qwe < Main.roomblocks.size(); qwe++){
                                Main.roomblocks.get(qwe).root.setLayoutX((qwe%4)*317);
                                control.showRoomBlocks.add(Main.roomblocks.get(qwe));
                        }

                        control.showRoom(control.vbox);

                        try {
                            ini.store(new File("setting.ini"));
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

                        yolo.stop();
                        updateTimeline.stop();
                        cvStage.close();
                        isOpenCvPage = false;

                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            timeline.play();




        });
        deleteButton.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;",myColor.homeBackColor));
        deleteButton.setOpacity(0.1);
        deleteButton.setLayoutX(572);
        deleteButton.setLayoutY(538);
        deleteButton.setPrefWidth(217);
        deleteButton.setPrefHeight(39);
        deleteButton.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                root.getChildren().add(root.getChildren().indexOf(deleteButton)-1,ondeleteImageView);

            }else {
                root.getChildren().remove(ondeleteImageView);

            }
        });


        Button getCsvButton = new Button("");
        Image ongetCsvButtonImage = new Image("res/images/control/cv/onCSV.png");
        ImageView ongetCsvButtonImageView = new ImageView(ongetCsvButtonImage);
        ongetCsvButtonImageView.setLayoutX(571-6);
        ongetCsvButtonImageView.setLayoutY(586-7);
        ongetCsvButtonImageView.setFitWidth(447);
        ongetCsvButtonImageView.setFitHeight(54);
        //backgroundImageView.setPreserveRatio(true);
        ongetCsvButtonImageView.setSmooth(true);



        getCsvButton.setText("");
        getCsvButton.setOnAction(e -> {
            getCsvButton.setOpacity(0.6);
            root.getChildren().remove(ongetCsvButtonImageView);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {
                        getCsvButton.setOpacity(0.1);
                        DirectoryChooser directoryChooser = new DirectoryChooser();
                        directoryChooser.setTitle("选择文件的上一级目录");
                        File selectedDirectory = directoryChooser.showDialog(cvStage);
                        String sourceFilePath = String.format("csv/%s.csv", roomName);
                        String destinationFilePath = String.format("%s/%s.csv", selectedDirectory.getAbsolutePath(), roomName);

                        try (FileInputStream fis = new FileInputStream(sourceFilePath);
                             FileOutputStream fos = new FileOutputStream(destinationFilePath)) {

                            int byteRead;
                            while ((byteRead = fis.read()) != -1) {
                                fos.write(byteRead);
                            }
                            System.out.println("文件复制成功！");
                        } catch (IOException e2) {
                            System.out.println("文件复制失败：" + e2.getMessage());
                        }


                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            timeline.play();




        });
        getCsvButton.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;",myColor.homeBackColor));
        getCsvButton.setOpacity(0.1);
        getCsvButton.setLayoutX(571);
        getCsvButton.setLayoutY(586);
        getCsvButton.setPrefWidth(439);
        getCsvButton.setPrefHeight(39);
        getCsvButton.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                root.getChildren().add(root.getChildren().indexOf(getCsvButton)-1,ongetCsvButtonImageView);

            }else {
                root.getChildren().remove(ongetCsvButtonImageView);

            }
        });


        updateTimeline.setCycleCount(-1);

        if (!yolo.over){
            updateTimeline.play();
        }

        switchButton detectionButton = new switchButton(isDetection,616,367,46,29,2,30);
        detectionButton.switchButton.setOnAction(e -> {
           detectionButton.tap();
            if (detectionButton.isopen){
                yolo.isDetection = true;
                isDetection = true;
            }else {
                yolo.isDetection = false;
                isDetection = false;
            }
            Ini ini = Main.ini;
            ini.put(String.format("roomblock%d",num), "isDetection",String.valueOf(isDetection));

        });

        switchButton broundButton = new switchButton(isBround,757,367,46,29,2,30);
        broundButton.switchButton.setOnAction(e -> {
            broundButton.tap();
            if (broundButton.isopen){
                yolo.isBround = true;
                isBround = true;
            }else {
                yolo.isBround = false;
                isBround = false;
            }
            Ini ini = Main.ini;
            ini.put(String.format("roomblock%d",num), "isBround",String.valueOf(isBround));
        });

        switchButton discoverButton = new switchButton(isDiscover,639,415,46,29,2,30);
        discoverButton.switchButton.setOnAction(e -> {
            discoverButton.tap();
            if (discoverButton.isopen){
                yolo.isDiscover = true;
                isDiscover = true;
            }else {
                yolo.isDiscover = false;
                isDiscover = false;
            }
            Ini ini = Main.ini;
            ini.put(String.format("roomblock%d",num), "isDiscover",String.valueOf(isDiscover));

        });

        switchButton rtmpserverButton = new switchButton(isRtmpOpen,821,415,46,29,2,30);
        rtmpserverButton.switchButton.setOnAction(e -> {
            rtmpserverButton.tap();
            if (rtmpserverButton.isopen){
               // yolo.rtmpStart();
                yolo.isRtmpOpen = true;
                isRtmpOpen = true;
            }else {
                //yolo.rtmpClose();
                yolo.isRtmpOpen = false;
                isRtmpOpen = false;
            }

            Ini ini = Main.ini;
            ini.put(String.format("roomblock%d",num), "isRtmpOpen",String.valueOf(isRtmpOpen));
        });


        root.getChildren().add(BackgroundImageView);
        root.getChildren().add(cvImageView);
        root.getChildren().add(roomNameText);
        root.getChildren().add(numberHeadText);
        root.getChildren().add(numberTailText);
        root.getChildren().add(comText);
        root.getChildren().add(avgNumText);
        root.getChildren().add(fpsNumText);
        root.getChildren().add(fpsPersent.root);
        root.getChildren().add(netPersent.root);
        root.getChildren().add(startButton);
        root.getChildren().add(stopButton);
        root.getChildren().add(deleteButton);
        root.getChildren().add(getCsvButton);
        root.getChildren().add(exitButton);
        root.getChildren().add(detectionButton.root);
        root.getChildren().add(broundButton.root);
        root.getChildren().add(rtmpserverButton.root);
        root.getChildren().add(discoverButton.root);
        root.getChildren().add(chart.root);


        anima.translateTransition_G(0,0,30,0,100,20,BackgroundImageView);
        netPersent.start(0);
        fpsPersent.start(0);
    }
}
