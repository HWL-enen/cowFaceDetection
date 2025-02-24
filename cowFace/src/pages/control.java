import ai.onnxruntime.OrtException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class control {

    public static Pane root;
    public static ArrayList<roomblock> showRoomBlocks = new ArrayList<>(Main.roomblocks);
    public static Stage addStage;
    public static Stage insertStage;
    public static boolean isAddStageOpen = false,isinsertStageOpen = false;
    public static int nowIndex = 2077;
    public static Text indexText;
    public static ArrayList<indexBlock> indexBlocks = new ArrayList<>();
    public static  cameraTest cameraTest;
    public static VBox vbox;
    public static VBox indexVbox;



    public static void init(){
        root = Home.Root;
        vbox = new VBox();
        vbox.setStyle("-fx-background-color: transparent;");

        showRoom(vbox);

        // 创建一个滚动面板并将垂直布局容器添加到其中
        ScrollPane scrollPane = new ScrollPane(vbox);
        // 设置滚动面板的滚动方向为垂直
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPrefWidth(1252);
        scrollPane.setPrefHeight(563);
        scrollPane.setLayoutX(14);
        scrollPane.setLayoutY(426);
        scrollPane.setStyle(
                "-fx-background-color: transparent;"
        );
        scrollPane.getStylesheets().add("data:,* { -fx-background-color: transparent; }");



        Image searchImage = new Image("res/images/control/search.png");
        ImageView searchImageView = new ImageView(searchImage);
        Image nullImage = new Image("res/images/control/null.png");
        ImageView nullImageView = new ImageView(nullImage);
        Image insertButtonImage = new Image("res/images/control/insertButton.png");
        ImageView insertButtonImageView = new ImageView(insertButtonImage);

        TextField searchTextField = new TextField();

        Button searchButton = new Button();
        Button addButton = new Button();
        Button insertButton = new Button();



        searchImageView.setLayoutX(400);
        searchImageView.setLayoutY(615);
        searchImageView.setFitWidth(568);
        searchImageView.setFitHeight(97);
        //backgroundImageView.setPreserveRatio(true);
        searchImageView.setSmooth(true);

        nullImageView.setLayoutX(559);
        nullImageView.setLayoutY(261);
        nullImageView.setFitWidth(187);
        nullImageView.setFitHeight(175);
        //backgroundImageView.setPreserveRatio(true);
        nullImageView.setSmooth(true);

        insertButtonImageView.setLayoutX(1246);
        insertButtonImageView.setLayoutY(24);
        insertButtonImageView.setFitWidth(79);
        insertButtonImageView.setFitHeight(79);
        //backgroundImageView.setPreserveRatio(true);
        insertButtonImageView.setSmooth(true);

        searchTextField.setPrefWidth(373);
        searchTextField.setPrefHeight(51);
        searchTextField.setLayoutX(797);
        searchTextField.setLayoutY(640);
        searchTextField.setStyle("-fx-background-color: transparent;");
        searchTextField.setFont(myFont.Outfit_Regular);
        String str = String.format("-fx-text-fill: %s;-fx-highlight-text-fill: %s;", myColor.textidColor,myColor.invertColor(myColor.textidColor));
        searchTextField.setStyle(searchTextField.getStyle() + str);


        searchButton.setText("");
        searchButton.setOnAction(e -> {
            searchButton.setOpacity(0.6);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {
                        searchButton.setOpacity(0.1);
                        String str1 = searchTextField.getText();
                        showRoomBlocks.clear();
                        int sum = 0;
                        for(int i = 0; i < Main.roomblocks.size(); i++){
                            if(Main.roomblocks.get(i).roomName.contains(str1)){
                                Main.roomblocks.get(i).root.setLayoutX((sum%4)*317);
                                sum++;
                                showRoomBlocks.add(Main.roomblocks.get(i));
                            }
                        }
                        if (showRoomBlocks.isEmpty()){
                            if (!root.getChildren().contains(nullImageView)) {
                                root.getChildren().add(nullImageView);
                            }
                        }else{
                            if (root.getChildren().contains(nullImageView)) {
                                root.getChildren().remove(nullImageView);
                            }
                        }
                        vbox.getChildren().clear();
                        showRoom(vbox);
                        anima.translateTransition_G(0,0,0,-300,200,30,scrollPane);

                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            // 开始播放动画
            timeline.play();

        });
        searchButton.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;", myColor.OnchooseGroundColor));
        searchButton.setOpacity(0.1);
        searchButton.setPrefWidth(48);
        searchButton.setPrefHeight(48);
        searchButton.setLayoutX(733);
        searchButton.setLayoutY(640);

        addButton.setText("");
        addButton.setOnAction(e -> {
            addButton.setOpacity(0.6);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {
                        addButton.setOpacity(0.1);


                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            // 开始播放动画
            timeline.play();
            if (isAddStageOpen) {
                addStage.setAlwaysOnTop(true);
                addStage.setAlwaysOnTop(false);
            }else{
                addPage();
                isAddStageOpen = true;
            }

        });
        addButton.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;", myColor.OnchooseGroundColor));
        addButton.setOpacity(0.1);
        addButton.setPrefWidth(32);
        addButton.setPrefHeight(32);
        addButton.setLayoutX(1205);
        addButton.setLayoutY(648);

        insertButton.setText("");
        insertButton.setOnAction(e -> {
            insertButton.setOpacity(0.6);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {
                        insertButton.setOpacity(0.1);


                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            // 开始播放动画
            timeline.play();
            if (isinsertStageOpen) {
                insertStage.setAlwaysOnTop(true);
                insertStage.setAlwaysOnTop(false);
            }else{
                insertPage();
                isinsertStageOpen = true;
            }

        });
        insertButton.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;", myColor.chooseGroundColor));
        insertButton.setOpacity(0.1);
        insertButton.setPrefWidth(79);
        insertButton.setPrefHeight(79);
        insertButton.setLayoutX(1146);
        insertButton.setLayoutY(24);


        root.getChildren().add(scrollPane);
        root.getChildren().add(searchImageView);
        root.getChildren().add(searchTextField);
        root.getChildren().add(searchButton);
        root.getChildren().add(addButton);
        root.getChildren().add(insertButtonImageView);
        root.getChildren().add(insertButton);


        anima.translateTransition_G(0,0,300,0,200,30,searchImageView);
        anima.translateTransition_G(0,0,0,-300,200,30,scrollPane);
        anima.translateTransition_G(0,0,-100,0,200,30,insertButtonImageView);
    }

    public static void showRoom(VBox vbox){
        vbox.getChildren().clear();
        Pane line = new Pane();
        for (int i = 0; i < showRoomBlocks.toArray().length; i++) {
            if(i % 4 == 0){
                line = new Pane();
                line.setPrefWidth(1252);
                line.setPrefHeight(194);
                line.setLayoutX(0);
                line.setLayoutY((i/4) * 194);
                vbox.getChildren().add(line);
            }
            showRoomBlocks.get(i).init();
            line.getChildren().add(showRoomBlocks.get(i).root);
        }
    }
    private static void addPage(){
        Stage stage = new Stage();
        addStage = stage;
        Pane root = new Pane();
        Scene scene = new Scene(root, 1051, 633);

        block.PaneRoundedRectangle(root,30);
        block.PanesetOnMouseMove(root,stage);

        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        addPage_Init(root);
        stage.show();

    }

    private static void addPage_Init(Pane root){
        Image addBackgroundImage = new Image("res/images/control/add/background.png");
        ImageView addBackgroundImageView = new ImageView(addBackgroundImage);
        addBackgroundImageView.setLayoutX(-30);
        addBackgroundImageView.setLayoutY(0);
        addBackgroundImageView.setFitWidth(1059);
        addBackgroundImageView.setFitHeight(641);
        //backgroundImageView.setPreserveRatio(true);
        addBackgroundImageView.setSmooth(true);

        Image saveButton_EImage = new Image("res/images/control/add/onAdd.png");
        ImageView saveButton_EImageView = new ImageView(saveButton_EImage);
        saveButton_EImageView.setLayoutX(759);
        saveButton_EImageView.setLayoutY(530);
        saveButton_EImageView.setFitWidth(266);
        saveButton_EImageView.setFitHeight(74);
        //backgroundImageView.setPreserveRatio(true);
        saveButton_EImageView.setSmooth(true);

        Image refreshButton_EImage = new Image("res/images/control/add/onRefresh.png");
        ImageView refreshButton_EImageView = new ImageView(refreshButton_EImage);
        refreshButton_EImageView.setLayoutX(263);
        refreshButton_EImageView.setLayoutY(253);
        refreshButton_EImageView.setFitWidth(103);
        refreshButton_EImageView.setFitHeight(76);
        //backgroundImageView.setPreserveRatio(true);
        refreshButton_EImageView.setSmooth(true);

        ImageView cameraTestView = new ImageView();
        cameraTestView.setLayoutX(622);
        cameraTestView.setLayoutY(96);
        cameraTestView.setFitWidth(413);
        cameraTestView.setFitHeight(363);
        //backgroundImageView.setPreserveRatio(true);
        cameraTestView.setSmooth(true);
        cameraTest = new cameraTest(cameraTestView,nowIndex);


        TextField nameTextField = new TextField();
        nameTextField.setPrefWidth(450);
        nameTextField.setPrefHeight(76);
        nameTextField.setLayoutX(130);
        nameTextField.setLayoutY(126);
        nameTextField.setStyle("-fx-background-color: transparent;");
        nameTextField.setFont(myFont.Outfit_Regular);
        String str = String.format("-fx-text-fill: %s;-fx-highlight-text-fill: %s;", myColor.textidColor,myColor.invertColor(myColor.textidColor));
        nameTextField.setStyle(nameTextField.getStyle() + str);



        Button exitButton = new Button();
        exitButton.setText("");
        exitButton.setOnAction(e -> {
            exitButton.setOpacity(0.6);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {

                        exitButton.setOpacity(0.3);
                        isAddStageOpen = false;
                        cameraTest.stop();
                        addStage.close();
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
        exitButton.setLayoutX(969);
        exitButton.setLayoutY(21);

        indexVbox = new VBox();
        indexVbox.setStyle("-fx-background-color: transparent;");


        indexText = new Text("");
        indexText.setLayoutX(175);
        indexText.setLayoutY(295);
        indexText.setStyle("-fx-background-color: transparent;");
        indexText.setFill(Color.web(myColor.OnchooseGroundColor));
        indexText.setFont(myFont.Outfit_Bold_32);


        // 创建一个滚动面板并将垂直布局容器添加到其中
        ScrollPane scrollPane = new ScrollPane(indexVbox);
        // 设置滚动面板的滚动方向为垂直
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPrefWidth(140);
        scrollPane.setPrefHeight(281);
        scrollPane.setLayoutX(125);
        scrollPane.setLayoutY(327);
        scrollPane.setStyle(
                "-fx-background-color: transparent;"
        );
        scrollPane.getStylesheets().add("data:,* { -fx-background-color: transparent; }");

        cameraIndexGet cameraIndexGet1 = new cameraIndexGet();
        Thread thread1 = new Thread(cameraIndexGet1);
        thread1.start();

        Button refreshButton = new Button();
        refreshButton.setText("");
        refreshButton.setOnAction(e -> {
            showIndexBlock(indexVbox);
            control.indexBlocks.clear();
            refreshButton.setOpacity(0.6);
            root.getChildren().remove(refreshButton_EImageView);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {
                        refreshButton.setOpacity(0.1);
                        cameraIndexGet cameraIndexGet = new cameraIndexGet();
                        Thread thread = new Thread(cameraIndexGet);
                        thread.start();


                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            timeline.play();



            System.out.println(Main.cameraIndex.toString());




        });
        refreshButton.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;",myColor.backgroundColor));
        refreshButton.setOpacity(0.1);
        refreshButton.setPrefWidth(93);
        refreshButton.setPrefHeight(61);
        refreshButton.setLayoutX(268);
        refreshButton.setLayoutY(258);
        refreshButton.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                root.getChildren().add(root.getChildren().indexOf(refreshButton)-1,refreshButton_EImageView);

            }else {
                root.getChildren().remove(refreshButton_EImageView);

            }
        });

        Button testStartButton = new Button();
        testStartButton.setText("");
        testStartButton.setOnAction(e -> {
            testStartButton.setOpacity(0.6);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {

                        testStartButton.setOpacity(0.3);
                        if(nowIndex!=2077) {
                            cameraTest.startCameraCapture();
                        }
                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            // 开始播放动画
            timeline.play();

        });
        testStartButton.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;",myColor.backgroundColor));
        testStartButton.setOpacity(0.1);
        testStartButton.setPrefWidth(106);
        testStartButton.setPrefHeight(39);
        testStartButton.setLayoutX(930);
        testStartButton.setLayoutY(473);

        Button testStopButton = new Button();
        testStopButton.setText("");
        testStopButton.setOnAction(e -> {
            testStopButton.setOpacity(0.6);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {

                        testStopButton.setOpacity(0.3);
                        cameraTest.stop();
                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            // 开始播放动画
            timeline.play();

        });
        testStopButton.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;",myColor.backgroundColor));
        testStopButton.setOpacity(0.1);
        testStopButton.setPrefWidth(106);
        testStopButton.setPrefHeight(39);
        testStopButton.setLayoutX(807);
        testStopButton.setLayoutY(473);


        Button saveButton = new Button();
        saveButton.setText("");
        saveButton.setOnAction(e -> {
            saveButton.setOpacity(0.6);
            root.getChildren().remove(saveButton_EImageView);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {

                        saveButton.setOpacity(0.1);
                        isAddStageOpen = false;
                        cameraTest.stop();
                        addStage.close();
                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            timeline.play();




            try {
                Ini ini = Main.ini;
                // 读取 INI 文件
                boolean qwe;
                if (Main.roomblocks.toArray().length/2==0){
                    qwe = true;
                }else{
                    qwe = false;
                }
                roomblock roomblock1 = new roomblock(((Main.roomblocks.toArray().length)%4)*317,0,qwe, Main.roomblocks.toArray().length,nameTextField.getText());
                roomblock1.csv.createCSVFile();
                roomblock1.com = nowIndex;
                roomblock1.avg = 0;
                roomblock1.isDetection = true;
                roomblock1.isBround = true;
                roomblock1.isDiscover = false;
                roomblock1.isRtmpOpen = false;
                LocalDateTime currentDateTime = LocalDateTime.now();
                // 定义日期时间格式
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                // 格式化日期时间
                roomblock1.numberHead = currentDateTime.format(formatter);
                roomblock1.numberTail = String.format("%03d", Main.roomblocks.toArray().length+1);
                Main.roomblocks.add(roomblock1);
                showRoomBlocks.add(roomblock1);


                ini.put(String.format("roomblock%d", Main.roomblocks.toArray().length-1), "roomName",roomblock1.roomName);
                ini.put(String.format("roomblock%d", Main.roomblocks.toArray().length-1), "numberHead",roomblock1.numberHead);
                ini.put(String.format("roomblock%d", Main.roomblocks.toArray().length-1), "numberTail",roomblock1.numberTail);
                ini.put(String.format("roomblock%d", Main.roomblocks.toArray().length-1), "com",String.valueOf(roomblock1.com));
                ini.put(String.format("roomblock%d", Main.roomblocks.toArray().length-1), "avg",String.valueOf(roomblock1.avg));
                ini.put(String.format("roomblock%d", Main.roomblocks.toArray().length-1), "isDetection",String.valueOf(roomblock1.isDetection));
                ini.put(String.format("roomblock%d", Main.roomblocks.toArray().length-1), "isBround",String.valueOf(roomblock1.isBround));
                ini.put(String.format("roomblock%d", Main.roomblocks.toArray().length-1), "isDiscover",String.valueOf(roomblock1.isDiscover));
                ini.put(String.format("roomblock%d", Main.roomblocks.toArray().length-1), "isRtmpOpen",String.valueOf(roomblock1.isRtmpOpen));


                // 写入 INI 文件
                ini.store(new File("setting.ini"));
                timeline.play();
                showRoom(vbox);

            } catch (IOException e1) {
                e1.printStackTrace();
            }



        });
        saveButton.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;",myColor.backgroundColor));
        saveButton.setOpacity(0.1);
        saveButton.setPrefWidth(256);
        saveButton.setPrefHeight(59);
        saveButton.setLayoutX(768);
        saveButton.setLayoutY(538);
        saveButton.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                root.getChildren().add(root.getChildren().indexOf(saveButton)-1,saveButton_EImageView);

            }else {
                root.getChildren().remove(saveButton_EImageView);

            }
        });

        root.getChildren().add(addBackgroundImageView);
        root.getChildren().add(nameTextField);
        root.getChildren().add(indexText);
        root.getChildren().add(scrollPane);
        root.getChildren().add(refreshButton);
        root.getChildren().add(saveButton);
        root.getChildren().add(exitButton);
        root.getChildren().add(cameraTestView);
        root.getChildren().add(testStartButton);
        root.getChildren().add(testStopButton);

        anima.translateTransition_G(0,0,30,0,100,20,addBackgroundImageView);

    }

    public static void showIndexBlock(VBox vbox) {
        vbox.getChildren().clear();
        for (int i = 0; i < indexBlocks.toArray().length; i++) {
            indexBlocks.get(i).init();
            vbox.getChildren().add(indexBlocks.get(i).root);
        }

    }

    private static void insertPage(){
        Stage stage = new Stage();
        insertStage = stage;
        Pane root = new Pane();
        Scene scene = new Scene(root, 881, 369);

        block.PaneRoundedRectangle(root,30);
        block.PanesetOnMouseMove(root,stage);

        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        insertPage_Init(root);
        stage.show();

    }

    private static void insertPage_Init(Pane root) {
        Image BackgroundImage = new Image("res/images/control/insert/background.png");
        ImageView BackgroundImageView = new ImageView(BackgroundImage);
        BackgroundImageView.setLayoutX(-30);
        BackgroundImageView.setLayoutY(0);
        BackgroundImageView.setFitWidth(889);
        BackgroundImageView.setFitHeight(370);
        //backgroundImageView.setPreserveRatio(true);
        BackgroundImageView.setSmooth(true);

        Text urlText = new Text("null");
        urlText.setLayoutX(139);
        urlText.setLayoutY(139+14);
        urlText.setStyle("-fx-background-color: transparent;");
        urlText.setFont(myFont.Outfit_Medium_14);

        Button dirUrlButton = new Button();
        dirUrlButton.setText("");
        dirUrlButton.setOnAction(e -> {
            dirUrlButton.setOpacity(0.6);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {
                        dirUrlButton.setOpacity(0.1);
                        DirectoryChooser directoryChooser = new DirectoryChooser();
                        directoryChooser.setTitle("选择图片文件的上一级目录");
                        File selectedDirectory = directoryChooser.showDialog(insertStage);
                        urlText.setText(selectedDirectory.getAbsolutePath());

                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            // 开始播放动画
            timeline.play();

        });
        dirUrlButton.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;", myColor.chooseGroundColor));
        dirUrlButton.setOpacity(0.1);
        dirUrlButton.setPrefWidth(100);
        dirUrlButton.setPrefHeight(59);
        dirUrlButton.setLayoutX(595);
        dirUrlButton.setLayoutY(122);

        Button startButton = new Button();
        startButton.setText("");
        startButton.setOnAction(e -> {
            startButton.setOpacity(0.6);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {
                        startButton.setOpacity(0.1);
                        try {
                            FaceInsert.start(urlText.getText());
                        } catch (OrtException ex) {
                            throw new RuntimeException(ex);
                        }

                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            // 开始播放动画
            timeline.play();

        });
        startButton.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;", myColor.chooseGroundColor));
        startButton.setOpacity(0.1);
        startButton.setPrefWidth(256);
        startButton.setPrefHeight(59);
        startButton.setLayoutX(595);
        startButton.setLayoutY(281);

        Button exitButton = new Button();
        exitButton.setText("");
        exitButton.setOnAction(e -> {
            exitButton.setOpacity(0.6);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {

                        exitButton.setOpacity(0.3);
                        isinsertStageOpen = false;
                        insertStage.close();
                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            // 开始播放动画
            timeline.play();

        });
        exitButton.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;",myColor.backgroundColor));
        exitButton.setOpacity(0.3);
        exitButton.setPrefWidth(25);
        exitButton.setPrefHeight(25);
        exitButton.setLayoutX(829);
        exitButton.setLayoutY(29);

        root.getChildren().add(BackgroundImageView);
        root.getChildren().add(urlText);
        root.getChildren().add(dirUrlButton);
        root.getChildren().add(startButton);
        root.getChildren().add(exitButton);


        anima.translateTransition_G(0,0,30,0,100,20,BackgroundImageView);
    }


}
