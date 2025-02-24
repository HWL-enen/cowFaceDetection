import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.ini4j.Ini;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class Home {
    public int onchoose = 1,oldchoose = 1;
    public double mainWidth, mainHeight;
    private boolean isNoticeOpen = false,isEditOpen = false,isControlopen = false;
    private Stage noticePage,controlPage,stage;
    public static Pane Root = new Pane();
    public double cpuPercentDouble = 0;
    public Runtime runtime = Runtime.getRuntime();
    public double memPercentDouble = 0;
    public static Timeline timeline ;
    public static double cpu=0,gpu=0,mem=0;
    public static VBox msgVBox = new VBox();
    public static boolean isAllDecetion = true;
    public static boolean isAllRtmp = true;
    public static boolean isAllBorder = true;
    public List<LocalDate> days = new ArrayList<>();
    public List<LocalDate> months = new ArrayList<>();
    public List<LocalDate> years = new ArrayList<>();
    public List<Integer> daySum = new ArrayList<>();
    public List<Integer> monthSum = new ArrayList<>();
    public List<Integer> yearSum = new ArrayList<>();
    public CsvMng dayCsv = new CsvMng("timeCsv/dayCsv");
    public CsvMng monthCsv = new CsvMng("timeCsv/monthCsv");
    public CsvMng yearCsv = new CsvMng("timeCsv/yearCsv");
    public myChart day_chart;
    public myChart month_chart;
    public myChart year_chart;

    public Home(double mainWidth, double mainHeight) {
        this.mainWidth = mainWidth;
        this.mainHeight = mainHeight;
        for (int i = 0 ;i < 5;++i){
            daySum.add(0);
            monthSum.add(0);
            yearSum.add(0);
        }
    }

    public void timeUpdate(){
        days.clear();
        months.clear();
        years.clear();
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter0 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy");
        for (int i = 0; i < 5; i++) {
            days.add(currentDate.minusDays(i));
            months.add(currentDate.minusMonths(i));
            years.add(currentDate.minusYears(i));
            daySum.set(i,Integer.parseInt(dayCsv.getValue(currentDate.minusDays(i).format(formatter0),"Sum")));
            monthSum.set(i,Integer.parseInt(monthCsv.getValue(currentDate.minusMonths(i).format(formatter1),"Sum")));
            yearSum.set(i,Integer.parseInt(yearCsv.getValue(currentDate.minusYears(i).format(formatter2),"Sum")));
        }


    }

    public void start(Stage primaryStage) {
        stage = primaryStage;
        try{
            Main.loginPage.timeline1.stop();
            Main.loginPage.timeline2.stop();
        }catch (Exception e){

        }
        Pane root = new Pane();
        Scene scene = new Scene(root, mainWidth, mainHeight);

        block.PaneRoundedRectangle(root,20);
        block.PanesetOnMouseMove(root,primaryStage);

        primaryStage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        init();
        primaryStage.show();

        Image backgroundImage = new Image("res/images/home/background.png");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setLayoutX(0);
        backgroundImageView.setLayoutY(0);
        backgroundImageView.setFitWidth(1283);
        backgroundImageView.setFitHeight(726);
        //backgroundImageView.setPreserveRatio(true);
        backgroundImageView.setSmooth(true);
        root.getChildren().add(backgroundImageView);

        root.getChildren().add(Root);
        choose(root);




    }

    private void init(){
        timeUpdate();

        Pane dataBlock = new Pane();
        Image dataBlockImage = new Image("res/images/home/datablock_s.png");
        ImageView dataBlockImageView = new ImageView(dataBlockImage);
        Image opendataBlockImage = new Image("res/images/home/opendataBlockImage.png");
        ImageView opendataBlockImageView = new ImageView(opendataBlockImage);
        Image dataTileImage = new Image("res/images/home/dataTitle.png");
        ImageView dataTileImageView = new ImageView(dataTileImage);
        Image cokeImage = new Image("res/images/home/coke.png");
        ImageView cokeImageView = new ImageView(cokeImage);
        Image ondataBlockImage = new Image("res/images/home/ondatablock_s.png");
        ImageView ondataBlockImageView = new ImageView(ondataBlockImage);
        Image getCsvImage = new Image("res/images/home/getCsv.png");
        ImageView getCsvImageView = new ImageView(getCsvImage);
        Button dataButton = new Button();
        Image tableBackgroundImage = new Image("res/images/home/tableBackground.png");
        Image changeupdownImage = new Image("res/images/home/changeupdown.png");
        Image dayImage = new Image("res/images/home/day.png");
        Image dayImage1 = new Image("res/images/home/month.png");
        Image dayImage2 = new Image("res/images/home/year.png");
        Image tableTitleImage = new Image("res/images/home/tableTitle.png");
        Image cowImage = new Image("res/images/home/cow.png");
        Pane tabelBlock = new Pane();
        ImageView tableBackgroundImageView = new ImageView(tableBackgroundImage);
        ImageView cowImageView = new ImageView(cowImage);
        ImageView changeupdownImageView = new ImageView(changeupdownImage);
        ImageView dayImageView = new ImageView(dayImage);
        ImageView tableTitleImageView = new ImageView(tableTitleImage);
        Pane tabelBlock1 = new Pane();
        ImageView tableBackgroundImageView1 = new ImageView(tableBackgroundImage);
        ImageView cowImageView1 = new ImageView(cowImage);
        ImageView changeupdownImageView1 = new ImageView(changeupdownImage);
        ImageView dayImageView1 = new ImageView(dayImage1);
        ImageView tableTitleImageView1 = new ImageView(tableTitleImage);
        Pane tabelBlock2 = new Pane();
        ImageView tableBackgroundImageView2 = new ImageView(tableBackgroundImage);
        ImageView cowImageView2 = new ImageView(cowImage);
        ImageView changeupdownImageView2 = new ImageView(changeupdownImage);
        ImageView dayImageView2 = new ImageView(dayImage2);
        ImageView tableTitleImageView2 = new ImageView(tableTitleImage);
        day_chart = new myChart(5,24,66,268,121,12,3,5,1,"日前","个体数");
        month_chart = new myChart(5,24,66,268,121,12,3,5,1,"月前","个体数");
        year_chart = new myChart(5,24,66,268,121,12,3,5,1,"年前","个体数");
        Button dataUp = new Button();




        Pane power = new Pane();
        Image powerBlockImage = new Image("res/images/home/powerBlock_s.png");
        ImageView powerBlockImageView = new ImageView(powerBlockImage);
        Image powerTitleImage = new Image("res/images/home/powerTitle.png");
        ImageView powerTitleImageView = new ImageView(powerTitleImage);
        Image cpuTitleImage = new Image("res/images/home/cpuTitle.png");
        ImageView cpuTitleImageView = new ImageView(cpuTitleImage);
        Image memoryTitleImage = new Image("res/images/home/memoryTitle.png");
        ImageView memoryTitleImageView = new ImageView(memoryTitleImage);
        Image netTitleImage = new Image("res/images/home/netTitle.png");
        ImageView netTitleImageView = new ImageView(netTitleImage);
        Image gpuTitleImage = new Image("res/images/home/gpuTitle.png");
        ImageView gpuTitleImageView = new ImageView(gpuTitleImage);
        Image powerMsgImage = new Image("res/images/home/powerMsg.png");
        ImageView powerMsgImageView = new ImageView(powerMsgImage);
        Image downImage = new Image("res/images/home/down.png");
        ImageView downImageView = new ImageView(downImage);
        Image openPowerBlockImage = new Image("res/images/home/openPowerBlock.png");
        ImageView openPowerBlockImageView = new ImageView(openPowerBlockImage);
        Text cpuText = new Text("CPU: "+ Main.cpu);
        Text gpuText = new Text("GPU: "+ Main.gpu);
        Text memoryText = new Text(String.format("Memory_size: %.2fGB", Main.mem));
        Text wlanSsidText = new Text("Wlan-SSID: 23102");
        Text wlanPasswdText = new Text("password: qwertyui");
        Text username = new Text("username: cow_face_arm001");
        Button down = new Button();
        Button powerUp = new Button();
        persentRectangle cpuPersent = new persentRectangle(728,28,149,48,8,30,10,myColor.rollBackColor,myColor.OnchooseGroundColor,myColor.OnchooseGroundColor1,myColor.offRollColor);
        persentRectangle memPersent = new persentRectangle(728,28,149,89,8,30,10,myColor.rollBackColor,myColor.OnchooseGroundColor1,myColor.OnchooseGroundColor1,myColor.offRollColor);
        persentRectangle netPersent = new persentRectangle(728,28,149,134,8,30,10,myColor.rollBackColor,myColor.OnchooseGroundColor,myColor.OnchooseGroundColor1,myColor.offRollColor);
        persentRectangle gpuPersent = new persentRectangle(728,28,149,178,8,30,10,myColor.rollBackColor,myColor.OnchooseGroundColor1,myColor.OnchooseGroundColor1,myColor.offRollColor);



        Pane group = new Pane();
        Pane onGroup = new Pane();
        Image groupImage;
        if (Main.msgBlocks.isEmpty()){
            groupImage = new Image("res/images/home/group.png");
        }else {
            groupImage = new Image("res/images/home/group1.png");
        }


        ImageView groupImageView = new ImageView(groupImage);
        Image onNoticeImage = new Image("res/images/home/onNotice.png");
        ImageView onNoticeImageView = new ImageView(onNoticeImage);
        Image onControlImage = new Image("res/images/home/onNotice.png");
        ImageView onControlImageView = new ImageView(onControlImage);
        Button onNoticeButton = new Button();
        Button onControlButton = new Button();
        Image groupImage1;
        if (Main.msgBlocks.isEmpty()){
            groupImage1 = new Image("res/images/home/onGroup.png");
        }else {
            groupImage1 = new Image("res/images/home/onGroup1.png");
        }
        ImageView groupImageView1 = new ImageView(groupImage1);
        Image onNoticeImage1 = new Image("res/images/home/onNotice1.png");
        ImageView onNoticeImageView1 = new ImageView(onNoticeImage1);
        Image onControlImage1 = new Image("res/images/home/onNotice1.png");
        ImageView onControlImageView1 = new ImageView(onControlImage1);
        Button onNoticeButton1 = new Button();
        Button onControlButton1 = new Button();




        dataBlock.setPrefWidth(196);
        dataBlock.setPrefHeight(139);
        dataBlock.setLayoutX(-9);
        dataBlock.setLayoutY(152);


        dataBlockImageView.setLayoutX(0);
        dataBlockImageView.setLayoutY(0);
        dataBlockImageView.setFitWidth(196);
        dataBlockImageView.setFitHeight(139);
        //backgroundImageView.setPreserveRatio(true);
        dataBlockImageView.setSmooth(true);

        opendataBlockImageView.setLayoutX(0);
        opendataBlockImageView.setLayoutY(0);
        opendataBlockImageView.setFitWidth(1118);
        opendataBlockImageView.setFitHeight(511);
        //backgroundImageView.setPreserveRatio(true);
        opendataBlockImageView.setSmooth(true);

        dataTileImageView.setLayoutX(15);
        dataTileImageView.setLayoutY(13);
        dataTileImageView.setFitWidth(86);
        dataTileImageView.setFitHeight(19);
        //backgroundImageView.setPreserveRatio(true);
        dataTileImageView.setSmooth(true);


        cokeImageView.setLayoutX(150);
        cokeImageView.setLayoutY(93);
        cokeImageView.setFitWidth(32);
        cokeImageView.setFitHeight(32);
        //backgroundImageView.setPreserveRatio(true);
        cokeImageView.setSmooth(true);


        ondataBlockImageView.setLayoutX(0);
        ondataBlockImageView.setLayoutY(0);
        ondataBlockImageView.setFitWidth(196);
        ondataBlockImageView.setFitHeight(139);
        //backgroundImageView.setPreserveRatio(true);
        ondataBlockImageView.setSmooth(true);

        tabelBlock.setPrefWidth(293);
        tabelBlock.setPrefHeight(292);
        tabelBlock.setLayoutX(71);
        tabelBlock.setLayoutY(82);

        tableBackgroundImageView.setLayoutX(0);
        tableBackgroundImageView.setLayoutY(0);
        tableBackgroundImageView.setFitWidth(293);
        tableBackgroundImageView.setFitHeight(292);
        //backgroundImageView.setPreserveRatio(true);
        tableBackgroundImageView.setSmooth(true);

        tableTitleImageView.setLayoutX(24);
        tableTitleImageView.setLayoutY(15);
        tableTitleImageView.setFitWidth(63);
        tableTitleImageView.setFitHeight(15);
        //backgroundImageView.setPreserveRatio(true);
        tableTitleImageView.setSmooth(true);

        cowImageView.setLayoutX(24);
        cowImageView.setLayoutY(210);
        cowImageView.setFitWidth(32);
        cowImageView.setFitHeight(32);
        //backgroundImageView.setPreserveRatio(true);
        cowImageView.setSmooth(true);

        changeupdownImageView.setLayoutX(29);
        changeupdownImageView.setLayoutY(256);
        changeupdownImageView.setFitWidth(24);
        changeupdownImageView.setFitHeight(24);
        //backgroundImageView.setPreserveRatio(true);
        changeupdownImageView.setSmooth(true);

        dayImageView.setLayoutX(29);
        dayImageView.setLayoutY(181);
        dayImageView.setFitWidth(19);
        dayImageView.setFitHeight(20);
        //backgroundImageView.setPreserveRatio(true);
        dayImageView.setSmooth(true);

        tabelBlock1.setPrefWidth(419);
        tabelBlock1.setPrefHeight(292);
        tabelBlock1.setLayoutX(419);
        tabelBlock1.setLayoutY(82);

        tableBackgroundImageView1.setLayoutX(0);
        tableBackgroundImageView1.setLayoutY(0);
        tableBackgroundImageView1.setFitWidth(293);
        tableBackgroundImageView1.setFitHeight(292);
        //backgroundImageView.setPreserveRatio(true);
        tableBackgroundImageView1.setSmooth(true);

        tableTitleImageView1.setLayoutX(24);
        tableTitleImageView1.setLayoutY(15);
        tableTitleImageView1.setFitWidth(63);
        tableTitleImageView1.setFitHeight(15);
        //backgroundImageView.setPreserveRatio(true);
        tableTitleImageView1.setSmooth(true);

        cowImageView1.setLayoutX(24);
        cowImageView1.setLayoutY(210);
        cowImageView1.setFitWidth(32);
        cowImageView1.setFitHeight(32);
        //backgroundImageView.setPreserveRatio(true);
        cowImageView1.setSmooth(true);

        changeupdownImageView1.setLayoutX(29);
        changeupdownImageView1.setLayoutY(256);
        changeupdownImageView1.setFitWidth(24);
        changeupdownImageView1.setFitHeight(24);
        //backgroundImageView.setPreserveRatio(true);
        changeupdownImageView1.setSmooth(true);

        dayImageView1.setLayoutX(29);
        dayImageView1.setLayoutY(181);
        dayImageView1.setFitWidth(12);
        dayImageView1.setFitHeight(13);
        //backgroundImageView.setPreserveRatio(true);
        dayImageView1.setSmooth(true);

        tabelBlock2.setPrefWidth(293);
        tabelBlock2.setPrefHeight(292);
        tabelBlock2.setLayoutX(762);
        tabelBlock2.setLayoutY(82);

        tableBackgroundImageView2.setLayoutX(0);
        tableBackgroundImageView2.setLayoutY(0);
        tableBackgroundImageView2.setFitWidth(293);
        tableBackgroundImageView2.setFitHeight(292);
        //backgroundImageView.setPreserveRatio(true);
        tableBackgroundImageView2.setSmooth(true);

        tableTitleImageView2.setLayoutX(24);
        tableTitleImageView2.setLayoutY(15);
        tableTitleImageView2.setFitWidth(63);
        tableTitleImageView2.setFitHeight(15);
        //backgroundImageView.setPreserveRatio(true);
        tableTitleImageView2.setSmooth(true);

        cowImageView2.setLayoutX(24);
        cowImageView2.setLayoutY(210);
        cowImageView2.setFitWidth(32);
        cowImageView2.setFitHeight(32);
        //backgroundImageView.setPreserveRatio(true);
        cowImageView2.setSmooth(true);

        changeupdownImageView2.setLayoutX(29);
        changeupdownImageView2.setLayoutY(256);
        changeupdownImageView2.setFitWidth(24);
        changeupdownImageView2.setFitHeight(24);
        //backgroundImageView.setPreserveRatio(true);
        changeupdownImageView2.setSmooth(true);

        dayImageView2.setLayoutX(29);
        dayImageView2.setLayoutY(181);
        dayImageView2.setFitWidth(13);
        dayImageView2.setFitHeight(13);
        //backgroundImageView.setPreserveRatio(true);
        dayImageView2.setSmooth(true);



        Text cowNomber = new Text(String.valueOf(daySum.get(0)));
        cowNomber.setLayoutX(61);
        cowNomber.setLayoutY(240);
        cowNomber.setStyle("-fx-background-color: transparent;");
        cowNomber.setFont(myFont.Outfit_Bold_32);
        cowNomber.setFill(Color.web("#FFFFFF"));



        Text changeNum = new Text(String.valueOf(daySum.get(0)-daySum.get(1)));
        changeNum.setLayoutX(65);
        changeNum.setLayoutY(273);
        changeNum.setStyle("-fx-background-color: transparent;");
        changeNum.setFont(myFont.Outfit_Medium_14);


        Text cowNomber1 = new Text(String.valueOf(monthSum.get(0)));
        cowNomber1.setLayoutX(61);
        cowNomber1.setLayoutY(240);
        cowNomber1.setStyle("-fx-background-color: transparent;");
        cowNomber1.setFont(myFont.Outfit_Bold_32);
        cowNomber1.setFill(Color.web("#FFFFFF"));

        Text changeNum1 = new Text(String.valueOf(monthSum.get(0)-monthSum.get(1)));
        changeNum1.setLayoutX(65);
        changeNum1.setLayoutY(273);
        changeNum1.setStyle("-fx-background-color: transparent;");
        changeNum1.setFont(myFont.Outfit_Medium_14);


        Text cowNomber2 = new Text(String.valueOf(yearSum.get(0)));
        cowNomber2.setLayoutX(61);
        cowNomber2.setLayoutY(240);
        cowNomber2.setStyle("-fx-background-color: transparent;");
        cowNomber2.setFont(myFont.Outfit_Bold_32);
        cowNomber2.setFill(Color.web("#FFFFFF"));

        Text changeNum2 = new Text(String.valueOf(yearSum.get(0)-yearSum.get(1)));
        changeNum2.setLayoutX(65);
        changeNum2.setLayoutY(273);
        changeNum2.setStyle("-fx-background-color: transparent;");
        changeNum2.setFont(myFont.Outfit_Medium_14);


        if ((daySum.get(0)-daySum.get(1)) >0 ){
            changeNum.setFill(Color.web("#BB4848"));
        }else {
            changeNum.setFill(Color.web("#48BB78"));
        }

        if ((monthSum.get(0)-monthSum.get(1)) >0 ){
            changeNum1.setFill(Color.web("#BB4848"));
        }else {
            changeNum1.setFill(Color.web("#48BB78"));
        }

        if ((yearSum.get(0)-yearSum.get(1)) >0 ){
            changeNum2.setFill(Color.web("#BB4848"));
        }else {
            changeNum2.setFill(Color.web("#48BB78"));
        }

        Button getCsvButton = new Button("");
        Image ongetCsvButtonImage = new Image("res/images/control/cv/onCSV.png");
        ImageView ongetCsvButtonImageView = new ImageView(ongetCsvButtonImage);
        ongetCsvButtonImageView.setLayoutX(621-6);
        ongetCsvButtonImageView.setLayoutY(422-7);
        ongetCsvButtonImageView.setFitWidth(447);
        ongetCsvButtonImageView.setFitHeight(54);
        //backgroundImageView.setPreserveRatio(true);
        ongetCsvButtonImageView.setSmooth(true);

        getCsvImageView.setLayoutX(624-6);
        getCsvImageView.setLayoutY(429-7);
        getCsvImageView.setFitWidth(445);
        getCsvImageView.setFitHeight(47);
        //backgroundImageView.setPreserveRatio(true);
        getCsvImageView.setSmooth(true);



        getCsvButton.setText("");
        getCsvButton.setOnAction(e -> {
            getCsvButton.setOpacity(0.6);
            dataBlock.getChildren().remove(ongetCsvButtonImageView);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {
                        getCsvButton.setOpacity(0.1);
                        DirectoryChooser directoryChooser = new DirectoryChooser();
                        directoryChooser.setTitle("选择文件的上一级目录");
                        File selectedDirectory = directoryChooser.showDialog(stage);


                        String[] path = new String[]{"dayCsv.csv","monthCsv.csv","yearCsv.csv"};

                        for (String str: path) {
                            String sourceFilePath = String.format("csv/timeCsv/%s", str);
                            String destinationFilePath = String.format("%s/%s", selectedDirectory.getAbsolutePath(), str);
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



                        }




                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            timeline.play();




        });
        getCsvButton.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;",myColor.homeBackColor));
        getCsvButton.setOpacity(0.1);
        getCsvButton.setLayoutX(621);
        getCsvButton.setLayoutY(422);
        getCsvButton.setPrefWidth(439);
        getCsvButton.setPrefHeight(39);
        getCsvButton.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                dataBlock.getChildren().add(dataBlock.getChildren().indexOf(getCsvButton)-1,ongetCsvButtonImageView);

            }else {
                dataBlock.getChildren().remove(ongetCsvButtonImageView);

            }
        });

        dataUp.setText("");
        dataUp.setOnAction(e -> {
            dataUp.setOpacity(0.6);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {
                        dataBlock.setPrefWidth(196);
                        dataBlock.setPrefHeight(139);
                        dataButton.setOpacity(0.1);
                        data_close(power,group,onGroup);

                        dataBlock.getChildren().remove(tabelBlock);
                        dataBlock.getChildren().remove(tabelBlock1);
                        dataBlock.getChildren().remove(tabelBlock2);
                        dataBlock.getChildren().remove(getCsvButton);
                        dataBlock.getChildren().remove(getCsvImageView);
                        dataBlock.getChildren().remove(dataUp);
                        anima.zoom_size(1118,511,196,139,200,opendataBlockImageView);
                        anima.translateTransition(400,200,-500,-200,200,opendataBlockImageView);
                        Timeline timeline1 = new Timeline(new KeyFrame(Duration.millis(200), e2 -> {
                            anima.back(dataBlockImageView);
                            dataBlock.getChildren().add(dataBlock.getChildren().indexOf(opendataBlockImageView)+1,dataBlockImageView);
                            dataBlock.getChildren().remove(opendataBlockImageView);
                            dataBlock.getChildren().add(dataButton);
                            dataBlock.getChildren().add(cokeImageView);
                        }));
                        timeline1.setCycleCount(1);
                        timeline1.play();



                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            // 开始播放动画
            timeline.play();

        });
        dataUp.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;", myColor.chooseGroundColor));
        dataUp.setOpacity(0.3);
        dataUp.setPrefWidth(24);
        dataUp.setPrefHeight(24);
        dataUp.setLayoutX(1070);
        dataUp.setLayoutY(472);



        dataButton.setText("");
        dataButton.setOnAction(e -> {
            dataButton.setOpacity(0.6);
            dataBlock.getChildren().remove(ondataBlockImageView);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {
                        dataBlock.setPrefWidth(1118);
                        dataBlock.setPrefHeight(511);
                        dataButton.setOpacity(0.1);
                        data_open(power,group,onGroup);
                        dataBlock.getChildren().remove(dataButton);
                        dataBlock.getChildren().remove(cokeImageView);
                        anima.zoom_size(196,139,1118,511,200,dataBlockImageView);
                        anima.translateTransition(100,0,400,200,200,dataBlockImageView);
                        Timeline timeline1 = new Timeline(new KeyFrame(Duration.millis(200), e2 -> {
                            anima.back(opendataBlockImageView);
                            dataBlock.getChildren().add(dataBlock.getChildren().indexOf(dataBlockImageView)+1,opendataBlockImageView);
                            dataBlock.getChildren().remove(dataBlockImageView);
                            dataBlock.getChildren().add(tabelBlock);
                            dataBlock.getChildren().add(tabelBlock1);
                            dataBlock.getChildren().add(tabelBlock2);
                            dataBlock.getChildren().add(getCsvImageView);
                            dataBlock.getChildren().add(getCsvButton);
                            dataBlock.getChildren().add(dataUp);

                        }));
                        timeline1.setCycleCount(1);
                        timeline1.play();


                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            timeline.play();




        });
        dataButton.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;",myColor.backgroundColor));
        dataButton.setOpacity(0.1);
        dataButton.setPrefWidth(196);
        dataButton.setPrefHeight(139);
        dataButton.setLayoutX(0);
        dataButton.setLayoutY(0);
        dataButton.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                dataBlock.getChildren().add(1,ondataBlockImageView);

            }else {
                dataBlock.getChildren().remove(ondataBlockImageView);

            }
        });


        dataBlock.getChildren().add(dataBlockImageView);
        dataBlock.getChildren().add(dataTileImageView);
        dataBlock.getChildren().add(cokeImageView);
        dataBlock.getChildren().add(dataButton);


        tabelBlock.getChildren().add(tableBackgroundImageView);
        tabelBlock.getChildren().add(tableTitleImageView);
        tabelBlock.getChildren().add(cowImageView);
        tabelBlock.getChildren().add(changeupdownImageView);
        tabelBlock.getChildren().add(dayImageView);
        tabelBlock.getChildren().add(cowNomber);
        tabelBlock.getChildren().add(changeNum);
        tabelBlock.getChildren().add(day_chart.root);

        tabelBlock1.getChildren().add(tableBackgroundImageView1);
        tabelBlock1.getChildren().add(tableTitleImageView1);
        tabelBlock1.getChildren().add(cowImageView1);
        tabelBlock1.getChildren().add(changeupdownImageView1);
        tabelBlock1.getChildren().add(dayImageView1);
        tabelBlock1.getChildren().add(cowNomber1);
        tabelBlock1.getChildren().add(changeNum1);
        tabelBlock1.getChildren().add(month_chart.root);

        tabelBlock2.getChildren().add(tableBackgroundImageView2);
        tabelBlock2.getChildren().add(tableTitleImageView2);
        tabelBlock2.getChildren().add(cowImageView2);
        tabelBlock2.getChildren().add(changeupdownImageView2);
        tabelBlock2.getChildren().add(dayImageView2);
        tabelBlock2.getChildren().add(cowNomber2);
        tabelBlock2.getChildren().add(changeNum2);
        tabelBlock2.getChildren().add(year_chart.root);


        power.setLayoutX(438);
        power.setLayoutY(152);
        power.setPrefWidth(888);
        power.setPrefHeight(139);


        powerBlockImageView.setLayoutX(0);
        powerBlockImageView.setLayoutY(0);
        powerBlockImageView.setFitWidth(888);
        powerBlockImageView.setFitHeight(139);
        //backgroundImageView.setPreserveRatio(true);
        powerBlockImageView.setSmooth(true);


        powerTitleImageView.setLayoutX(22);
        powerTitleImageView.setLayoutY(13);
        powerTitleImageView.setFitWidth(86);
        powerTitleImageView.setFitHeight(20);
        //backgroundImageView.setPreserveRatio(true);
        powerTitleImageView.setSmooth(true);


        cpuTitleImageView.setLayoutX(62);
        cpuTitleImageView.setLayoutY(52);
        cpuTitleImageView.setFitWidth(72);
        cpuTitleImageView.setFitHeight(13);
        //backgroundImageView.setPreserveRatio(true);
        cpuTitleImageView.setSmooth(true);


        memoryTitleImageView.setLayoutX(108);
        memoryTitleImageView.setLayoutY(93);
        memoryTitleImageView.setFitWidth(26);
        memoryTitleImageView.setFitHeight(13);
        //backgroundImageView.setPreserveRatio(true);
        memoryTitleImageView.setSmooth(true);

        netTitleImageView.setLayoutX(108);
        netTitleImageView.setLayoutY(138);
        netTitleImageView.setFitWidth(26);
        netTitleImageView.setFitHeight(13);
        //backgroundImageView.setPreserveRatio(true);
        netTitleImageView.setSmooth(true);

        gpuTitleImageView.setLayoutX(107);
        gpuTitleImageView.setLayoutY(181);
        gpuTitleImageView.setFitWidth(28);
        gpuTitleImageView.setFitHeight(10);
        //backgroundImageView.setPreserveRatio(true);
        gpuTitleImageView.setSmooth(true);

        powerMsgImageView.setLayoutX(49);
        powerMsgImageView.setLayoutY(228);
        powerMsgImageView.setFitWidth(816);
        powerMsgImageView.setFitHeight(238);
        //backgroundImageView.setPreserveRatio(true);
        powerMsgImageView.setSmooth(true);

        downImageView.setLayoutX(851);
        downImageView.setLayoutY(17);
        downImageView.setFitWidth(24);
        downImageView.setFitHeight(24);
        //backgroundImageView.setPreserveRatio(true);
        downImageView.setSmooth(true);

        cpuText.setLayoutX(75);
        cpuText.setLayoutY(265);
        cpuText.setStyle("-fx-background-color: transparent;");
        cpuText.setFont(myFont.Outfit_Medium_14);
        cpuText.setFill(Color.web(myColor.OnchooseGroundColor1));

        gpuText.setLayoutX(75);
        gpuText.setLayoutY(295);
        gpuText.setStyle("-fx-background-color: transparent;");
        gpuText.setFont(myFont.Outfit_Medium_14);
        gpuText.setFill(Color.web(myColor.OnchooseGroundColor1));

        memoryText.setLayoutX(75);
        memoryText.setLayoutY(325);
        memoryText.setStyle("-fx-background-color: transparent;");
        memoryText.setFont(myFont.Outfit_Medium_14);
        memoryText.setFill(Color.web(myColor.OnchooseGroundColor1));

        wlanSsidText.setLayoutX(482);
        wlanSsidText.setLayoutY(265);
        wlanSsidText.setStyle("-fx-background-color: transparent;");
        wlanSsidText.setFont(myFont.Outfit_Medium_14);
        wlanSsidText.setFill(Color.web(myColor.OnchooseGroundColor1));

        wlanPasswdText.setLayoutX(482);
        wlanPasswdText.setLayoutY(295);
        wlanPasswdText.setStyle("-fx-background-color: transparent;");
        wlanPasswdText.setFont(myFont.Outfit_Medium_14);
        wlanPasswdText.setFill(Color.web(myColor.OnchooseGroundColor1));

        username.setLayoutX(482);
        username.setLayoutY(325);
        username.setStyle("-fx-background-color: transparent;");
        username.setFont(myFont.Outfit_Medium_14);
        username.setFill(Color.web(myColor.OnchooseGroundColor1));



        down.setText("");
        down.setOnAction(e -> {
            down.setOpacity(0.6);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {

                        power.setPrefWidth(888);
                        power.setPrefHeight(502);
                        down.setOpacity(0.1);
                        power_open(group,onGroup);
                        power.getChildren().remove(downImageView);
                        power.getChildren().remove(down);
                        anima.zoom_size(888,139,888,502,200,powerBlockImageView);
                        anima.translateTransition(0,0,0,200,200,powerBlockImageView);
                        Timeline timeline1 = new Timeline(new KeyFrame(Duration.millis(200), e2 -> {
                            anima.back(openPowerBlockImageView);
                            power.getChildren().add(dataBlock.getChildren().indexOf(powerBlockImageView)+1,openPowerBlockImageView);
                            power.getChildren().remove(powerBlockImageView);
                            power.getChildren().add(powerUp);
                            power.getChildren().add(netPersent.getRoot());
                            power.getChildren().add(gpuPersent.getRoot());
                            power.getChildren().add(netTitleImageView);
                            power.getChildren().add(gpuTitleImageView);
                            power.getChildren().add(powerMsgImageView);
                            power.getChildren().add(cpuText);
                            power.getChildren().add(gpuText);
                            power.getChildren().add(memoryText);
                            power.getChildren().add(wlanSsidText);
                            power.getChildren().add(wlanPasswdText);
                            power.getChildren().add(username);

                        }));
                        timeline1.setCycleCount(1);
                        timeline1.play();

                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            // 开始播放动画
            timeline.play();

        });
        down.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;", myColor.chooseGroundColor));
        down.setOpacity(0.3);
        down.setPrefWidth(24);
        down.setPrefHeight(24);
        down.setLayoutX(852);
        down.setLayoutY(12);

        powerUp.setText("");
        powerUp.setOnAction(e -> {
            powerUp.setOpacity(0.6);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {
                        power.setPrefWidth(196);
                        power.setPrefHeight(139);
                        powerUp.setOpacity(0.1);
                        power_close(group,onGroup);
                        power.getChildren().remove(powerUp);
                        power.getChildren().remove(netPersent.getRoot());
                        power.getChildren().remove(gpuPersent.getRoot());
                        power.getChildren().remove(netTitleImageView);
                        power.getChildren().remove(gpuTitleImageView);
                        power.getChildren().remove(powerMsgImageView);
                        power.getChildren().remove(cpuText);
                        power.getChildren().remove(gpuText);
                        power.getChildren().remove(memoryText);
                        power.getChildren().remove(wlanSsidText);
                        power.getChildren().remove(wlanPasswdText);
                        power.getChildren().remove(username);
                        anima.zoom_size(888,502,888,139,200,openPowerBlockImageView);
                        anima.translateTransition(0,0,0,-200,200,openPowerBlockImageView);
                        Timeline timeline1 = new Timeline(new KeyFrame(Duration.millis(200), e2 -> {
                            anima.back(powerBlockImageView);
                            power.getChildren().add(dataBlock.getChildren().indexOf(openPowerBlockImageView)+1,powerBlockImageView);
                            power.getChildren().remove(openPowerBlockImageView);
                            power.getChildren().add(down);
                            power.getChildren().add(downImageView);

                        }));
                        timeline1.setCycleCount(1);
                        timeline1.play();



                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            // 开始播放动画
            timeline.play();

        });
        powerUp.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;", myColor.chooseGroundColor));
        powerUp.setOpacity(0.3);
        powerUp.setPrefWidth(24);
        powerUp.setPrefHeight(24);
        powerUp.setLayoutX(853);
        powerUp.setLayoutY(466);

        power.getChildren().add(powerBlockImageView);
        power.getChildren().add(powerTitleImageView);
        power.getChildren().add(cpuTitleImageView);
        power.getChildren().add(memoryTitleImageView);
        power.getChildren().add(downImageView);
        power.getChildren().add(cpuPersent.getRoot());
        power.getChildren().add(memPersent.getRoot());
        power.getChildren().add(down);



        group.setLayoutX(91);
        group.setLayoutY(418);
        group.setPrefWidth(1133);
        group.setPrefHeight(194);


        groupImageView.setLayoutX(0);
        groupImageView.setLayoutY(4);
        groupImageView.setFitWidth(1135);
        groupImageView.setFitHeight(185);
        //backgroundImageView.setPreserveRatio(true);
        groupImageView.setSmooth(true);


        onNoticeImageView.setLayoutX(0);
        onNoticeImageView.setLayoutY(4);
        onNoticeImageView.setFitWidth(1135);
        onNoticeImageView.setFitHeight(78);
        //backgroundImageView.setPreserveRatio(true);
        onNoticeImageView.setSmooth(true);


        onControlImageView.setLayoutX(0);
        onControlImageView.setLayoutY(111);
        onControlImageView.setFitWidth(1135);
        onControlImageView.setFitHeight(78);
        //backgroundImageView.setPreserveRatio(true);
        onControlImageView.setSmooth(true);


        onNoticeButton.setText("");
        onNoticeButton.setOnAction(e -> {
            onNoticeButton.setOpacity(0.6);
            group.getChildren().remove(onNoticeImageView);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {

                        onNoticeButton.setOpacity(0.1);


                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            timeline.play();

            if (isNoticeOpen) {
                noticePage.setAlwaysOnTop(true);
                noticePage.setAlwaysOnTop(false);
            }else{
                noticePage_Start();
                isNoticeOpen = true;
            }


        });
        onNoticeButton.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;",myColor.backgroundColor));
        onNoticeButton.setOpacity(0.1);
        onNoticeButton.setPrefWidth(1135);
        onNoticeButton.setPrefHeight(78);
        onNoticeButton.setLayoutX(0);
        onNoticeButton.setLayoutY(4);
        onNoticeButton.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                group.getChildren().add(group.getChildren().indexOf(onNoticeButton),onNoticeImageView);

            }else {
                group.getChildren().remove(onNoticeImageView);

            }
        });


        onControlButton.setText("");
        onControlButton.setOnAction(e -> {
            onControlButton.setOpacity(0.6);
            group.getChildren().remove(onControlImageView);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {

                        onControlButton.setOpacity(0.1);

                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            timeline.play();

            if (isControlopen) {
                controlPage.setAlwaysOnTop(true);
                controlPage.setAlwaysOnTop(false);
            }else{
                controlPage_Start();
                isControlopen = true;
            }


        });
        onControlButton.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;",myColor.backgroundColor));
        onControlButton.setOpacity(0.1);
        onControlButton.setPrefWidth(1135);
        onControlButton.setPrefHeight(78);
        onControlButton.setLayoutX(0);
        onControlButton.setLayoutY(111);
        onControlButton.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                group.getChildren().add(group.getChildren().indexOf(onControlButton)-1,onControlImageView);

            }else {
                group.getChildren().remove(onControlImageView);

            }
        });

        onGroup.setLayoutX(-400);
        onGroup.setLayoutY(334);
        onGroup.setPrefWidth(196);
        onGroup.setPrefHeight(318);


        groupImageView1.setLayoutX(0);
        groupImageView1.setLayoutY(0);
        groupImageView1.setFitWidth(196);
        groupImageView1.setFitHeight(318);
        //backgroundImageView.setPreserveRatio(true);
        groupImageView1.setSmooth(true);


        onNoticeImageView1.setLayoutX(0);
        onNoticeImageView1.setLayoutY(0);
        onNoticeImageView1.setFitWidth(196);
        onNoticeImageView1.setFitHeight(139);
        //backgroundImageView.setPreserveRatio(true);
        onNoticeImageView1.setSmooth(true);


        onControlImageView1.setLayoutX(0);
        onControlImageView1.setLayoutY(179);
        onControlImageView1.setFitWidth(196);
        onControlImageView1.setFitHeight(139);
        //backgroundImageView.setPreserveRatio(true);
        onControlImageView1.setSmooth(true);


        onNoticeButton1.setText("");
        onNoticeButton1.setOnAction(e -> {
            onNoticeButton1.setOpacity(0.6);
            onGroup.getChildren().remove(onNoticeImageView1);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {

                        onNoticeButton1.setOpacity(0.1);

                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            timeline.play();

            if (isNoticeOpen) {
                noticePage.setAlwaysOnTop(true);
                noticePage.setAlwaysOnTop(false);
            }else{
                noticePage_Start();
                isNoticeOpen = true;
            }



        });
        onNoticeButton1.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;",myColor.backgroundColor));
        onNoticeButton1.setOpacity(0.1);
        onNoticeButton1.setPrefWidth(196);
        onNoticeButton1.setPrefHeight(139);
        onNoticeButton1.setLayoutX(0);
        onNoticeButton1.setLayoutY(0);
        onNoticeButton1.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                onGroup.getChildren().add(onGroup.getChildren().indexOf(onNoticeButton1),onNoticeImageView1);

            }else {
                onGroup.getChildren().remove(onNoticeImageView1);

            }
        });


        onControlButton1.setText("");
        onControlButton1.setOnAction(e -> {
            onControlButton1.setOpacity(0.6);
            onGroup.getChildren().remove(onControlImageView1);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {

                        onControlButton1.setOpacity(0.1);

                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            timeline.play();

            if (isControlopen) {
                controlPage.setAlwaysOnTop(true);
                controlPage.setAlwaysOnTop(false);
            }else{
                controlPage_Start();
                isControlopen = true;
            }


        });
        onControlButton1.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;",myColor.backgroundColor));
        onControlButton1.setOpacity(0.1);
        onControlButton1.setPrefWidth(196);
        onControlButton1.setPrefHeight(139);
        onControlButton1.setLayoutX(0);
        onControlButton1.setLayoutY(179);
        onControlButton1.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                onGroup.getChildren().add(onGroup.getChildren().indexOf(onControlButton1)-1,onControlImageView1);

            }else {
                onGroup.getChildren().remove(onControlImageView1);

            }
        });

        onGroup.getChildren().add(groupImageView1);
        onGroup.getChildren().add(onNoticeButton1);
        onGroup.getChildren().add(onControlButton1);

        group.getChildren().add(groupImageView);
        group.getChildren().add(onNoticeButton);
        group.getChildren().add(onControlButton);





        Root.getChildren().add(dataBlock);
        Root.getChildren().add(group);
        Root.getChildren().add(power);
        Root.getChildren().add(onGroup);


        cpuPersent.start(cpu);
        gpuPersent.start(gpu);
        memPersent.start(mem);
        timeline = new Timeline(new KeyFrame(Duration.millis(5000), e1 -> {
            try {
                cpuGpuMem.getCPUUsage();
                cpuPersent.start(cpu);
                gpuPersent.start(gpu);
                double i = 0;
                for (roomblock r:Main.roomblocks){
                    if (r.isRtmpOpen && !r.yolo.over){
                        i++;
                    }
                }
                netPersent.start(i*(double)rtmpClient.bits/(double)rtmpClient.bandwidth);
                memPersent.start(mem);


            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }));







        anima.translateTransition_G(0,0,100,0,200,20,dataBlock);
        anima.translateTransition_G(0,0,-100,0,200,20,power);
        anima.translateTransition_G(0,0,0,-100,200,20,group);
        chart_update();

        cpuPersent.start(0);
        gpuPersent.start(0);
        netPersent.start(0);
        memPersent.start(0);


        timeline.setCycleCount(-1);
        timeline.play();




    }

    public void chart_update(){
        Timeline timeline = new Timeline();

        for (int i = 4; i >= 0 ; i--) {
            // 创建 KeyFrame，设置延迟时间和要执行的操作
            int finalI = i;
            KeyFrame keyFrame = new KeyFrame(
                    Duration.millis(500 * (4-i)),
                    event -> {
                        day_chart.data_update(daySum.get(finalI));
                        month_chart.data_update(monthSum.get(finalI));
                        year_chart.data_update(yearSum.get(finalI));
                    }
            );
            // 将 KeyFrame 添加到 Timeline 中
            timeline.getKeyFrames().add(keyFrame);
        }
        timeline.setCycleCount(1);
        // 播放 Timeline
        timeline.play();
    }

    private void data_open(Pane power, Pane group,Pane onGroup) {
        anima.translateTransition(-100,0,1000,0,300,power);
        anima.translateTransition(group.getTranslateX(),-100,group.getTranslateX(),400,300,group);
        anima.translateTransition(onGroup.getTranslateX(),0,onGroup.getTranslateX(),500,300,onGroup);

    }
    private void data_close(Pane power, Pane group,Pane onGroup) {
        anima.translateTransition(1000,0,-100,0,300,power);
        anima.translateTransition(group.getTranslateX(),400,group.getTranslateX(),-100,300,group);
        anima.translateTransition(onGroup.getTranslateX(),500,onGroup.getTranslateX(),0,300,onGroup);

    }

    private void power_open(Pane group,Pane onGroup) {
        anima.translateTransition(0,-100,1600,-100,200,group);
        anima.translateTransition(0,0,496,0,300,onGroup);
    }

    private void power_close(Pane group,Pane onGroup) {
        anima.translateTransition(1600,-100,0,-100,200,group);
        anima.translateTransition(496,0,0,0,300,onGroup);
    }

    private void noticePage_Start(){
        Stage stage = new Stage();
        noticePage = stage;
        Pane root = new Pane();
        Scene scene = new Scene(root, 861, 561);

        block.PaneRoundedRectangle(root,30);
        block.PanesetOnMouseMove(root,stage);

        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        noticePage_Init(root);
        stage.show();


    }

    private void noticePage_Init(Pane root) {
        Image noticeBackgroundImage = new Image("res/images/home/noticePage.png");
        ImageView noticeBackgroundImageView = new ImageView(noticeBackgroundImage);
        noticeBackgroundImageView.setLayoutX(-30);
        noticeBackgroundImageView.setLayoutY(0);
        noticeBackgroundImageView.setFitWidth(861);
        noticeBackgroundImageView.setFitHeight(561);
        //backgroundImageView.setPreserveRatio(true);
        noticeBackgroundImageView.setSmooth(true);

        Button exitButton = new Button();
        exitButton.setText("");
        exitButton.setOnAction(e -> {
            exitButton.setOpacity(0.6);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {

                        exitButton.setOpacity(0.3);
                        isNoticeOpen = false;
                        noticePage.close();
                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            // 开始播放动画
            timeline.play();

        });
        exitButton.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;",myColor.backgroundColor));
        exitButton.setOpacity(0.3);
        exitButton.setPrefWidth(32);
        exitButton.setPrefHeight(32);
        exitButton.setLayoutX(798);
        exitButton.setLayoutY(15);

        msgVBox = new VBox();
        msgVBox.setStyle("-fx-background-color: transparent;");

        showMsg(msgVBox);

        // 创建一个滚动面板并将垂直布局容器添加到其中
        ScrollPane scrollPane = new ScrollPane(msgVBox);
        // 设置滚动面板的滚动方向为垂直
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPrefWidth(797);
        scrollPane.setPrefHeight(435);
        scrollPane.setLayoutX(39);
        scrollPane.setLayoutY(95);
        scrollPane.setStyle(
                "-fx-background-color: transparent;"
        );
        scrollPane.getStylesheets().add("data:,* { -fx-background-color: transparent; }");

        root.getChildren().add(noticeBackgroundImageView);
        root.getChildren().add(exitButton);
        root.getChildren().add(scrollPane);

        anima.translateTransition_G(0,0,30,0,100,20,noticeBackgroundImageView);
    }
    public static void showMsg(VBox msgVBox) {
        msgVBox.getChildren().clear();

        for (int i = 0; i < Main.msgBlocks.size(); i++) {
            if (Main.msgBlocks.get(i).type == msgBlock.IMSG){
                msgVBox.getChildren().add(Main.msgBlocks.get(i).root);
            }
        }
        for (int i = 0; i < Main.msgBlocks.size(); i++) {
            if (Main.msgBlocks.get(i).type == msgBlock.noIMSG){
                msgVBox.getChildren().add(Main.msgBlocks.get(i).root);
            }
        }
    }

    private void controlPage_Start(){
        Stage stage = new Stage();
        controlPage = stage;
        Pane root = new Pane();
        Scene scene = new Scene(root, 626, 476);

        block.PaneRoundedRectangle(root,30);
        block.PanesetOnMouseMove(root,stage);

        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        control_Init(root);
        stage.show();


    }

    private void control_Init(Pane root) {
        isAllDecetion = true;
        isAllRtmp = true;
        isAllBorder = true;


        for (roomblock r:Main.roomblocks) {
            if (!r.isDetection){
                isAllDecetion = false;
                break;
            }
        }
        for (roomblock r:Main.roomblocks) {
            if (!r.isRtmpOpen){
                isAllRtmp = false;
                break;
            }
        }
        for (roomblock r:Main.roomblocks) {
            if (!r.isBround){
                isAllBorder = false;
                break;
            }
        }

        Image controlBackgroundImage = new Image("res/images/home/controlPage.png");
        ImageView controlBackgroundImageView = new ImageView(controlBackgroundImage);
        controlBackgroundImageView.setLayoutX(-30);
        controlBackgroundImageView.setLayoutY(0);
        controlBackgroundImageView.setFitWidth(626);
        controlBackgroundImageView.setFitHeight(476);
        //backgroundImageView.setPreserveRatio(true);
        controlBackgroundImageView.setSmooth(true);

        Button exitButton = new Button();
        exitButton.setText("");
        exitButton.setOnAction(e -> {
            exitButton.setOpacity(0.6);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {

                        exitButton.setOpacity(0.3);
                        isControlopen = false;
                        controlPage.close();
                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            // 开始播放动画
            timeline.play();

        });
        exitButton.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;",myColor.backgroundColor));
        exitButton.setOpacity(0.3);
        exitButton.setPrefWidth(32);
        exitButton.setPrefHeight(32);
        exitButton.setLayoutX(550);
        exitButton.setLayoutY(15);

        ComboBox<Integer> hourComboBox = new ComboBox<>();
        for (int i = 0; i < 24; i++) {
            hourComboBox.getItems().add(i);
        }
        hourComboBox.setValue(yoloClient.startTime.getHour());

        // 创建分钟的下拉框
        ComboBox<Integer> minuteComboBox = new ComboBox<>();
        for (int i = 0; i < 60; i++) {
            minuteComboBox.getItems().add(i);
        }
        minuteComboBox.setValue(yoloClient.startTime.getMinute());

        ComboBox<Integer> hourComboBox1 = new ComboBox<>();
        for (int i = 0; i < 24; i++) {
            hourComboBox1.getItems().add(i);
        }
        hourComboBox1.setValue(yoloClient.endTime.getHour());

        // 创建分钟的下拉框
        ComboBox<Integer> minuteComboBox1 = new ComboBox<>();
        for (int i = 0; i < 60; i++) {
            minuteComboBox1.getItems().add(i);
        }
        minuteComboBox1.setValue(yoloClient.endTime.getMinute());


        String fontStyle = "-fx-font-family: '" + myFont.Outfit_Bold_18.getFamily() + "'; -fx-font-size: " + myFont.Outfit_Bold_18.getSize() + "px; -fx-background-color: transparent; -fx-text-fill: "+myColor.OnchooseGroundColor+" ;";
        // 下拉框的倒角、背景颜色和鼠标悬停及选中时的颜色样式
        String comboBoxExtraStyle = "-fx-background-radius: 10; " + // 设置倒角半径
                "-fx-control-inner-background: #FBFBFB ; " + // 下拉框内部背景颜色
                "-fx-selection-bar: "+myColor.OnchooseGroundColor+"; " + // 下拉框选中项背景颜色
                "-fx-selection-bar-non-focused: "+myColor.OnchooseGroundColor+"; " + // 下拉框非聚焦时选中项背景颜色
                "-fx-hover-base: gray;"; // 鼠标悬停时的颜色

        // 合并样式
        String comboBoxStyle = fontStyle + comboBoxExtraStyle;
        String scrollBarStyle =
                // 滚动条轨道宽度
                ".combo-box-popup .scroll-bar:vertical { -fx-pref-width: 5; } " +
                        ".combo-box-popup .scroll-bar:horizontal { -fx-pref-width: 5; } " +
                        // 滚动条滑块宽度
                        ".combo-box-popup .scroll-bar:vertical .thumb { -fx-pref-width: 5; } " +
                        ".combo-box-popup .scroll-bar:horizontal .thumb { -fx-pref-width: 5; } ";


        hourComboBox.setStyle(comboBoxStyle);
        hourComboBox.getStylesheets().add("data:text/css," + scrollBarStyle.replaceAll("\n", ""));
        minuteComboBox.setStyle(comboBoxStyle);
        minuteComboBox.getStylesheets().add("data:text/css," + scrollBarStyle.replaceAll("\n", ""));
        hourComboBox1.setStyle(comboBoxStyle);
        hourComboBox1.getStylesheets().add("data:text/css," + scrollBarStyle.replaceAll("\n", ""));
        minuteComboBox1.setStyle(comboBoxStyle);
        minuteComboBox1.getStylesheets().add("data:text/css," + scrollBarStyle.replaceAll("\n", ""));



        hourComboBox.setLayoutX(356);
        hourComboBox.setLayoutY(110);

        minuteComboBox.setLayoutX(463);
        minuteComboBox.setLayoutY(110);

        hourComboBox1.setLayoutX(356);
        hourComboBox1.setLayoutY(225);

        minuteComboBox1.setLayoutX(463);
        minuteComboBox1.setLayoutY(225);



        // 监听选择事件
        hourComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(" 开始的时间是: " + newValue + ":" + minuteComboBox.getValue());
        });
        minuteComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("开始的时间是: " + hourComboBox.getValue() + ":" + newValue);
        });

        hourComboBox1.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("结束的时间是: " + newValue + ":" + minuteComboBox1.getValue());
        });
        minuteComboBox1.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("结束的时间是: " + hourComboBox1.getValue() + ":" + newValue);
        });

        switchButton allDetectionButton = new switchButton(isAllDecetion,230,109,52,32,2,30);
        allDetectionButton.switchButton.setOnAction(event -> {
            allDetectionButton.tap();

        });

        switchButton allRtmpButton = new switchButton(isAllRtmp,230,169,52,32,2,30);
        allRtmpButton.switchButton.setOnAction(event -> {
            allRtmpButton.tap();

        });

        switchButton allBroderButton = new switchButton(isAllBorder,230,235,52,32,2,30);
        allBroderButton.switchButton.setOnAction(event -> {
            allBroderButton.tap();

        });

        switchButton nPDButton = new switchButton(yoloClient.isPersonDetected,230,301,52,32,2,30);
        nPDButton.switchButton.setOnAction(event -> {
            nPDButton.tap();

        });

        Image saveButton_EImage = new Image("res/images/home/onSave.png");
        ImageView saveButton_EImageView = new ImageView(saveButton_EImage);
        saveButton_EImageView.setLayoutX(375);
        saveButton_EImageView.setLayoutY(385);
        saveButton_EImageView.setFitWidth(217);
        saveButton_EImageView.setFitHeight(54);
        //backgroundImageView.setPreserveRatio(true);
        saveButton_EImageView.setSmooth(true);

        Button saveButton = new Button();
        saveButton.setText("");
        saveButton.setOnAction(e -> {
            saveButton.setOpacity(0.6);
            root.getChildren().remove(saveButton_EImageView);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {

                        saveButton.setOpacity(0.1);
                        isControlopen = false;
                        controlPage.close();
                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            timeline.play();






            try {
                Ini ini = Main.ini;
                // 读取 INI 文件
                yoloClient.startTime = LocalTime.of(hourComboBox.getValue(), minuteComboBox.getValue());
                yoloClient.endTime = LocalTime.of(hourComboBox1.getValue(), minuteComboBox1.getValue());
                yoloClient.isPersonDetected = nPDButton.isopen;



                ini.put("Yolo", "PersonStart",new String(hourComboBox.getValue()+":"+minuteComboBox.getValue()+":00"));
                ini.put("Yolo", "PersonEnd",new String(hourComboBox1.getValue()+":"+minuteComboBox1.getValue()+":00"));
                ini.put("Yolo","isPersonDetection",String.valueOf(yoloClient.isPersonDetected));
                if (isAllDecetion != allDetectionButton.isopen || isAllRtmp != allRtmpButton.isopen || isAllBorder != allBroderButton.isopen ) {
                    if (isAllDecetion != allDetectionButton.isopen){
                        for (roomblock r:Main.roomblocks){
                            r.isDetection = allDetectionButton.isopen;
                        }
                    }

                    if (isAllRtmp != allRtmpButton.isopen){
                        for (roomblock r:Main.roomblocks){
                            r.isRtmpOpen = allRtmpButton.isopen;
                        }
                    }

                    if (isAllBorder != allBroderButton.isopen){
                        for (roomblock r:Main.roomblocks){
                            r.isBround = allBroderButton.isopen;
                        }
                    }
                    int i = 0;
                    for (roomblock r:Main.roomblocks){
                        ini.put(String.format("roomblock%d", i), "roomName",r.roomName);
                        ini.put(String.format("roomblock%d", i), "numberHead",r.numberHead);
                        ini.put(String.format("roomblock%d", i), "numberTail",r.numberTail);
                        ini.put(String.format("roomblock%d", i), "com",String.valueOf(r.com));
                        ini.put(String.format("roomblock%d", i), "avg",String.valueOf(r.avg));
                        ini.put(String.format("roomblock%d", i), "isDetection",String.valueOf(r.isDetection));
                        ini.put(String.format("roomblock%d", i), "isBround",String.valueOf(r.isBround));
                        ini.put(String.format("roomblock%d", i), "isDiscover",String.valueOf(r.isDiscover));
                        ini.put(String.format("roomblock%d", i), "isRtmpOpen",String.valueOf(r.isRtmpOpen));
                        i++;
                    }

                }


                // 写入 INI 文件
                ini.store(new File("setting.ini"));
                timeline.play();

            } catch (IOException e1) {
                e1.printStackTrace();
            }



        });
        saveButton.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;",myColor.backgroundColor));
        saveButton.setOpacity(0.1);
        saveButton.setPrefWidth(217);
        saveButton.setPrefHeight(54);
        saveButton.setLayoutX(372);
        saveButton.setLayoutY(379);
        saveButton.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                root.getChildren().add(root.getChildren().indexOf(saveButton)-1,saveButton_EImageView);

            }else {
                root.getChildren().remove(saveButton_EImageView);

            }
        });

        root.getChildren().add(controlBackgroundImageView);
        root.getChildren().add(exitButton);
        root.getChildren().add(hourComboBox);
        root.getChildren().add(minuteComboBox);
        root.getChildren().add(hourComboBox1);
        root.getChildren().add(minuteComboBox1);
        root.getChildren().add(allDetectionButton.root);
        root.getChildren().add(allRtmpButton.root);
        root.getChildren().add(allBroderButton.root);
        root.getChildren().add(nPDButton.root);
        root.getChildren().add(saveButton);


        anima.translateTransition_G(0,0,30,0,100,20,controlBackgroundImageView);
    }



    public void choose(Pane root) {
        Pane root2 = new Pane();
        root2.setLayoutX(413);
        root2.setLayoutY(21);
        root2.setPrefWidth(440);
        root2.setPrefHeight(77);

        Image backgroundImage = new Image("res/images/choose/background.png");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setLayoutX(0);
        backgroundImageView.setLayoutY(0);
        backgroundImageView.setFitWidth(440);
        backgroundImageView.setFitHeight(77);
        //backgroundImageView.setPreserveRatio(true);
        backgroundImageView.setSmooth(true);

        Image settingImage = new Image("res/images/choose/settingOff.png");
        ImageView settingImageView = new ImageView(settingImage);
        settingImageView.setLayoutX(23);
        settingImageView.setLayoutY(7);
        settingImageView.setFitWidth(64);
        settingImageView.setFitHeight(64);
        //backgroundImageView.setPreserveRatio(true);
        settingImageView.setSmooth(true);

        Image homeImage = new Image("res/images/choose/homeOn.png");
        ImageView homeImageView = new ImageView(homeImage);
        homeImageView.setLayoutX(133);
        homeImageView.setLayoutY(7);
        homeImageView.setFitWidth(64);
        homeImageView.setFitHeight(64);
        //homeImageView.setPreserveRatio(true);
        homeImageView.setSmooth(true);

        Image contrlImage = new Image("choose/controlOff.png");
        ImageView contrlImageView = new ImageView(contrlImage);
        contrlImageView.setLayoutX(248);
        contrlImageView.setLayoutY(7);
        contrlImageView.setFitWidth(64);
        contrlImageView.setFitHeight(64);
        //backgroundImageView.setPreserveRatio(true);
        contrlImageView.setSmooth(true);

        Image shutdownImage = new Image("res/images/choose/shutdownOff.png");
        ImageView shutdownImageView = new ImageView(shutdownImage);
        shutdownImageView.setLayoutX(358);
        shutdownImageView.setLayoutY(7);
        shutdownImageView.setFitWidth(64);
        shutdownImageView.setFitHeight(64);
        //backgroundImageView.setPreserveRatio(true);
        shutdownImageView.setSmooth(true);

        Image chooseImage = new Image("res/images/choose/choose.png");
        ImageView chooseImageView = new ImageView(chooseImage);
        switch(onchoose){
            case 0:chooseImageView.setLayoutX(0);break;
            case 1:chooseImageView.setLayoutX(108);break;
            case 2:chooseImageView.setLayoutX(223);break;
            case 3:chooseImageView.setLayoutX(326);break;
        }
        chooseImageView.setLayoutY(0);
        chooseImageView.setFitWidth(114);
        chooseImageView.setFitHeight(78);
        //backgroundImageView.setPreserveRatio(true);
        chooseImageView.setSmooth(true);

        Button settingButton = new Button();
        Button homeButton = new Button();
        Button controlButton = new Button();
        Button shutdownButton = new Button();

        settingButton.setText("");
        settingButton.setOnAction(e -> {
            settingButton.setOpacity(0.6);
            timeline.stop();
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {

                        settingButton.setOpacity(0.3);
                        onchoose = 0;
                        chooseUpdate(settingImageView,homeImageView,contrlImageView,shutdownImageView,chooseImageView,root2);
                        //chooseBack(settingButton,homeButton,controlButton,shutdownButton);
                        chooseBack(settingButton,homeButton,controlButton,shutdownButton);
                        rootChange();oldchoose = onchoose;
                        Timeline timeline1 = new Timeline(new KeyFrame(Duration.millis(200), e2 -> {
                            Root.getChildren().clear();
                            anima.back(Root);
                            Setting.init();
                        }));
                        timeline1.setCycleCount(1);
                        timeline1.play();


                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            // 开始播放动画
            timeline.play();


        });
        settingButton.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;",myColor.chooseGroundColor));
        settingButton.setOpacity(0.3);
        settingButton.setLayoutX(23);
        settingButton.setLayoutY(7);
        settingButton.setPrefWidth(64);
        settingButton.setPrefHeight(64);


        homeButton.setText("");
        homeButton.setOnAction(e -> {
            homeButton.setOpacity(0.6);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {

                        homeButton.setOpacity(0.3);
                        onchoose = 1;
                        chooseUpdate(settingImageView,homeImageView,contrlImageView,shutdownImageView,chooseImageView,root2);
                        chooseBack(settingButton,homeButton,controlButton,shutdownButton);
                        rootChange();
                        oldchoose = onchoose;
                        Timeline timeline1 = new Timeline(new KeyFrame(Duration.millis(200), e2 -> {
                            Root.getChildren().clear();
                            anima.back(Root);
                            Main.home.init();
                        }));
                        timeline1.setCycleCount(1);
                        timeline1.play();





                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            // 开始播放动画
            timeline.play();

        });
        homeButton.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;",myColor.chooseGroundColor));
        homeButton.setOpacity(0.3);
        homeButton.setLayoutX(133);
        homeButton.setLayoutY(7);
        homeButton.setPrefWidth(64);
        homeButton.setPrefHeight(64);


        controlButton.setText("");
        controlButton.setOnAction(e -> {
            controlButton.setOpacity(0.6);
            timeline.stop();
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {

                        controlButton.setOpacity(0.3);
                        onchoose = 2;
                        chooseUpdate(settingImageView,homeImageView,contrlImageView,shutdownImageView,chooseImageView,root2);
                        //chooseBack(settingButton,homeButton,controlButton,shutdownButton);
                        chooseBack(settingButton,homeButton,controlButton,shutdownButton);
                        rootChange();oldchoose = onchoose;
                        Timeline timeline1 = new Timeline(new KeyFrame(Duration.millis(200), e2 -> {
                            Root.getChildren().clear();
                            anima.back(Root);
                            control.init();
                        }));
                        timeline1.setCycleCount(1);
                        timeline1.play();

                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            // 开始播放动画
            timeline.play();

        });
        controlButton.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;",myColor.chooseGroundColor));
        controlButton.setOpacity(0.3);
        controlButton.setLayoutX(248);
        controlButton.setLayoutY(7);
        controlButton.setPrefWidth(64);
        controlButton.setPrefHeight(64);


        shutdownButton.setText("");
        shutdownButton.setOnAction(e -> {
            shutdownButton.setOpacity(0.6);
            timeline.stop();
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {

                        shutdownButton.setOpacity(0.3);
                        onchoose = 3;
                        chooseUpdate(settingImageView,homeImageView,contrlImageView,shutdownImageView,chooseImageView,root2);
                        //chooseBack(settingButton,homeButton,controlButton,shutdownButton);
                        chooseBack(settingButton,homeButton,controlButton,shutdownButton);
                        oldchoose = onchoose;
                        Timeline timeline1 = new Timeline(new KeyFrame(Duration.millis(200), e2 -> {
                            Root.getChildren().clear();
                            anima.back(Root);
                            Root.getChildren().add(exit.root);
                            Main.exit.open();
                        }));
                        timeline1.setCycleCount(1);
                        timeline1.play();






                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            // 开始播放动画
            timeline.play();


        });
        shutdownButton.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;",myColor.chooseGroundColor));
        shutdownButton.setOpacity(0.3);
        shutdownButton.setLayoutX(358);
        shutdownButton.setLayoutY(7);
        shutdownButton.setPrefWidth(64);
        shutdownButton.setPrefHeight(64);


        root2.getChildren().add(backgroundImageView);
        root2.getChildren().add(chooseImageView);
        root2.getChildren().add(settingImageView);
        root2.getChildren().add(homeImageView);
        root2.getChildren().add(contrlImageView);
        root2.getChildren().add(shutdownImageView);
        root2.getChildren().add(settingButton);
        root2.getChildren().add(homeButton);
        root2.getChildren().add(controlButton);
        root2.getChildren().add(shutdownButton);
        root.getChildren().add(root2);
        chooseUpdate(settingImageView,homeImageView,contrlImageView,shutdownImageView,chooseImageView,root2);
        chooseBack(settingButton,homeButton,controlButton,shutdownButton);
    }

    public void rootChange(){
        if(onchoose>oldchoose){
            anima.translateTransition(0,0,-1000,0,200,Root);

        } else if (onchoose<oldchoose) {
            anima.translateTransition(0,0,1000,0,200,Root);

        }else if(onchoose==oldchoose){
            anima.translateTransition(0,0,0,1000,200,Root);
        }
    }

    public void chooseUpdate(ImageView setting, ImageView home, ImageView control, ImageView shutdown,ImageView choose,Pane root) {
        switch(onchoose){
            case 0:setting.setImage(new Image("res/images/choose/settingOn.png"));break;
            case 1:home.setImage(new Image("res/images/choose/homeOn.png"));break;
            case 2:control.setImage(new Image("res/images/choose/controlOn.png"));break;
            case 3:shutdown.setImage(new Image("res/images/choose/shutdownOn.png"));break;
        }
        anima.translateTransition(110*(oldchoose-1),0,110*(onchoose-1),0,140,choose);
        switch(oldchoose){
            case 0:setting.setImage(new Image("res/images/choose/settingOff.png"));break;
            case 1:home.setImage(new Image("res/images/choose/homeOff.png"));break;
            case 2:control.setImage(new Image("res/images/choose/controlOff.png"));break;
            case 3:shutdown.setImage(new Image("res/images/choose/shutdownOff.png"));break;
        }

    }
    public void chooseBack(Button setting,Button home,Button control,Button shutdown) {
        switch(oldchoose){
        case 0: setting.setOpacity(0.3);setting.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;",myColor.chooseGroundColor));


            break;

        case 1:home.setOpacity(0.3); home.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;",myColor.chooseGroundColor));

            break;
        case 2:control.setOpacity(0.3);control.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;",myColor.chooseGroundColor));

            break;
        case 3:shutdown.setOpacity(0.3);shutdown.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;",myColor.chooseGroundColor));

            break;
    }
        switch(onchoose){
            case 0:setting.setOpacity(0);

            break;

            case 1:home.setOpacity(0);

            break;
            case 2:control.setOpacity(0);

            break;
            case 3:shutdown.setOpacity(0);

            break;
        }


    }
}
