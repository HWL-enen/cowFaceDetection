import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.ini4j.Ini;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class Login{
        public int mainWidth;
        public int mainHeight;
        public int settingWidth;
        public int settingHeight;
        private boolean isSettingOpen = false;
        private Stage settingStage;
        public Timeline timeline1;
        public Timeline timeline2;

    public Login(int mainWidth, int mainHeight, int settingWidth, int settingHeight) {
        this.mainWidth = mainWidth;
        this.mainHeight = mainHeight;
        this.settingWidth = settingWidth;
        this.settingHeight = settingHeight;
    }

    public Login(int width, int height) {
            this.mainWidth = width;
            this.mainHeight = height;
        }

        public void start(Stage primaryStage) {
            Pane root = new Pane();
            Scene scene = new Scene(root, mainWidth, mainHeight);

            block.PaneRoundedRectangle(root,20);
            block.PanesetOnMouseMove(root,primaryStage);

            primaryStage.setScene(scene);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            init(root,primaryStage);
            primaryStage.show();






        }

        private void init(Pane root,Stage primaryStage) {
            try{
                timeline1.stop();
                timeline2.stop();
            }catch (Exception e){

            }

            Image backgroundImage = new Image("res/images/login/background.png");
            ImageView backgroundImageView = new ImageView(backgroundImage);
            backgroundImageView.setLayoutX(0);
            backgroundImageView.setLayoutY(0);
            backgroundImageView.setFitWidth(1283);
            backgroundImageView.setFitHeight(726);
            //backgroundImageView.setPreserveRatio(true);
            backgroundImageView.setSmooth(true);

            Image loginImage = new Image("res/images/login/image.png");
            ImageView loginImageView = new ImageView(loginImage);
            loginImageView.setFitWidth(908);
            loginImageView.setFitHeight(720);
            loginImageView.setLayoutX(0);
            loginImageView.setLayoutY(0);
            //loginImageView.setPreserveRatio(true);
            loginImageView.setSmooth(true);

            Image cowImage = new Image("res/images/login/cow.png");
            ImageView cowImageView = new ImageView(cowImage);
            cowImageView.setFitWidth(145);
            cowImageView.setFitHeight(142);
            cowImageView.setLayoutX(85);
            cowImageView.setLayoutY(259);
            //cowImageView.setPreserveRatio(true);
            cowImageView.setSmooth(true);

            Image titleImage = new Image("res/images/login/Title.png");
            ImageView titleImageView = new ImageView(titleImage);
            titleImageView.setFitWidth(352);
            titleImageView.setFitHeight(60);
            titleImageView.setLayoutX(-150);
            titleImageView.setLayoutY(599);
            //titleImageView.setPreserveRatio(true);
            titleImageView.setSmooth(true);

            Image aLittleTitleImage = new Image("res/images/login/aLittleTitle.png");
            ImageView aLittleTitleImageView = new ImageView(aLittleTitleImage);
            aLittleTitleImageView.setFitWidth(127);
            aLittleTitleImageView.setFitHeight(15);
            aLittleTitleImageView.setLayoutX(74);
            aLittleTitleImageView.setLayoutY(662);
            //aLittleTitleImageView.setPreserveRatio(true);
            aLittleTitleImageView.setSmooth(true);

            Image softwareNameImage = new Image("res/images/login/softwareName.png");
            ImageView softwareNameImageView = new ImageView(softwareNameImage);
            softwareNameImageView.setFitWidth(327);
            softwareNameImageView.setFitHeight(35);
            softwareNameImageView.setLayoutX(926);
            softwareNameImageView.setLayoutY(76-100);
            //softwareNameImageView.setPreserveRatio(true);
            softwareNameImageView.setSmooth(true);

            Image idImage = new Image("res/images/login/id.png");
            ImageView idImageView = new ImageView(idImage);
            idImageView.setFitWidth(298);
            idImageView.setFitHeight(95);
            idImageView.setLayoutX(840);
            idImageView.setLayoutY(203);
            //idImageView.setPreserveRatio(true);
            idImageView.setSmooth(true);

            Image passwdImage = new Image("res/images/login/passwd.png");
            ImageView passwdImageView = new ImageView(passwdImage);
            passwdImageView.setFitWidth(298);
            passwdImageView.setFitHeight(95);
            passwdImageView.setLayoutX(840);
            passwdImageView.setLayoutY(313);
            //passwdImageView.setPreserveRatio(true);
            passwdImageView.setSmooth(true);

            Image loginBottonImage = new Image("res/images/login/loginBotton.png");
            ImageView loginBottonImageView = new ImageView(loginBottonImage);
            loginBottonImageView.setFitWidth(215);
            loginBottonImageView.setFitHeight(50);
            loginBottonImageView.setLayoutX(1029);
            loginBottonImageView.setLayoutY(459);
            //loginBottonImageView.setPreserveRatio(true);
            loginBottonImageView.setSmooth(true);

            Image loginBotton_EImage = new Image("res/images/login/settingSave_E.png");
            ImageView loginBotton_EImageView = new ImageView(loginBotton_EImage);
            loginBotton_EImageView.setFitWidth(215);
            loginBotton_EImageView.setFitHeight(50);
            loginBotton_EImageView.setLayoutX(1029);
            loginBotton_EImageView.setLayoutY(456);
            //loginBottonImageView.setPreserveRatio(true);
            loginBotton_EImageView.setSmooth(true);

            Image loadingImage = new Image("res/images/login/Load.png");
            ImageView loadingImageView = new ImageView(loadingImage);
            loadingImageView.setFitWidth(96);
            loadingImageView.setFitHeight(96);
            loadingImageView.setLayoutX(592);
            loadingImageView.setLayoutY(424);
            //loginBottonImageView.setPreserveRatio(true);
            loadingImageView.setSmooth(true);

            Image maskImage = new Image("res/images/login/mask.png");
            ImageView maskImageView = new ImageView(maskImage);
            maskImageView.setFitWidth(1280);
            maskImageView.setFitHeight(720);
            maskImageView.setLayoutX(0);
            maskImageView.setLayoutY(0);
            //loginBottonImageView.setPreserveRatio(true);
            loadingImageView.setSmooth(true);


            Button exitButton = new Button();
            exitButton.setText("");
            exitButton.setOnAction(e -> {
                exitButton.setOpacity(0.6);
                Timeline timeline = new Timeline(
                        new KeyFrame(Duration.millis(100), e1 -> {

                            exitButton.setOpacity(0.3);
                            System.exit(0);
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
            exitButton.setLayoutX(1237);
            exitButton.setLayoutY(9);

            // 创建一个 TextField 输入框
            TextField idTextField = new TextField("");
            idTextField.setPrefWidth(279);
            idTextField.setPrefHeight(34);
            idTextField.setLayoutX(954);
            idTextField.setLayoutY(246);
            idTextField.setStyle("-fx-background-color: transparent;");
            idTextField.setFont(myFont.Outfit_Medium);
            String str = String.format("-fx-text-fill: %s;-fx-highlight-text-fill: %s;", myColor.textidColor,myColor.invertColor(myColor.textidColor));
            idTextField.setStyle(idTextField.getStyle() + str);



            TextField passwdTextField = new TextField("");
            passwdTextField.setPrefWidth(279);
            passwdTextField.setPrefHeight(34);
            passwdTextField.setLayoutX(954);
            passwdTextField.setLayoutY(356);
            passwdTextField.setStyle("-fx-background-color: transparent;");
            passwdTextField.setFont(myFont.Outfit_Medium);
            str = String.format("-fx-text-fill: %s;-fx-highlight-text-fill: %s;", myColor.textidColor,myColor.invertColor(myColor.textidColor));
            passwdTextField.setStyle(idTextField.getStyle() + str);
            System.out.println( myColor.textidColor);

            Text Tips = new Text("欢迎使用CowFace\n");
            str = Tips.getText();
            Tips.setLayoutX(610-str.length()*4);
            Tips.setLayoutY(580);
            Tips.setStyle("-fx-background-color: transparent;");
            Tips.setFont(myFont.Outfit_Regular);
            Tips.setFill(Color.web("#FFFFFF"));

            Text littleTips = new Text("Welcome to CowFace\n");
            str = littleTips.getText();
            littleTips.setLayoutX(610-str.length()*3);
            littleTips.setLayoutY(610);
            littleTips.setStyle("-fx-background-color: transparent;");
            littleTips.setFont(myFont.Outfit_Regular);
            littleTips.setFill(Color.web("#FFFFFF"));



            Button loginButton = new Button();
            loginButton.setText("");
            loginButton.setOnAction(e -> {
                root.getChildren().remove(loginBotton_EImageView);
                root.getChildren().remove(loginButton);
                loginButton.setOpacity(0.6);
                Timeline timeline = new Timeline(
                        new KeyFrame(Duration.millis(100), e1 -> {

                            loginButton.setOpacity(0.1);
                        })
                );
                // 设置动画只执行一次
                timeline.setCycleCount(1);
                // 开始播放动画
                timeline.play();

                Main.mqttserver = new mqtt(idTextField.getText(),passwdTextField.getText());

                root.getChildren().remove(idTextField);
                root.getChildren().remove(passwdTextField);
                root.getChildren().remove(loginBottonImageView);
                root.getChildren().remove(softwareNameImageView);
                root.getChildren().remove(idImageView);
                root.getChildren().remove(passwdImageView);
                root.getChildren().remove(aLittleTitleImageView);
                root.getChildren().remove(titleImageView);
                root.getChildren().remove(exitButton);

                anima.translateTransition(0,0,186,0,300,loginImageView);
                anima.zoom_size(loginImageView.getFitWidth(),loginImageView.getFitHeight(),1280,720,300,loginImageView);
                anima.translateTransition(0,0,483,-38,300,cowImageView);
                root.getChildren().add(loadingImageView);
                root.getChildren().add(root.getChildren().indexOf(cowImageView),maskImageView);
                anima.fadeT(0.1,0.7,400,maskImageView);
                root.getChildren().add(Tips);
                root.getChildren().add(littleTips);
                anima.Rotate(0,360,1000,loadingImageView);


                Main.mqttserver.start();



                timeline2 = new Timeline(new KeyFrame(Duration.millis(10000), e1 -> {
                    root.getChildren().clear();
                    Main.loginPage.init(root,primaryStage);

                    SystemTip systemTip = new SystemTip("请求超时 服务器配置错误","登陆失败", TrayIcon.MessageType.ERROR);
                    systemTip.pushMsg();

                }));
                timeline2.setCycleCount(1);
                timeline2.play();



                timeline1 = new Timeline(
                        new KeyFrame(Duration.millis(1000), e1 -> {
                                anima.translateTransition_G(186+30,0,186+30+30,0,100,10,loginImageView);
                                anima.translateTransition_G(186+30,0,186,0,100,10,loginImageView);
                                anima.Rotate(0,360,1000,loadingImageView);
                                if (Main.mqttserver.isLogin == 1) {
                                    timeline2.stop();
                                    root.getChildren().clear();
                                    Main.home.start(primaryStage);
                                }else if (Main.mqttserver.isLogin == 2){
                                    timeline2.stop();
                                    root.getChildren().clear();
                                    Main.loginPage.init(root,primaryStage);
                                    SystemTip systemTip = new SystemTip("账号、密码错误","登陆失败", TrayIcon.MessageType.ERROR);
                                    systemTip.pushMsg();
                                }

                        })
                );
                timeline1.setCycleCount(10);
                timeline1.play();







            });
            loginButton.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;",myColor.backgroundColor));
            loginButton.setOpacity(0.1);
            loginButton.setPrefWidth(215);
            loginButton.setPrefHeight(50);
            loginButton.setLayoutX(1029);
            loginButton.setLayoutY(459);
            loginButton.hoverProperty().addListener((observable, oldValue, newValue) -> {
                if(newValue){

                    root.getChildren().add(root.getChildren().indexOf(loginButton)-1,loginBotton_EImageView);

                }else {

                    root.getChildren().remove(loginBotton_EImageView);

                }
            });

            Button settingButton = new Button();
            settingButton.setText("");
            settingButton.setOnAction(e -> {
                settingButton.setOpacity(0.6);
                Timeline timeline = new Timeline(
                        new KeyFrame(Duration.millis(100), e1 -> {

                            settingButton.setOpacity(0.1);
                        })
                );
                // 设置动画只执行一次
                timeline.setCycleCount(1);
                // 开始播放动画
                timeline.play();

                if (isSettingOpen) {
                    settingStage.setAlwaysOnTop(true);
                    settingStage.setAlwaysOnTop(false);
                }else{
                    settingPage();
                    isSettingOpen = true;
                }
            });
            settingButton.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;",myColor.backgroundColor));
            settingButton.setOpacity(0.1);
            settingButton.setPrefWidth(49);
            settingButton.setPrefHeight(49);
            settingButton.setLayoutX(1213);
            settingButton.setLayoutY(648);



            root.getChildren().add(0,backgroundImageView);
            root.getChildren().add(1,loginImageView);
            root.getChildren().add(2,cowImageView);
            root.getChildren().add(titleImageView);
            root.getChildren().add(aLittleTitleImageView);
            root.getChildren().add(exitButton);
            root.getChildren().add(softwareNameImageView);
            root.getChildren().add(1,idImageView);
            root.getChildren().add(1,passwdImageView);
            root.getChildren().add(loginBottonImageView);
            root.getChildren().add(idTextField);
            root.getChildren().add(passwdTextField);
            root.getChildren().add(loginButton);
            root.getChildren().add(settingButton);

            anima.fadeT(0,1,1000,loginImageView);
            anima.translateTransition_G(0,0,0,200,500,20,cowImageView);
            anima.translateTransition_G(0,0,200,0,600,20,titleImageView);
            anima.translateTransition_G(0,0,200,0,600,20,aLittleTitleImageView);
            anima.translateTransition_G(0,0,0,100,500,20,softwareNameImageView);
            anima.translateTransition_G(0,0,100,0,500,20,idImageView);
            anima.translateTransition_G(0,0,100,0,500,20,passwdImageView);

        }

        private void settingPage(){
            Stage stage = new Stage();
            settingStage = stage;
            Pane root = new Pane();
            Scene scene = new Scene(root, settingWidth, settingHeight);

            block.PaneRoundedRectangle(root,30);
            block.PanesetOnMouseMove(root,stage);

            stage.setScene(scene);
            scene.setFill(Color.TRANSPARENT);
            stage.initStyle(StageStyle.TRANSPARENT);
            settingPage_Init(root);
            stage.show();

        }

        private void settingPage_Init(Pane root){
            Image settingBackgroundImage = new Image("res/images/login/settingPage.png");
            ImageView settingBackgroundImageView = new ImageView(settingBackgroundImage);
            settingBackgroundImageView.setLayoutX(-30);
            settingBackgroundImageView.setLayoutY(0);
            settingBackgroundImageView.setFitWidth(609);
            settingBackgroundImageView.setFitHeight(458);
            //backgroundImageView.setPreserveRatio(true);
            settingBackgroundImageView.setSmooth(true);

            Image saveButton_EImage = new Image("res/images/login/settingSave_E.png");
            ImageView saveButton_EImageView = new ImageView(saveButton_EImage);
            saveButton_EImageView.setLayoutX(367);
            saveButton_EImageView.setLayoutY(370);
            saveButton_EImageView.setFitWidth(217);
            saveButton_EImageView.setFitHeight(57);
            //backgroundImageView.setPreserveRatio(true);
            saveButton_EImageView.setSmooth(true);


            TextField mqttTextField = new TextField(mqttClient.host+":"+mqttClient.port);
            mqttTextField.setPrefWidth(452);
            mqttTextField.setPrefHeight(54);
            mqttTextField.setLayoutX(127);
            mqttTextField.setLayoutY(89);
            mqttTextField.setStyle("-fx-background-color: transparent;");
            mqttTextField.setFont(myFont.Outfit_Regular);
            String str = String.format("-fx-text-fill: %s;-fx-highlight-text-fill: %s;", myColor.textidColor,myColor.invertColor(myColor.textidColor));
            mqttTextField.setStyle(mqttTextField.getStyle() + str);

            TextField rtmpTextField = new TextField(rtmpClient.host+":"+rtmpClient.port);
            rtmpTextField.setPrefWidth(452);
            rtmpTextField.setPrefHeight(54);
            rtmpTextField.setLayoutX(127);
            rtmpTextField.setLayoutY(185);
            rtmpTextField.setStyle("-fx-background-color: transparent;");
            rtmpTextField.setFont(myFont.Outfit_Regular);
            str = String.format("-fx-text-fill: %s;-fx-highlight-text-fill: %s;", myColor.textidColor,myColor.invertColor(myColor.textidColor));
            rtmpTextField.setStyle(mqttTextField.getStyle() + str);

            Button exitButton = new Button();
            exitButton.setText("");
            exitButton.setOnAction(e -> {
                exitButton.setOpacity(0.6);
                Timeline timeline = new Timeline(
                        new KeyFrame(Duration.millis(100), e1 -> {

                            exitButton.setOpacity(0.3);
                            isSettingOpen = false;
                            settingStage.close();
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
            exitButton.setLayoutX(547);
            exitButton.setLayoutY(15);

            Button saveButton = new Button();
            saveButton.setText("");
            saveButton.setOnAction(e -> {
                saveButton.setOpacity(0.6);
                root.getChildren().remove(saveButton_EImageView);
                Timeline timeline = new Timeline(
                        new KeyFrame(Duration.millis(100), e1 -> {

                            saveButton.setOpacity(0.1);
                            isSettingOpen = false;
                            settingStage.close();
                        })
                );
                // 设置动画只执行一次
                timeline.setCycleCount(1);




                try {
                    Ini ini = Main.ini;
                    // 读取 INI 文件

                    String strText = mqttTextField.getText();
                    String[] newstr = strText.split(":");
                    if (newstr.length == 2) {
                        ini.put("mqttServer", "host", newstr[0]);
                        mqttClient.host = newstr[0];
                        ini.put("mqttServer", "port", newstr[1]);
                        mqttClient.port = newstr[1];
                    }else{

                    }


                    strText = rtmpTextField.getText();
                    newstr = strText.split(":");
                    if (newstr.length == 2) {
                        ini.put("rtmpServer", "host", newstr[0]);
                        rtmpClient.host = newstr[0];
                        ini.put("rtmpServer", "port", newstr[1]);
                        rtmpClient.port = newstr[1];
                    }else{

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
            saveButton.setPrefWidth(207);
            saveButton.setPrefHeight(42);
            saveButton.setLayoutX(372);
            saveButton.setLayoutY(377);
            saveButton.hoverProperty().addListener((observable, oldValue, newValue) -> {
                if(newValue){
                    root.getChildren().add(1,saveButton_EImageView);

                }else {
                    root.getChildren().remove(saveButton_EImageView);

                }
            });

            root.getChildren().add(settingBackgroundImageView);
            root.getChildren().add(mqttTextField);
            root.getChildren().add(rtmpTextField);
            root.getChildren().add(exitButton);
            root.getChildren().add(2,saveButton);

            anima.translateTransition_G(0,0,30,0,100,20,settingBackgroundImageView);

        }


}
