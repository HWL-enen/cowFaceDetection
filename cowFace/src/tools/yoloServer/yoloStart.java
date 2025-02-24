import ai.onnxruntime.OnnxTensor;
import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtException;
import ai.onnxruntime.OrtSession;
import config.ODConfig;
import domain.Detection;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.util.Duration;
import nu.pattern.OpenCV;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;
import utils.Letterbox;
import java.io.File;
import java.nio.FloatBuffer;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class yoloStart implements Runnable {
    public ImageView overImageview;
    public boolean over = true;
    public double fps = 0;
    public boolean isDetection, isBround, isDiscover, isRtmpOpen, complexPD = false;
    public String name;
    public ArrayList<Integer> FpsData = new ArrayList<>();
    // 跳帧检测，一般设置为3，毫秒内视频画面变化是不大的，快了无意义，反而浪费性能
    public int detect_skip = 7;
    // 跳帧计数
    public int detect_skip_index = 0;
    public CsvMng csvMng;
    public Integer chartNum;
    public myChart chart;
    static {
        // 加载opencv动态库，
        //System.load(ClassLoader.getSystemResource("lib/opencv_java470-无用.dll").getPath());
        OpenCV.loadLocally();
    }

    public yoloStart(ImageView overImageview, String name,CsvMng csv,Integer num) {
        this.overImageview = overImageview;
        this.name = name;
        this.csvMng = csv;
        this.chartNum = num;
    }

    public void openReasoning() throws OrtException {

        String model_path = yoloClient.modelUrl;

        List<double[]> colors = new ArrayList<>();



        String[] labels = null;

        // 加载ONNX模型
        OrtEnvironment environment = OrtEnvironment.getEnvironment();
        OrtSession.SessionOptions sessionOptions = new OrtSession.SessionOptions();

        // 使用gpu,需要本机按钻过cuda，并修改pom.xml，不安装也能运行本程序
        // sessionOptions.addCUDA(0);

        OrtSession session = environment.createSession(model_path, sessionOptions);
        String meteStr = session.getMetadata().getCustomMetadata().get("names");


        labels = new String[meteStr.split(",").length];


        Pattern pattern = Pattern.compile("'([^']*)'");
        Matcher matcher = pattern.matcher(meteStr);


        VideoCapture video = new VideoCapture();
        video.open(0);  //获取电脑上第0个摄像头
        //video.open("images/car2.mp4"); //不开启gpu比较卡


        if (!video.isOpened()) {
            System.err.println("打开视频流失败,未检测到监控,请先用vlc软件测试链接是否可以播放！,下面试用默认测试视频进行预览效果！");
        }


        int h = 0;
        while (matcher.find()) {
            labels[h] = matcher.group(1);
            Random random = new Random();
            double[] color = {random.nextDouble() * 256, random.nextDouble() * 256, random.nextDouble() * 256};
            colors.add(color);
            h++;
        }
        // 输出基本信息
        session.getInputInfo().keySet().forEach(x -> {
            try {
                System.out.println("input name = " + x);
                System.out.println(session.getInputInfo().get(x).getInfo().toString());
            } catch (OrtException e) {
                throw new RuntimeException(e);
            }
        });

        // 要检测的图片所在目录
        int minDwDh = Math.min((int) video.get(Videoio.CAP_PROP_FRAME_WIDTH), (int) video.get(Videoio.CAP_PROP_FRAME_HEIGHT));
        int thickness = minDwDh / ODConfig.lineThicknessRatio;
        long start_time = System.currentTimeMillis();
        double ratio = 0;
        double dw = 0;
        double dh = 0;


        Mat img = new Mat();



        float[][] outputData = null;

        rtmp rtmpYolo = new rtmp(name);

        try {
            rtmpYolo.startStreaming(1280, 720, (int)(yoloClient.maxFps*0.5));
        } catch (Exception e) {
            System.out.println("rtmp error");
        }


        List<Detection> detections = new ArrayList<>();

        while (video.read(img)) {
            if (isDetection) {
                if (detect_skip_index % detect_skip == 0) {
                    detect_skip_index = 1;
                    Mat image = img.clone();
                    Imgproc.cvtColor(image, image, Imgproc.COLOR_BGR2RGB);


                    // 在这里先定义下框的粗细、字的大小、字的类型、字的颜色(按比例设置大小粗细比较好一些)

                    // 更改 image 尺寸
                    Letterbox letterbox = new Letterbox();
                    image = letterbox.letterbox(image);

                    ratio = letterbox.getRatio();
                    dw = letterbox.getDw();
                    dh = letterbox.getDh();
                    int rows = letterbox.getHeight();
                    int cols = letterbox.getWidth();
                    int channels = image.channels();


                    // 将Mat对象的像素值赋值给Float[]对象
                    float[] pixels = new float[channels * rows * cols];
                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                            double[] pixel = image.get(j, i);
                            for (int k = 0; k < channels; k++) {
                                // 这样设置相当于同时做了image.transpose((2, 0, 1))操作
                                pixels[rows * cols * k + j * cols + i] = (float) pixel[k] / 255.0f;
                            }
                        }
                    }

                    // 创建OnnxTensor对象
                    long[] shape = {1L, (long) channels, (long) rows, (long) cols};
                    OnnxTensor tensor = OnnxTensor.createTensor(environment, FloatBuffer.wrap(pixels), shape);
                    HashMap<String, OnnxTensor> stringOnnxTensorHashMap = new HashMap<>();
                    stringOnnxTensorHashMap.put(session.getInputInfo().keySet().iterator().next(), tensor);

                    // 运行推理
                    try (OrtSession.Result output = session.run(stringOnnxTensorHashMap)) {
                        outputData = ((float[][][]) output.get(0).getValue())[0];
                    }
                    outputData = transposeMatrix(outputData);




                Map<Integer, List<float[]>> class2Bbox = new HashMap<>();
                for (float[] bbox : outputData) {

                    float[] conditionalProbabilities = Arrays.copyOfRange(bbox, 4, bbox.length);
                    int label = argmax(conditionalProbabilities);
                    float conf = conditionalProbabilities[label];
                    if (conf < yoloClient.sure) continue;

                    bbox[4] = conf;

                    // xywh to (x1, y1, x2, y2)
                    xywh2xyxy(bbox);

                    // skip invalid predictions
                    if (bbox[0] >= bbox[2] || bbox[1] >= bbox[3]) continue;


                    class2Bbox.putIfAbsent(label, new ArrayList<>());
                    class2Bbox.get(label).add(bbox);
                }

                detections = new ArrayList<>();
                for (Map.Entry<Integer, List<float[]>> entry : class2Bbox.entrySet()) {
                    int label = entry.getKey();
                    List<float[]> bboxes = entry.getValue();
                    bboxes = nonMaxSuppression(bboxes, (float) yoloClient.faultTolerance);
                    for (float[] bbox : bboxes) {
                        String labelString = labels[label];
                        Detection detection = new Detection(labelString, entry.getKey(), Arrays.copyOfRange(bbox, 0, 4), bbox[4]);
                        detections.add(detection);
                        if (detection.getLabel().equals("person") && yoloClient.isTimeWithinRange(yoloClient.startTime,yoloClient.endTime,LocalTime.now()) && yoloClient.isPersonDetected && !complexPD) {
                            HttpServer.jiShiDa(String.format("【%s】夜间防盗",name),"检测到人源走动");
                            Timeline timeline = new Timeline(new KeyFrame(Duration.minutes(5),e->{
                                complexPD = false;
                            }));
                            timeline.setCycleCount(1);
                            timeline.play();
                            complexPD = true;
                        }

                        String recognizedPerson = Main.recognizer.recognizeSingleFace(Arrays.copyOfRange(bbox, 5, bbox.length-1));
                        if (recognizedPerson != null) {
                        System.out.println("name:"+recognizedPerson);
                        System.out.println("FACE recognized as: " + recognizedPerson + "-----" + new String(INIHandler.ini.get(recognizedPerson, "name")));
                            String name = new String(INIHandler.ini.get(recognizedPerson, "name"));

                            if (!Main.yololog.storage.containsKey("FACE recognized as: " + recognizedPerson + "-----" + name)){
                                LocalDateTime currentDateTime = LocalDateTime.now();
                                // 定义日期时间格式
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                                DateTimeFormatter formatter0 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM");
                                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy");
                                // 格式化日期时间
                                csvMng.modifyDataInCSV_obj( name,currentDateTime.format(formatter),String.valueOf(Integer.parseInt(csvMng.getValue(name,currentDateTime.format(formatter)))+1) );

                                chartNum++;
                                if (chart!=null){
                                    chart.data_update(chartNum);
                                }

                                Main.home.dayCsv.modifyDataInCSV_obj(currentDateTime.format(formatter0),"Sum",String.valueOf(Integer.parseInt(Main.home.dayCsv.getValue(currentDateTime.format(formatter0),"Sum"))+1));
                                Main.home.monthCsv.modifyDataInCSV_obj(currentDateTime.format(formatter1),"Sum",String.valueOf(Integer.parseInt(Main.home.monthCsv.getValue(currentDateTime.format(formatter1),"Sum"))+1));
                                Main.home.yearCsv.modifyDataInCSV_obj(currentDateTime.format(formatter2),"Sum",String.valueOf(Integer.parseInt(Main.home.yearCsv.getValue(currentDateTime.format(formatter2),"Sum"))+1));
                                Main.home.timeUpdate();
                            }
                            Main.yololog.put("FACE recognized as: " + recognizedPerson + "-----" + name, LocalDateTime.now());
                        }




                    }

                }

                } else {
                    detect_skip_index = detect_skip_index + 1;
                }


                if (isBround) {
                    for (Detection detection : detections) {
                        float[] bbox = detection.getBbox();
                        // 画框
                        Point topLeft = new Point((bbox[0] - dw) / ratio, (bbox[1] - dh) / ratio);
                        Point bottomRight = new Point((bbox[2] - dw) / ratio, (bbox[3] - dh) / ratio);
                        Scalar color = new Scalar(colors.get(detection.getClsId()));
                        Imgproc.rectangle(img, topLeft, bottomRight, color, thickness + 1);
                        // 框上写文字
                        Point boxNameLoc = new Point((bbox[0] - dw) / ratio, (bbox[1] - dh) / ratio - 3);

                        Imgproc.putText(img, detection.getLabel(), boxNameLoc, Imgproc.FONT_HERSHEY_PLAIN, 1.5, color, thickness + 1);
                    }
                }

            }
            FpsData.add((int)(1000.0 / (System.currentTimeMillis() - start_time)));
            if (FpsData.size()>=45){
                double sum = 0.0;
                for (int i : FpsData){
                    sum += i;
                }
                fps = (sum / FpsData.size()) / yoloClient.maxFps;
                FpsData.clear();
            }
            start_time = System.currentTimeMillis();

            if (isRtmpOpen) {
                try {
                    rtmpYolo.pushFrame(img);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            overImageview.setImage(matToFXImage(img));
            if (over) {
                rtmpYolo.stopStreaming();
                break;
            }

        }

        video.release();

    }


    public void scaleCoords(float[] bbox, float orgW, float orgH, float padW, float padH, float gain) {
        // xmin, ymin, xmax, ymax -> (xmin_org, ymin_org, xmax_org, ymax_org)
        bbox[0] = Math.max(0, Math.min(orgW - 1, (bbox[0] - padW) / gain));
        bbox[1] = Math.max(0, Math.min(orgH - 1, (bbox[1] - padH) / gain));
        bbox[2] = Math.max(0, Math.min(orgW - 1, (bbox[2] - padW) / gain));
        bbox[3] = Math.max(0, Math.min(orgH - 1, (bbox[3] - padH) / gain));
    }

    public void xywh2xyxy(float[] bbox) {
        float x = bbox[0];
        float y = bbox[1];
        float w = bbox[2];
        float h = bbox[3];

        bbox[0] = x - w * 0.5f;
        bbox[1] = y - h * 0.5f;
        bbox[2] = x + w * 0.5f;
        bbox[3] = y + h * 0.5f;
    }

    public float[][] transposeMatrix(float[][] m) {
        float[][] temp = new float[m[0].length][m.length];
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length; j++)
                temp[j][i] = m[i][j];
        return temp;
    }

    public List<float[]> nonMaxSuppression(List<float[]> bboxes, float iouThreshold) {

        List<float[]> bestBboxes = new ArrayList<>();

        bboxes.sort(Comparator.comparing(a -> a[4]));

        while (!bboxes.isEmpty()) {
            float[] bestBbox = bboxes.remove(bboxes.size() - 1);
            bestBboxes.add(bestBbox);
            bboxes = bboxes.stream().filter(a -> computeIOU(a, bestBbox) < iouThreshold).collect(Collectors.toList());
        }

        return bestBboxes;
    }

    public float computeIOU(float[] box1, float[] box2) {

        float area1 = (box1[2] - box1[0]) * (box1[3] - box1[1]);
        float area2 = (box2[2] - box2[0]) * (box2[3] - box2[1]);

        float left = Math.max(box1[0], box2[0]);
        float top = Math.max(box1[1], box2[1]);
        float right = Math.min(box1[2], box2[2]);
        float bottom = Math.min(box1[3], box2[3]);

        float interArea = Math.max(right - left, 0) * Math.max(bottom - top, 0);
        float unionArea = area1 + area2 - interArea;
        return Math.max(interArea / unionArea, 1e-8f);

    }

    //返回最大值的索引
    public int argmax(float[] a) {
        float re = -Float.MAX_VALUE;
        int arg = -1;
        for (int i = 0; i < a.length; i++) {
            if (a[i] >= re) {
                re = a[i];
                arg = i;
            }
        }
        return arg;
    }

    public Map<String, String> getImagePathMap(String imagePath) {
        Map<String, String> map = new TreeMap<>();
        File file = new File(imagePath);
        if (file.isFile()) {
            map.put(file.getName(), file.getAbsolutePath());
        } else if (file.isDirectory()) {
            for (File tmpFile : Objects.requireNonNull(file.listFiles())) {
                map.putAll(getImagePathMap(tmpFile.getPath()));
            }
        }
        return map;
    }

    private Image matToFXImage(Mat mat) {
        int width = mat.width();
        int height = mat.height();
        WritableImage writableImage = new WritableImage(width, height);
        PixelWriter pixelWriter = writableImage.getPixelWriter();

        if (mat.channels() == 3) {
            // 处理三通道图像（如BGR格式）
            byte[] buffer = new byte[width * height * 3];
            mat.get(0, 0, buffer);
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int index = (y * width + x) * 3;
                    int b = buffer[index] & 0xff;
                    int g = buffer[index + 1] & 0xff;
                    int r = buffer[index + 2] & 0xff;
                    int argb = (255 << 24) | (r << 16) | (g << 8) | b;
                    pixelWriter.setArgb(x, y, argb);
                }
            }
        } else if (mat.channels() == 1) {
            // 处理单通道图像（如灰度图）
            byte[] buffer = new byte[width * height];
            mat.get(0, 0, buffer);
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int gray = buffer[y * width + x] & 0xff;
                    int argb = (255 << 24) | (gray << 16) | (gray << 8) | gray;
                    pixelWriter.setArgb(x, y, argb);
                }
            }
        }
        return writableImage;
    }

    public void stop() {
        over = true;
    }

    /*public void rtmpClose() {
        isRtmpOpen = false;
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), event -> {
            rtmpYolo.stopStreaming();
        }));
        timeline.setCycleCount(1);
        timeline.play();

    }
    public void rtmpStart() {
        rtmpYolo = new rtmp();
        try{
            rtmpYolo.startStreaming(640,640,(int)(fps*yoloClient.maxFps));
        }catch (Exception e){
            e.printStackTrace();
        }

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), event -> {
            isRtmpOpen = true;
        }));
        timeline.setCycleCount(1);
        timeline.play();

    }*/


    @Override
    public void run() {
        try {
            openReasoning();
        } catch (OrtException e) {
            throw new RuntimeException(e);
        }
    }
}
