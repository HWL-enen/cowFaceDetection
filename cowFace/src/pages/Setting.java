import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.ini4j.Ini;

import java.io.File;
import java.io.IOException;

public class Setting {
    public static void init(){
        Pane Root = Home.Root;

        Image nameImage = new Image("res/images/setting/name.png");
        ImageView nameImageView = new ImageView(nameImage);
        nameImageView.setLayoutX(512);
        nameImageView.setLayoutY(655);
        nameImageView.setFitWidth(228);
        nameImageView.setFitHeight(19);
        //backgroundImageView.setPreserveRatio(true);
        nameImageView.setSmooth(true);
        Root.getChildren().add(nameImageView);

        Pane yoloSetting = new Pane();
        Image yoloBlockImage = new Image("res/images/setting/yoloSettingBlock.png");
        ImageView yoloBlockImageView = new ImageView(yoloBlockImage);
        Image openYoloBlockImage = new Image("res/images/setting/openYoloSettingBlock.png");
        ImageView openYoloBlockImageView = new ImageView(openYoloBlockImage);
        Image yoloTitleImage = new Image("res/images/setting/yoloTitle.png");
        ImageView yoloTitleImageView = new ImageView(yoloTitleImage);
        Image downImage = new Image("res/images/setting/down.png");
        ImageView downImageView = new ImageView(downImage);
        Image yoloBlockControlImage = new Image("res/images/setting/yoloBlock.png");
        ImageView yoloBlockControlImageView = new ImageView(yoloBlockControlImage);
        Image upImage = new Image("res/images/setting/up.png");
        ImageView upImageView = new ImageView(upImage);
        Image yoloSaveButton_EImage = new Image("res/images/setting/yoloSaveButton_EImage.png");
        ImageView yoloSaveButton_EImageView = new ImageView(yoloSaveButton_EImage);
        Button yoloDown = new Button();
        Button yoloUp = new Button();
        TextField sureTextField = new TextField(String.valueOf(yoloClient.sure));
        TextField faultToleranceTextField = new TextField(String.valueOf(yoloClient.faultTolerance));
        TextField modelUrlTextField = new TextField(yoloClient.modelUrl);
        TextField fpsTextField = new TextField(String.valueOf(yoloClient.maxFps));
        Button yoloSaveButton = new Button("");




        Pane mqttSetting = new Pane();
        Image mqttBlockImage = new Image("res/images/setting/yoloSettingBlock.png");
        ImageView mqttBlockImageView = new ImageView(mqttBlockImage);
        Image openMqttBlockImage = new Image("res/images/setting/openYoloSettingBlock.png");
        ImageView openMqttBlockImageView = new ImageView(openMqttBlockImage);
        Image mqttTitleImage = new Image("res/images/setting/mqttTitle.png");
        ImageView mqttTitleImageView = new ImageView(mqttTitleImage);
        ImageView mqttDownImageView = new ImageView(downImage);
        Image mqttBlockControlImage = new Image("res/images/setting/mqttBlock.png");
        ImageView mqttBlockControlImageView = new ImageView(mqttBlockControlImage);
        Image mqttSaveButton_EImage = new Image("res/images/setting/yoloSaveButton_EImage.png");
        ImageView mqttSaveButton_EImageView = new ImageView(mqttSaveButton_EImage);
        ImageView mqttUpImageView = new ImageView(upImage);
        Button mqttDown = new Button();
        Button mqttUp = new Button();
        TextField qosTextField = new TextField(String.valueOf(mqttClient.Qos));
        TextField cycleTimeTextField = new TextField(String.valueOf(mqttClient.cycleTime));
        TextField mqttUrlTextField = new TextField(mqttClient.host+":"+mqttClient.port);
        Button mqttSaveButton = new Button("");

        Pane rtmpSetting = new Pane();
        Image rtmpBlockImage = new Image("res/images/setting/yoloSettingBlock.png");
        ImageView rtmpBlockImageView = new ImageView(rtmpBlockImage);
        Image openRtmpBlockImage = new Image("res/images/setting/openYoloSettingBlock.png");
        ImageView openRtmpBlockImageView = new ImageView(openRtmpBlockImage);
        Image rtmpTitleImage = new Image("res/images/setting/rtmpTitle.png");
        ImageView rtmpTitleImageView = new ImageView(rtmpTitleImage);
        ImageView rtmpDownImageView = new ImageView(downImage);
        Image rtmpBlockControlImage = new Image("res/images/setting/rtmpBlock.png");
        ImageView rtmpBlockControlImageView = new ImageView(rtmpBlockControlImage);
        Image ntpButtonImage = new Image("res/images/setting/ntp.png");
        ImageView ntpButtonImageView = new ImageView(ntpButtonImage);
        Image rtmpSaveButton_EImage = new Image("res/images/setting/yoloSaveButton_EImage.png");
        ImageView rtmpSaveButton_EImageView = new ImageView(rtmpSaveButton_EImage);
        ImageView rtmpUpImageView = new ImageView(upImage);
        Button rtmpDown = new Button();
        Button rtmpUp = new Button();
        TextField rtmpUrlTextField = new TextField(rtmpClient.host+":"+rtmpClient.port);
        TextField bitsTextField = new TextField(String.valueOf(rtmpClient.bits));
        TextField bandwidthTextField = new TextField(String.valueOf(rtmpClient.bandwidth));
        Button rtmpSaveButton = new Button("");
        Button ntpUpdate = new Button("");

        yoloSetting.setLayoutX(24-200);
        yoloSetting.setLayoutY(129);
        yoloSetting.setPrefWidth(1239);
        yoloSetting.setPrefHeight(64);

        yoloBlockImageView.setLayoutX(0);
        yoloBlockImageView.setLayoutY(0);
        yoloBlockImageView.setFitWidth(1239);
        yoloBlockImageView.setFitHeight(64);
        //backgroundImageView.setPreserveRatio(true);
        yoloBlockImageView.setSmooth(true);

        openYoloBlockImageView.setLayoutX(0);
        openYoloBlockImageView.setLayoutY(0);
        openYoloBlockImageView.setFitWidth(1239);
        openYoloBlockImageView.setFitHeight(292);
        //backgroundImageView.setPreserveRatio(true);
        openYoloBlockImageView.setSmooth(true);

        yoloTitleImageView.setLayoutX(28);
        yoloTitleImageView.setLayoutY(16);
        yoloTitleImageView.setFitWidth(149);
        yoloTitleImageView.setFitHeight(19);
        //backgroundImageView.setPreserveRatio(true);
        yoloTitleImageView.setSmooth(true);

        downImageView.setLayoutX(1191);
        downImageView.setLayoutY(15);
        downImageView.setFitWidth(32);
        downImageView.setFitHeight(32);
        //backgroundImageView.setPreserveRatio(true);
        downImageView.setSmooth(true);

        upImageView.setLayoutX(1223-32);
        upImageView.setLayoutY(292-32);
        upImageView.setFitWidth(32);
        upImageView.setFitHeight(32);
        //backgroundImageView.setPreserveRatio(true);
        upImageView.setSmooth(true);

        yoloBlockControlImageView.setLayoutX(39);
        yoloBlockControlImageView.setLayoutY(45);
        yoloBlockControlImageView.setFitWidth(1184);
        yoloBlockControlImageView.setFitHeight(215);
        //backgroundImageView.setPreserveRatio(true);
        yoloBlockControlImageView.setSmooth(true);

        mqttSetting.setLayoutX(224);
        mqttSetting.setLayoutY(232);
        mqttSetting.setPrefWidth(1239);
        mqttSetting.setPrefHeight(64);

        mqttBlockImageView.setLayoutX(0);
        mqttBlockImageView.setLayoutY(0);
        mqttBlockImageView.setFitWidth(1239);
        mqttBlockImageView.setFitHeight(64);
        //backgroundImageView.setPreserveRatio(true);
        mqttBlockImageView.setSmooth(true);

        openMqttBlockImageView.setLayoutX(0);
        openMqttBlockImageView.setLayoutY(0);
        openMqttBlockImageView.setFitWidth(1239);
        openMqttBlockImageView.setFitHeight(292);
        //backgroundImageView.setPreserveRatio(true);
        openMqttBlockImageView.setSmooth(true);

        mqttTitleImageView.setLayoutX(28);
        mqttTitleImageView.setLayoutY(16);
        mqttTitleImageView.setFitWidth(149);
        mqttTitleImageView.setFitHeight(19);
        //backgroundImageView.setPreserveRatio(true);
        mqttTitleImageView.setSmooth(true);

        mqttDownImageView.setLayoutX(1191);
        mqttDownImageView.setLayoutY(15);
        mqttDownImageView.setFitWidth(32);
        mqttDownImageView.setFitHeight(32);
        //backgroundImageView.setPreserveRatio(true);
        mqttDownImageView.setSmooth(true);

        mqttUpImageView.setLayoutX(1223-32);
        mqttUpImageView.setLayoutY(292-32);
        mqttUpImageView.setFitWidth(32);
        mqttUpImageView.setFitHeight(32);
        //backgroundImageView.setPreserveRatio(true);
        mqttUpImageView.setSmooth(true);

        mqttBlockControlImageView.setLayoutX(37);
        mqttBlockControlImageView.setLayoutY(69);
        mqttBlockControlImageView.setFitWidth(1170);
        mqttBlockControlImageView.setFitHeight(174);
        //backgroundImageView.setPreserveRatio(true);
        mqttBlockControlImageView.setSmooth(true);



        rtmpSetting.setLayoutX(24-200);
        rtmpSetting.setLayoutY(335);
        rtmpSetting.setPrefWidth(1239);
        rtmpSetting.setPrefHeight(64);


        rtmpBlockImageView.setLayoutX(0);
        rtmpBlockImageView.setLayoutY(0);
        rtmpBlockImageView.setFitWidth(1239);
        rtmpBlockImageView.setFitHeight(64);
        //backgroundImageView.setPreserveRatio(true);
        rtmpBlockImageView.setSmooth(true);

        openRtmpBlockImageView.setLayoutX(0);
        openRtmpBlockImageView.setLayoutY(0);
        openRtmpBlockImageView.setFitWidth(1239);
        openRtmpBlockImageView.setFitHeight(292);
        //backgroundImageView.setPreserveRatio(true);
        openRtmpBlockImageView.setSmooth(true);

        rtmpTitleImageView.setLayoutX(28);
        rtmpTitleImageView.setLayoutY(16);
        rtmpTitleImageView.setFitWidth(149);
        rtmpTitleImageView.setFitHeight(19);
        //backgroundImageView.setPreserveRatio(true);
        rtmpTitleImageView.setSmooth(true);

        rtmpDownImageView.setLayoutX(1191);
        rtmpDownImageView.setLayoutY(15);
        rtmpDownImageView.setFitWidth(32);
        rtmpDownImageView.setFitHeight(32);
        //backgroundImageView.setPreserveRatio(true);
        rtmpDownImageView.setSmooth(true);

        rtmpUpImageView.setLayoutX(1223-32);
        rtmpUpImageView.setLayoutY(292-32);
        rtmpUpImageView.setFitWidth(32);
        rtmpUpImageView.setFitHeight(32);
        //backgroundImageView.setPreserveRatio(true);
        rtmpUpImageView.setSmooth(true);

        rtmpBlockControlImageView.setLayoutX(48);
        rtmpBlockControlImageView.setLayoutY(72);
        rtmpBlockControlImageView.setFitWidth(1159);
        rtmpBlockControlImageView.setFitHeight(154);
        //backgroundImageView.setPreserveRatio(true);
        rtmpBlockControlImageView.setSmooth(true);



        sureTextField.setPrefWidth(159);
        sureTextField.setPrefHeight(54);
        sureTextField.setLayoutX(145);
        sureTextField.setLayoutY(68);
        sureTextField.setStyle("-fx-background-color: transparent;");
        sureTextField.setFont(myFont.Outfit_Regular);
        String str = String.format("-fx-text-fill: %s;-fx-highlight-text-fill: %s;", myColor.textidColor,myColor.invertColor(myColor.textidColor));
        sureTextField.setStyle(sureTextField.getStyle() + str);

        faultToleranceTextField.setPrefWidth(159);
        faultToleranceTextField.setPrefHeight(54);
        faultToleranceTextField.setLayoutX(145);
        faultToleranceTextField.setLayoutY(157);
        faultToleranceTextField.setStyle("-fx-background-color: transparent;");
        faultToleranceTextField.setFont(myFont.Outfit_Regular);
        str = String.format("-fx-text-fill: %s;-fx-highlight-text-fill: %s;", myColor.textidColor,myColor.invertColor(myColor.textidColor));
        faultToleranceTextField.setStyle(sureTextField.getStyle() + str);

        modelUrlTextField.setPrefWidth(630);
        modelUrlTextField.setPrefHeight(54);
        modelUrlTextField.setLayoutX(547);
        modelUrlTextField.setLayoutY(68);
        modelUrlTextField.setStyle("-fx-background-color: transparent;");
        modelUrlTextField.setFont(myFont.Outfit_Regular);
        str = String.format("-fx-text-fill: %s;-fx-highlight-text-fill: %s;", myColor.textidColor,myColor.invertColor(myColor.textidColor));
        modelUrlTextField.setStyle(modelUrlTextField.getStyle() + str);

        fpsTextField.setPrefWidth(141);
        fpsTextField.setPrefHeight(54);
        fpsTextField.setLayoutX(551);
        fpsTextField.setLayoutY(157);
        fpsTextField.setStyle("-fx-background-color: transparent;");
        fpsTextField.setFont(myFont.Outfit_Regular);
        str = String.format("-fx-text-fill: %s;-fx-highlight-text-fill: %s;", myColor.textidColor,myColor.invertColor(myColor.textidColor));
        fpsTextField.setStyle(fpsTextField.getStyle() + str);

        qosTextField.setPrefWidth(159);
        qosTextField.setPrefHeight(54);
        qosTextField.setLayoutX(143);
        qosTextField.setLayoutY(69);
        qosTextField.setStyle("-fx-background-color: transparent;");
        qosTextField.setFont(myFont.Outfit_Regular);
        str = String.format("-fx-text-fill: %s;-fx-highlight-text-fill: %s;", myColor.textidColor,myColor.invertColor(myColor.textidColor));
        qosTextField.setStyle(qosTextField.getStyle() + str);

        mqttUrlTextField.setPrefWidth(771);
        mqttUrlTextField.setPrefHeight(54);
        mqttUrlTextField.setLayoutX(436);
        mqttUrlTextField.setLayoutY(69);
        mqttUrlTextField.setStyle("-fx-background-color: transparent;");
        mqttUrlTextField.setFont(myFont.Outfit_Regular);
        str = String.format("-fx-text-fill: %s;-fx-highlight-text-fill: %s;", myColor.textidColor,myColor.invertColor(myColor.textidColor));
        mqttUrlTextField.setStyle(mqttUrlTextField.getStyle() + str);

        cycleTimeTextField.setPrefWidth(159);
        cycleTimeTextField.setPrefHeight(54);
        cycleTimeTextField.setLayoutX(143);
        cycleTimeTextField.setLayoutY(147);
        cycleTimeTextField.setStyle("-fx-background-color: transparent;");
        cycleTimeTextField.setFont(myFont.Outfit_Regular);
        str = String.format("-fx-text-fill: %s;-fx-highlight-text-fill: %s;", myColor.textidColor,myColor.invertColor(myColor.textidColor));
        cycleTimeTextField.setStyle(cycleTimeTextField.getStyle() + str);

        rtmpUrlTextField.setPrefWidth(771);
        rtmpUrlTextField.setPrefHeight(54);
        rtmpUrlTextField.setLayoutX(436);
        rtmpUrlTextField.setLayoutY(72);
        rtmpUrlTextField.setStyle("-fx-background-color: transparent;");
        rtmpUrlTextField.setFont(myFont.Outfit_Regular);
        str = String.format("-fx-text-fill: %s;-fx-highlight-text-fill: %s;", myColor.textidColor,myColor.invertColor(myColor.textidColor));
        rtmpUrlTextField.setStyle(rtmpUrlTextField.getStyle() + str);

        bitsTextField.setPrefWidth(189);
        bitsTextField.setPrefHeight(54);
        bitsTextField.setLayoutX(102);
        bitsTextField.setLayoutY(160);
        bitsTextField.setStyle("-fx-background-color: transparent;");
        bitsTextField.setFont(myFont.Outfit_Regular);
        str = String.format("-fx-text-fill: %s;-fx-highlight-text-fill: %s;", myColor.textidColor,myColor.invertColor(myColor.textidColor));
        bitsTextField.setStyle(bitsTextField.getStyle() + str);

        bandwidthTextField.setPrefWidth(189);
        bandwidthTextField.setPrefHeight(54);
        bandwidthTextField.setLayoutX(384);
        bandwidthTextField.setLayoutY(160);
        bandwidthTextField.setStyle("-fx-background-color: transparent;");
        bandwidthTextField.setFont(myFont.Outfit_Regular);
        str = String.format("-fx-text-fill: %s;-fx-highlight-text-fill: %s;", myColor.textidColor,myColor.invertColor(myColor.textidColor));
        bandwidthTextField.setStyle(bandwidthTextField.getStyle() + str);


        yoloSaveButton_EImageView.setLayoutX(1009);
        yoloSaveButton_EImageView.setLayoutY(204);
        yoloSaveButton_EImageView.setFitWidth(214);
        yoloSaveButton_EImageView.setFitHeight(57);
        //backgroundImageView.setPreserveRatio(true);
        yoloSaveButton_EImageView.setSmooth(true);

        Button dirUrlButton = new Button();
        dirUrlButton.setText("");
        dirUrlButton.setOnAction(e -> {
            dirUrlButton.setOpacity(0.6);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {
                        dirUrlButton.setOpacity(0.1);
                        FileChooser fileChooser = new FileChooser();
                        fileChooser.setTitle("选择onnx模型文件");
                        fileChooser.getExtensionFilters().addAll(
                                new FileChooser.ExtensionFilter("模型文件", "*.onnx"),
                                new FileChooser.ExtensionFilter("所有文件", "*.*")
                        );
                        File selectedFile = fileChooser.showOpenDialog(Main.MainStage);
                        if (selectedFile != null) {
                            modelUrlTextField.setText(selectedFile.getAbsolutePath());
                            System.out.println("你选择的文件是: " + selectedFile.getAbsolutePath());
                        } else {
                            System.out.println("未选择任何文件。");
                        }
                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            // 开始播放动画
            timeline.play();

        });
        dirUrlButton.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;", myColor.OnchooseGroundColor1));
        dirUrlButton.setOpacity(0.1);
        dirUrlButton.setPrefWidth(52);
        dirUrlButton.setPrefHeight(28);
        dirUrlButton.setLayoutX(1122);
        dirUrlButton.setLayoutY(84);

        yoloSaveButton.setText("");
        yoloSaveButton.setOnAction(e -> {
            yoloSaveButton.setOpacity(0.6);
            yoloSetting.getChildren().remove(yoloSaveButton_EImageView);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {

                        yoloSaveButton.setOpacity(0.1);

                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);




            try {
                Ini ini = Main.ini;
                // 读取 INI 文件

                String strText = "";
                strText = sureTextField.getText();
                ini.put("Yolo", "sure", strText);
                yoloClient.sure = Double.valueOf(strText);
                strText = faultToleranceTextField.getText();
                ini.put("Yolo", "faultTolerance", strText);
                yoloClient.faultTolerance = Double.valueOf(strText);
                strText = modelUrlTextField.getText();
                ini.put("Yolo", "modelUrl", strText);
                yoloClient.modelUrl = strText;
                strText = fpsTextField.getText();
                ini.put("Yolo", "maxFps", strText);
                yoloClient.maxFps = Integer.valueOf(strText);




                // 写入 INI 文件
                ini.store(new File("setting.ini"));
                timeline.play();

            } catch (IOException e1) {
                e1.printStackTrace();
            }



        });
        yoloSaveButton.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;",myColor.backgroundColor));
        yoloSaveButton.setOpacity(0.1);
        yoloSaveButton.setPrefWidth(204);
        yoloSaveButton.setPrefHeight(42);
        yoloSaveButton.setLayoutX(1011);
        yoloSaveButton.setLayoutY(210);
        yoloSaveButton.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                yoloSetting.getChildren().add(yoloSetting.getChildren().indexOf(yoloSaveButton)-1,yoloSaveButton_EImageView);

            }else {
                yoloSetting.getChildren().remove(yoloSaveButton_EImageView);

            }
        });

        mqttSaveButton_EImageView.setLayoutX(998);
        mqttSaveButton_EImageView.setLayoutY(189);
        mqttSaveButton_EImageView.setFitWidth(214);
        mqttSaveButton_EImageView.setFitHeight(57);
        //backgroundImageView.setPreserveRatio(true);
        mqttSaveButton_EImageView.setSmooth(true);

        mqttSaveButton.setText("");
        mqttSaveButton.setOnAction(e -> {
            mqttSaveButton.setOpacity(0.6);
            mqttSetting.getChildren().remove(mqttSaveButton_EImageView);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {

                        mqttSaveButton.setOpacity(0.1);

                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);




            try {
                Ini ini = Main.ini;
                // 读取 INI 文件

                String strText = "";
                strText = mqttUrlTextField.getText();
                String[] newstr = strText.split(":");
                if (newstr.length == 2) {
                    ini.put("mqttServer", "host", newstr[0]);
                    ini.put("mqttServer", "port", newstr[1]);
                    mqttClient.host = newstr[0];
                    mqttClient.port = newstr[1];
                }else{

                }
                strText = qosTextField.getText();
                ini.put("mqttServer", "Qos", strText);
                mqttClient.Qos = Integer.valueOf(strText);
                strText = cycleTimeTextField.getText();
                ini.put("mqttServer", "cycleTime", strText);
                mqttClient.cycleTime = Integer.valueOf(strText);






                // 写入 INI 文件
                ini.store(new File("setting.ini"));
                timeline.play();

            } catch (IOException e1) {
                e1.printStackTrace();
            }



        });
        mqttSaveButton.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;",myColor.backgroundColor));
        mqttSaveButton.setOpacity(0.1);
        mqttSaveButton.setPrefWidth(204);
        mqttSaveButton.setPrefHeight(42);
        mqttSaveButton.setLayoutX(998);
        mqttSaveButton.setLayoutY(195);
        mqttSaveButton.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                mqttSetting.getChildren().add(mqttSetting.getChildren().indexOf(mqttSaveButton)-1,mqttSaveButton_EImageView);

            }else {
                mqttSetting.getChildren().remove(mqttSaveButton_EImageView);

            }
        });

        rtmpSaveButton_EImageView.setLayoutX(968);
        rtmpSaveButton_EImageView.setLayoutY(172);
        rtmpSaveButton_EImageView.setFitWidth(214);
        rtmpSaveButton_EImageView.setFitHeight(57);
        //backgroundImageView.setPreserveRatio(true);
        rtmpSaveButton_EImageView.setSmooth(true);

        rtmpSaveButton.setText("");
        rtmpSaveButton.setOnAction(e -> {
            rtmpSaveButton.setOpacity(0.6);
            rtmpSetting.getChildren().remove(rtmpSaveButton_EImageView);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {

                        rtmpSaveButton.setOpacity(0.1);

                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);




            try {
                Ini ini = Main.ini;
                // 读取 INI 文件

                String strText = "";
                strText = rtmpUrlTextField.getText();
                String[] newstr = strText.split(":");
                if (newstr.length == 2) {
                    ini.put("rtmpServer", "host", newstr[0]);
                    ini.put("rtmpServer", "port", newstr[1]);
                    rtmpClient.host = newstr[0];
                    rtmpClient.port = newstr[1];
                }else{

                }
                strText = bitsTextField.getText();
                ini.put("rtmpServer", "bits", strText);
                rtmpClient.bits = Integer.valueOf(strText);
                strText = bandwidthTextField.getText();
                ini.put("rtmpServer", "bandwidth", strText);
                rtmpClient.bandwidth = Integer.valueOf(strText);



                // 写入 INI 文件
                ini.store(new File("setting.ini"));
                timeline.play();

            } catch (IOException e1) {
                e1.printStackTrace();
            }



        });
        rtmpSaveButton.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;",myColor.backgroundColor));
        rtmpSaveButton.setOpacity(0.1);
        rtmpSaveButton.setPrefWidth(204);
        rtmpSaveButton.setPrefHeight(42);
        rtmpSaveButton.setLayoutX(973);
        rtmpSaveButton.setLayoutY(176);
        rtmpSaveButton.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                rtmpSetting.getChildren().add(rtmpSetting.getChildren().indexOf(rtmpSaveButton)-1,rtmpSaveButton_EImageView);

            }else {
                rtmpSetting.getChildren().remove(rtmpSaveButton_EImageView);

            }
        });

        ntpButtonImageView.setLayoutX(46);
        ntpButtonImageView.setLayoutY(65);
        ntpButtonImageView.setFitWidth(169);
        ntpButtonImageView.setFitHeight(69);
        //backgroundImageView.setPreserveRatio(true);
        ntpButtonImageView.setSmooth(true);

        ntpUpdate.setText("");
        ntpUpdate.setOnAction(e -> {
            ntpUpdate.setOpacity(0.6);
            rtmpSetting.getChildren().remove(ntpButtonImageView);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {

                        ntpUpdate.setOpacity(0.1);

                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            timeline.play();






        });
        ntpUpdate.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;",myColor.backgroundColor));
        ntpUpdate.setOpacity(0.1);
        ntpUpdate.setPrefWidth(159);
        ntpUpdate.setPrefHeight(54);
        ntpUpdate.setLayoutX(52);
        ntpUpdate.setLayoutY(72);
        ntpUpdate.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                rtmpSetting.getChildren().add(rtmpSetting.getChildren().indexOf(ntpUpdate)-1,ntpButtonImageView);

            }else {
                rtmpSetting.getChildren().remove(ntpButtonImageView);

            }
        });







        yoloDown.setText("");
        yoloDown.setOnAction(e -> {
            yoloDown.setOpacity(0.6);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {

                        yoloSetting.setPrefWidth(1239);
                        yoloSetting.setPrefHeight(292);
                        yoloDown.setOpacity(0.1);
                        yolo_open(mqttSetting,rtmpSetting);
                        yoloSetting.getChildren().remove(downImageView);
                        yoloSetting.getChildren().remove(yoloDown);

                        anima.zoom_size(1292,64,1292,292,200,yoloBlockImageView);
                        anima.translateTransition(0,0,0,100,200,yoloBlockImageView);
                        Timeline timeline1 = new Timeline(new KeyFrame(Duration.millis(200), e2 -> {
                            anima.back(openYoloBlockImageView);
                            yoloSetting.getChildren().add(yoloSetting.getChildren().indexOf(yoloBlockImageView)+1,openYoloBlockImageView);
                            yoloSetting.getChildren().remove(yoloBlockImageView);
                            yoloSetting.getChildren().add(upImageView);
                            yoloSetting.getChildren().add(yoloUp);
                            yoloSetting.getChildren().add(yoloBlockControlImageView);
                            yoloSetting.getChildren().add(sureTextField);
                            yoloSetting.getChildren().add(faultToleranceTextField);
                            yoloSetting.getChildren().add(modelUrlTextField);
                            yoloSetting.getChildren().add(fpsTextField);
                            yoloSetting.getChildren().add(yoloSaveButton);
                            yoloSetting.getChildren().add(dirUrlButton);
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
        yoloDown.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;", myColor.chooseGroundColor));
        yoloDown.setOpacity(0.3);
        yoloDown.setPrefWidth(32);
        yoloDown.setPrefHeight(32);
        yoloDown.setLayoutX(1185);
        yoloDown.setLayoutY(15);

        yoloUp.setText("");
        yoloUp.setOnAction(e -> {
            yoloUp.setOpacity(0.6);
            if (yoloSetting.getChildren().contains(yoloSaveButton_EImageView)){ yoloSetting.getChildren().remove(yoloSaveButton_EImageView); }
            yoloSetting.getChildren().remove(yoloSaveButton);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {
                        yoloSetting.setPrefWidth(1239);
                        yoloSetting.setPrefHeight(64);
                        yoloUp.setOpacity(0.1);
                        yolo_close(mqttSetting,rtmpSetting);
                        yoloSetting.getChildren().remove(yoloUp);
                        yoloSetting.getChildren().remove(upImageView);
                        yoloSetting.getChildren().remove(yoloBlockControlImageView);
                        yoloSetting.getChildren().remove(sureTextField);
                        yoloSetting.getChildren().remove(faultToleranceTextField);
                        yoloSetting.getChildren().remove(modelUrlTextField);
                        yoloSetting.getChildren().remove(fpsTextField);
                        yoloSetting.getChildren().remove(dirUrlButton);
                        anima.zoom_size(1239,292,1239,64,200,openYoloBlockImageView);
                        anima.translateTransition(0,0,0,-100,200,openYoloBlockImageView);
                        Timeline timeline1 = new Timeline(new KeyFrame(Duration.millis(200), e2 -> {
                            anima.back(yoloBlockImageView);
                            yoloSetting.getChildren().add(yoloSetting.getChildren().indexOf(openYoloBlockImageView)+1,yoloBlockImageView);
                            yoloSetting.getChildren().remove(openYoloBlockImageView);
                            yoloSetting.getChildren().add(downImageView);
                            yoloSetting.getChildren().add(yoloDown);


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
        yoloUp.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;", myColor.chooseGroundColor));
        yoloUp.setOpacity(0.3);
        yoloUp.setPrefWidth(32);
        yoloUp.setPrefHeight(32);
        yoloUp.setLayoutX(1223-32);
        yoloUp.setLayoutY(292-32);

        yoloSetting.getChildren().add(yoloBlockImageView);
        yoloSetting.getChildren().add(yoloTitleImageView);
        yoloSetting.getChildren().add(downImageView);
        yoloSetting.getChildren().add(yoloDown);



        mqttDown.setText("");
        mqttDown.setOnAction(e -> {
            mqttDown.setOpacity(0.6);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {

                        mqttSetting.setPrefWidth(1239);
                        mqttSetting.setPrefHeight(292);
                        mqttDown.setOpacity(0.1);
                        mqtt_open(yoloSetting,rtmpSetting);
                        mqttSetting.getChildren().remove(mqttDownImageView);
                        mqttSetting.getChildren().remove(mqttDown);

                        anima.zoom_size(1292,64,1292,292,200,mqttBlockImageView);
                        anima.translateTransition(0,0,0,100,200,mqttBlockImageView);
                        Timeline timeline1 = new Timeline(new KeyFrame(Duration.millis(200), e2 -> {
                            anima.back(openMqttBlockImageView);
                            mqttSetting.getChildren().add(mqttSetting.getChildren().indexOf(mqttBlockImageView)+1,openMqttBlockImageView);
                            mqttSetting.getChildren().remove(mqttBlockImageView);
                            mqttSetting.getChildren().add(mqttUpImageView);
                            mqttSetting.getChildren().add(mqttUp);
                            mqttSetting.getChildren().add(mqttBlockControlImageView);
                            mqttSetting.getChildren().add(qosTextField);
                            mqttSetting.getChildren().add(mqttUrlTextField);
                            mqttSetting.getChildren().add(cycleTimeTextField);
                            mqttSetting.getChildren().add(mqttSaveButton);


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
        mqttDown.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;", myColor.chooseGroundColor));
        mqttDown.setOpacity(0.3);
        mqttDown.setPrefWidth(32);
        mqttDown.setPrefHeight(32);
        mqttDown.setLayoutX(1185);
        mqttDown.setLayoutY(15);

        mqttUp.setText("");
        mqttUp.setOnAction(e -> {
            mqttUp.setOpacity(0.6);
            if (mqttSetting.getChildren().contains(mqttSaveButton_EImageView)){ mqttSetting.getChildren().remove(mqttSaveButton_EImageView); }
            mqttSetting.getChildren().remove(mqttSaveButton);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {
                        mqttSetting.setPrefWidth(1239);
                        mqttSetting.setPrefHeight(64);
                        mqttUp.setOpacity(0.1);
                        mqtt_close(yoloSetting,rtmpSetting);
                        mqttSetting.getChildren().remove(mqttUp);
                        mqttSetting.getChildren().remove(mqttUpImageView);
                        mqttSetting.getChildren().remove(mqttBlockControlImageView);
                        mqttSetting.getChildren().remove(qosTextField);
                        mqttSetting.getChildren().remove(mqttUrlTextField);
                        mqttSetting.getChildren().remove(cycleTimeTextField);

                        anima.zoom_size(1239,292,1239,64,200,openMqttBlockImageView);
                        anima.translateTransition(0,0,0,-100,200,openMqttBlockImageView);
                        Timeline timeline1 = new Timeline(new KeyFrame(Duration.millis(200), e2 -> {
                            anima.back(mqttBlockImageView);
                            mqttSetting.getChildren().add(mqttSetting.getChildren().indexOf(openMqttBlockImageView)+1,mqttBlockImageView);
                            mqttSetting.getChildren().remove(openMqttBlockImageView);
                            mqttSetting.getChildren().add(mqttDownImageView);
                            mqttSetting.getChildren().add(mqttDown);



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
        mqttUp.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;", myColor.chooseGroundColor));
        mqttUp.setOpacity(0.3);
        mqttUp.setPrefWidth(32);
        mqttUp.setPrefHeight(32);
        mqttUp.setLayoutX(1223-32);
        mqttUp.setLayoutY(292-32);

        mqttSetting.getChildren().add(mqttBlockImageView);
        mqttSetting.getChildren().add(mqttTitleImageView);
        mqttSetting.getChildren().add(mqttDownImageView);
        mqttSetting.getChildren().add(mqttDown);

        rtmpDown.setText("");
        rtmpDown.setOnAction(e -> {
            rtmpDown.setOpacity(0.6);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {

                        rtmpSetting.setPrefWidth(1239);
                        rtmpSetting.setPrefHeight(292);
                        rtmpDown.setOpacity(0.1);
                        rtmp_open(yoloSetting,mqttSetting);
                        rtmpSetting.getChildren().remove(rtmpDownImageView);
                        rtmpSetting.getChildren().remove(rtmpDown);

                        anima.zoom_size(1292,64,1292,292,200,rtmpBlockImageView);
                        anima.translateTransition(0,0,0,100,200,rtmpBlockImageView);
                        Timeline timeline1 = new Timeline(new KeyFrame(Duration.millis(200), e2 -> {
                            anima.back(openRtmpBlockImageView);
                            rtmpSetting.getChildren().add(rtmpSetting.getChildren().indexOf(rtmpBlockImageView)+1,openRtmpBlockImageView);
                            rtmpSetting.getChildren().remove(rtmpBlockImageView);
                            rtmpSetting.getChildren().add(rtmpUpImageView);
                            rtmpSetting.getChildren().add(rtmpUp);
                            rtmpSetting.getChildren().add(rtmpBlockControlImageView);
                            rtmpSetting.getChildren().add(rtmpUrlTextField);
                            rtmpSetting.getChildren().add(bitsTextField);
                            rtmpSetting.getChildren().add(bandwidthTextField);
                            rtmpSetting.getChildren().add(rtmpSaveButton);
                            rtmpSetting.getChildren().add(ntpUpdate);




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
        rtmpDown.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;", myColor.chooseGroundColor));
        rtmpDown.setOpacity(0.3);
        rtmpDown.setPrefWidth(32);
        rtmpDown.setPrefHeight(32);
        rtmpDown.setLayoutX(1185);
        rtmpDown.setLayoutY(15);

        rtmpUp.setText("");
        rtmpUp.setOnAction(e -> {
            rtmpUp.setOpacity(0.6);
            if (rtmpSetting.getChildren().contains(rtmpSaveButton_EImageView)){ rtmpSetting.getChildren().remove(rtmpSaveButton_EImageView); }
            rtmpSetting.getChildren().remove(rtmpSaveButton);
            if (rtmpSetting.getChildren().contains(ntpButtonImageView)){ rtmpSetting.getChildren().remove(ntpButtonImageView); }
            rtmpSetting.getChildren().remove(ntpUpdate);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {
                        rtmpSetting.setPrefWidth(1239);
                        rtmpSetting.setPrefHeight(64);
                        rtmpUp.setOpacity(0.1);
                        rtmp_close(yoloSetting,mqttSetting);
                        rtmpSetting.getChildren().remove(rtmpUp);
                        rtmpSetting.getChildren().remove(rtmpUpImageView);
                        rtmpSetting.getChildren().remove(rtmpBlockControlImageView);
                        rtmpSetting.getChildren().remove(rtmpUrlTextField);
                        rtmpSetting.getChildren().remove(bitsTextField);
                        rtmpSetting.getChildren().remove(bandwidthTextField);

                        anima.zoom_size(1239,292,1239,64,200,openRtmpBlockImageView);
                        anima.translateTransition(0,0,0,-100,200,openRtmpBlockImageView);
                        Timeline timeline1 = new Timeline(new KeyFrame(Duration.millis(200), e2 -> {
                            anima.back(rtmpBlockImageView);
                            rtmpSetting.getChildren().add(rtmpSetting.getChildren().indexOf(openRtmpBlockImageView)+1,rtmpBlockImageView);
                            rtmpSetting.getChildren().remove(openRtmpBlockImageView);
                            rtmpSetting.getChildren().add(rtmpDownImageView);
                            rtmpSetting.getChildren().add(rtmpDown);


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
        rtmpUp.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;", myColor.chooseGroundColor));
        rtmpUp.setOpacity(0.3);
        rtmpUp.setPrefWidth(32);
        rtmpUp.setPrefHeight(32);
        rtmpUp.setLayoutX(1223-32);
        rtmpUp.setLayoutY(292-32);

        rtmpSetting.getChildren().add(rtmpBlockImageView);
        rtmpSetting.getChildren().add(rtmpTitleImageView);
        rtmpSetting.getChildren().add(rtmpDownImageView);
        rtmpSetting.getChildren().add(rtmpDown);

        Root.getChildren().add(yoloSetting);
        Root.getChildren().add(mqttSetting);
        Root.getChildren().add(rtmpSetting);

        anima.translateTransition_G(0,0,200,0,200,20,yoloSetting);
        anima.translateTransition_G(0,0,-200,0,200,20,mqttSetting);
        anima.translateTransition_G(0,0,200,0,200,20,rtmpSetting);



    }

    private static void yolo_open(Pane mqtt, Pane rtmp){
        anima.translateTransition(rtmp.getTranslateX(), rtmp.getTranslateY(),rtmp.getTranslateX(),rtmp.getTranslateY()+228,200,rtmp);
        anima.translateTransition(mqtt.getTranslateX(),mqtt.getTranslateY(), mqtt.getTranslateX(), mqtt.getTranslateY()+228,200,mqtt);
    }

    private static void yolo_close(Pane mqtt, Pane rtmp){
        anima.translateTransition(rtmp.getTranslateX(), rtmp.getTranslateY(), rtmp.getTranslateX(), rtmp.getTranslateY()-228,200,rtmp);
        anima.translateTransition(mqtt.getTranslateX(), mqtt.getTranslateY(), mqtt.getTranslateX(), mqtt.getTranslateY()-228,200,mqtt);
    }
    private static void mqtt_open(Pane yolo, Pane rtmp){
        anima.translateTransition(rtmp.getTranslateX(), rtmp.getTranslateY(),rtmp.getTranslateX(),rtmp.getTranslateY()+228,200,rtmp);
    }

    private static void mqtt_close(Pane yolo, Pane rtmp){
        anima.translateTransition(rtmp.getTranslateX(), rtmp.getTranslateY(), rtmp.getTranslateX(), rtmp.getTranslateY()-228,200,rtmp);
    }
    private static void rtmp_open(Pane yolo, Pane mqtt){

    }

    private static void rtmp_close(Pane yolo, Pane mqtt){

    }

}
