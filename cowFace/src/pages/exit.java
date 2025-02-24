import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;

public class exit {
    public static Pane root = new Pane();
    public ImageView exitBackImageView;
    public ImageView exitBlockImageView;
    public exit() {
        root.setLayoutX(0);
        root.setLayoutY(0);
        root.setPrefWidth(1280);
        root.setPrefHeight(720);

        Image exitBackImage = new Image("res/images/exit/exitBack.png");
        exitBackImageView = new ImageView(exitBackImage);
        exitBackImageView.setLayoutX(0);
        exitBackImageView.setLayoutY(0);
        exitBackImageView.setFitWidth(1280);
        exitBackImageView.setFitHeight(720);
        //backgroundImageView.setPreserveRatio(true);
        exitBackImageView.setSmooth(true);

        Image exitBlockImage = new Image("res/images/exit/exitBlock.png");
        exitBlockImageView = new ImageView(exitBlockImage);
        exitBlockImageView.setLayoutX(261);
        exitBlockImageView.setLayoutY(128-200);
        exitBlockImageView.setFitWidth(766);
        exitBlockImageView.setFitHeight(240);
        //backgroundImageView.setPreserveRatio(true);
        exitBlockImageView.setSmooth(true);

        root.getChildren().add(exitBackImageView);
        root.getChildren().add(exitBlockImageView);


        Button shutdown = new Button();
        shutdown.setText("");
        shutdown.setOnAction(e -> {
            shutdown.setOpacity(0.6);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {


                        shutdown.setOpacity(0.1);
                        shutdown();

                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            // 开始播放动画
            timeline.play();

        });
        shutdown.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;", myColor.backgroundColor));
        shutdown.setOpacity(0.1);
        shutdown.setPrefWidth(128);
        shutdown.setPrefHeight(131);
        shutdown.setLayoutX(318);
        shutdown.setLayoutY(152);

        Button reboot = new Button();
        reboot.setText("");
        reboot.setOnAction(e -> {
            reboot.setOpacity(0.6);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {


                        reboot.setOpacity(0.1);
                        reboot();

                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            // 开始播放动画
            timeline.play();

        });
        reboot.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;", myColor.backgroundColor));
        reboot.setOpacity(0.1);
        reboot.setPrefWidth(96);
        reboot.setPrefHeight(98);
        reboot.setLayoutX(575);
        reboot.setLayoutY(158);

        Button exit = new Button();
        exit.setText("");
        exit.setOnAction(e -> {
            exit.setOpacity(0.6);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(100), e1 -> {


                        exit.setOpacity(0.1);
                        logout();

                    })
            );
            // 设置动画只执行一次
            timeline.setCycleCount(1);
            // 开始播放动画
            timeline.play();

        });
        exit.setStyle(String.format("-fx-background-color: %s  ;-fx-text-fill: white; -fx-border-color: transparent;", myColor.backgroundColor));
        exit.setOpacity(0.1);
        exit.setPrefWidth(128);
        exit.setPrefHeight(131);
        exit.setLayoutX(820);
        exit.setLayoutY(152);

        root.getChildren().add(shutdown);
        root.getChildren().add(reboot);
        root.getChildren().add(exit);

    }

    public void close(){
        anima.translateTransition(0,200,0,0,200,exitBlockImageView);
    }
    public void open(){
        anima.translateTransition_G(0,0,0,200,200,20,exitBlockImageView);
    }

    private static void shutdown() {
        String os = System.getProperty("os.name").toLowerCase();
        try {
            ProcessBuilder processBuilder;
            if (os.contains("win")) {
                processBuilder = new ProcessBuilder("shutdown", "/s", "/t", "0");
            } else if (os.contains("linux")) {
                processBuilder = new ProcessBuilder("sudo", "shutdown", "-h", "now");
            } else {
                System.out.println("Unsupported operating system");
                return;
            }
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println(os + " 关机命令已执行");
            } else {
                System.out.println(os + " 关机命令执行失败，错误代码: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void reboot() {
        String os = System.getProperty("os.name").toLowerCase();
        try {
            ProcessBuilder processBuilder;
            if (os.contains("win")) {
                processBuilder = new ProcessBuilder("shutdown", "/r", "/t", "0");
            } else if (os.contains("linux")) {
                processBuilder = new ProcessBuilder("sudo", "reboot");
            } else {
                System.out.println("Unsupported operating system");
                return;
            }
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println(os + " 重启命令已执行");
            } else {
                System.out.println(os + " 重启命令执行失败，错误代码: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void logout() {
        String os = System.getProperty("os.name").toLowerCase();
        try {
            ProcessBuilder processBuilder;
            if (os.contains("win")) {
                processBuilder = new ProcessBuilder("shutdown", "/l");
            } else if (os.contains("linux")) {
                processBuilder = new ProcessBuilder("sudo", "pkill", "-KILL", "-u", System.getProperty("user.name"));
            } else {
                System.out.println("Unsupported operating system");
                return;
            }
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println(os + " 用户登出命令已执行");
            } else {
                System.out.println(os + " 用户登出命令执行失败，错误代码: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


}
