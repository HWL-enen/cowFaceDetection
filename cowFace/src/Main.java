import javafx.application.Application;
import javafx.stage.Stage;
import org.ini4j.Ini;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.regex.Pattern;


public class Main extends Application {
    public static Yololog yololog = new Yololog();
    public static Login loginPage = new Login(1280,720,609,458);
    public static Home home = new Home(1280,720);
    public static exit exit;
    public static Ini ini = new Ini();
    public static Ini msgIni = new Ini();
    public static String cpu;
    public static String gpu;
    public static double mem = 0;
    public static ArrayList<roomblock> roomblocks = new ArrayList<>();
    public static ArrayList<Integer> cameraIndex = new ArrayList<>();
    public static FaceRecognizer recognizer = new FaceRecognizer();
    public static ArrayList<msgBlock> msgBlocks = new ArrayList<>();
    public static mqtt mqttserver;
    public static Stage MainStage;

    static {
        // 加载本地库，需要替换为实际的库文件路径

        /*try {
            String basePath = new File(".").getAbsolutePath();
            String libraryPath = basePath + File.separator + "lib" + File.separator + "opencv_470" + File.separator + "opencv_java470.dll";
            System.load(libraryPath);
            System.out.println("OpenCV 库加载成功！");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("无法加载本地库: " + e.getMessage());
        }*/
    }

    @Override
    public void start(Stage stage) throws Exception {

        MainStage = stage;
        loginPage.start(stage);


    }

    public static void main(String[] args) {
        yololog.put_str("-------------------------------start---------------------------------\n\t\t\t\t\twelcome to CowFace\n---------------------------------------------------------------------");
        yololog.put_str("Start logging log files...");
        Main main = new Main();
        main.ini_init();
        msg_init();
        exit = new exit();
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            getWindowsHardwareInfo();
        } else if (os.contains("linux")) {
            getLinuxHardwareInfo();
        } else {
            System.out.println("Unsupported operating system");
        }

        SystemTip systemTip = new SystemTip("欢迎使用","CowFace-1.0.0", TrayIcon.MessageType.INFO);
        systemTip.pushMsg();
        main.launch(args);

    }

    public static void msg_init()  {
        try{
            msgIni.load(new File("msg.ini"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int i = 0;
        while(msgIni.containsKey(String.format("msg%d",i))){
            msgBlocks.add(new msgBlock((double) 0, (double) 0,Boolean.valueOf(msgIni.get(String.format("msg%d",i), "IM")),msgIni.get(String.format("msg%d",i), "msg")));
            i++;
        }
    }

    public void ini_init(){
        try {

            // 读取 INI 文件
            ini.load(new File("setting.ini"));
            String value;

            // 获取配置项的值
            value = new String("#"+ini.get("Color0", "textid"));
            myColor.textidColor = value;
            value = new String("#"+ini.get("Color0", "background"));
            myColor.backgroundColor = value;
            value = new String("#"+ini.get("Color0", "chooseBackground"));
            myColor.chooseGroundColor = value;
            value = new String("#"+ini.get("Color0", "OnchooseGroundColor"));
            myColor.OnchooseGroundColor = value;
            value = new String("#"+ini.get("Color0", "OnchooseGroundColor1"));
            myColor.OnchooseGroundColor1 = value;
            value = new String("#"+ini.get("Color0", "rollBackColor"));
            myColor.rollBackColor = value;
            value = new String("#"+ini.get("Color0", "offRollColor"));
            myColor.offRollColor = value;
            value = new String("#"+ini.get("Color0", "homeBack"));
            myColor.homeBackColor = value;

            value = new String(ini.get("Yolo", "sure"));
            yoloClient.sure = Double.valueOf(value);
            value = new String(ini.get("Yolo", "faultTolerance"));
            yoloClient.faultTolerance = Double.parseDouble(value);
            value = new String(ini.get("Yolo", "modelUrl"));
            yoloClient.modelUrl = value;
            value = new String(ini.get("Yolo", "maxFps"));
            yoloClient.maxFps = Integer.parseInt(value);
            value = new String(ini.get("Yolo", "isPersonDetection"));
            yoloClient.isPersonDetected = Boolean.parseBoolean(value);
            value = new String(ini.get("Yolo", "PersonStart"));
            String var0[] = value.split(":");
            yoloClient.startTime = LocalTime.of(Integer.parseInt(var0[0]), Integer.parseInt(var0[1]),Integer.parseInt(var0[2]));
            value = new String(ini.get("Yolo", "PersonEnd"));
            String var1[] = value.split(":");
            yoloClient.endTime = LocalTime.of(Integer.parseInt(var1[0]), Integer.parseInt(var1[1]),Integer.parseInt(var1[2]));




            value = new String(ini.get("mqttServer", "host"));
            mqttClient.host = value;
            value = new String(ini.get("mqttServer", "port"));
            mqttClient.port = value;
            value = new String(ini.get("mqttServer", "Qos"));
            mqttClient.Qos = Integer.valueOf(value);
            value = new String(ini.get("mqttServer", "cycleTime"));
            mqttClient.cycleTime = Integer.valueOf(value);

            value = new String(ini.get("rtmpServer", "host"));
            rtmpClient.host = value;
            value = new String(ini.get("rtmpServer", "port"));
            rtmpClient.port = value;
            value = new String(ini.get("rtmpServer", "bits"));
            rtmpClient.bits = Integer.valueOf(value);
            value = new String(ini.get("rtmpServer", "bandwidth"));
            rtmpClient.bandwidth = Integer.valueOf(value);

            boolean qwe;

            for (int i = 0;i<999;++i){
                if (i%2==0){
                    qwe = true;
                }else {
                    qwe = false;
                }
                if (!ini.containsKey(String.format("roomblock%d",i))){
                    break;
                }
                roomblock roomblock1 = new roomblock((i % 4) * 317,0,qwe,i, ini.get(String.format("roomblock%d", i), "roomName"));
                roomblocks.add(roomblock1);
                roomblock1.numberHead = new String(ini.get(String.format("roomblock%d",i), "numberHead"));
                roomblock1.numberTail = new String(ini.get(String.format("roomblock%d",i), "numberTail"));
                roomblock1.com = Integer.valueOf(ini.get(String.format("roomblock%d",i), "com"));
                roomblock1.avg = Double.parseDouble(ini.get(String.format("roomblock%d",i), "avg"));
                roomblock1.isDetection = Boolean.valueOf(ini.get(String.format("roomblock%d",i), "isDetection"));
                if (!roomblock1.isDetection){
                    Home.isAllDecetion = false;
                }
                roomblock1.isBround = Boolean.valueOf(ini.get(String.format("roomblock%d",i), "isBround"));
                if (!roomblock1.isBround){
                    Home.isAllBorder = false;
                }
                roomblock1.isDiscover = Boolean.valueOf(ini.get(String.format("roomblock%d",i), "isDiscover"));
                roomblock1.isRtmpOpen = Boolean.valueOf(ini.get(String.format("roomblock%d",i), "isRtmpOpen"));
                if (!roomblock1.isRtmpOpen){
                    Home.isAllRtmp = false;
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getWindowsHardwareInfo() {
        try {
            // 获取 CPU 信息
            ProcessBuilder cpuProcessBuilder = new ProcessBuilder("wmic", "cpu", "get", "name");
            Process cpuProcess = cpuProcessBuilder.start();
            BufferedReader cpuReader = new BufferedReader(new InputStreamReader(cpuProcess.getInputStream()));
            String cpuLine;
            while ((cpuLine = cpuReader.readLine())!= null) {
                if (!cpuLine.trim().isEmpty()) {
                    cpu = cpuLine;
                }
            }
            cpuReader.close();

            // 获取内存信息
            ProcessBuilder memoryProcessBuilder = new ProcessBuilder("wmic", "memorychip", "get", "capacity");
            Process memoryProcess = memoryProcessBuilder.start();
            BufferedReader memoryReader = new BufferedReader(new InputStreamReader(memoryProcess.getInputStream()));
            String memoryLine;

            while ((memoryLine = memoryReader.readLine())!= null) {
                if (!memoryLine.trim().isEmpty() && isAllDigits(memoryLine.trim())) {
                    mem += ((Double.valueOf(memoryLine)/1024)/1024)/1024;

                }
            }
            memoryReader.close();

            // 获取显卡信息
            ProcessBuilder gpuProcessBuilder = new ProcessBuilder("wmic", "path", "Win32_VideoController", "get", "name");
            Process gpuProcess = gpuProcessBuilder.start();
            BufferedReader gpuReader = new BufferedReader(new InputStreamReader(gpuProcess.getInputStream()));
            String gpuLine;
            while ((gpuLine = gpuReader.readLine())!= null) {
                if (!gpuLine.trim().isEmpty()) {
                    gpu = gpuLine;
                }
            }
            gpuReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getLinuxHardwareInfo() {
        try {
            // 获取 CPU 信息
            ProcessBuilder cpuProcessBuilder = new ProcessBuilder("cat", "/proc/cpuinfo");
            Process cpuProcess = cpuProcessBuilder.start();
            BufferedReader cpuReader = new BufferedReader(new InputStreamReader(cpuProcess.getInputStream()));
            String cpuLine;
            while ((cpuLine = cpuReader.readLine())!= null) {
                if (cpuLine.contains("model name")) {
                    cpu = cpuLine;
                }
            }
            cpuReader.close();

            // 获取内存信息
            ProcessBuilder memoryProcessBuilder = new ProcessBuilder("free", "-m");
            Process memoryProcess = memoryProcessBuilder.start();
            BufferedReader memoryReader = new BufferedReader(new InputStreamReader(memoryProcess.getInputStream()));
            String memoryLine;
            while ((memoryLine = memoryReader.readLine())!= null) {
                if (memoryLine.startsWith("Mem:")) {
                    mem += ((Double.parseDouble(memoryLine)/1024)/1024)/1024;
                }
            }
            memoryReader.close();

            // 获取显卡信息
            ProcessBuilder gpuProcessBuilder = new ProcessBuilder("lspci", "|", "grep", "VGA");
            Process gpuProcess = gpuProcessBuilder.start();
            BufferedReader gpuReader = new BufferedReader(new InputStreamReader(gpuProcess.getInputStream()));
            String gpuLine;
            while ((gpuLine = gpuReader.readLine())!= null) {
                if (!gpuLine.trim().isEmpty()) {
                    gpu = gpuLine;
                }
            }
            gpuReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static boolean isAllDigits(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return Pattern.matches("\\d+", str);
    }


}