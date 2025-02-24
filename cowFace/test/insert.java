import ai.onnxruntime.OnnxTensor;
import ai.onnxruntime.OrtEnvironment;
import ai.onnxruntime.OrtException;
import ai.onnxruntime.OrtSession;
import config.ODConfig;
import domain.Detection;
import utils.Letterbox;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

import java.io.File;
import java.nio.FloatBuffer;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class insert {

    static {
        // 加载opencv动态库，
        //System.load(ClassLoader.getSystemResource("lib/opencv_java470-无用.dll").getPath());
        nu.pattern.OpenCV.loadLocally();
    }

    public static void main(String[] args) throws OrtException {

        String model_path = "res/model/yolo11n.onnx";

        List<double[]> colors = new ArrayList<>();

        float confThreshold = 0.35F;

        float nmsThreshold = 0.55F;

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


        ODConfig odConfig = new ODConfig();
        VideoCapture video = new VideoCapture();
        video.open(0);  //获取电脑上第0个摄像头
        //video.open("images/car2.mp4"); //不开启gpu比较卡

        //可以把识别后的视频在通过rtmp转发到其他流媒体服务器，就可以远程预览视频后视频，需要使用ffmpeg将连续图片合成flv 等等，很简单。
        if (!video.isOpened()) {
            System.err.println("打开视频流失败,未检测到监控,请先用vlc软件测试链接是否可以播放！,下面试用默认测试视频进行预览效果！");
        }


        int h = 0;
        while (matcher.find()) {
            labels[h] = matcher.group(1);
            Random random = new Random();
            double[] color = {random.nextDouble()*256, random.nextDouble()*256, random.nextDouble()*256};
            colors.add(color);
            h++;
        }
        // 输出基本信息
        session.getInputInfo().keySet().forEach(x-> {
            try {
                System.out.println("input name = " + x);
                System.out.println(session.getInputInfo().get(x).getInfo().toString());
            } catch (OrtException e) {
                throw new RuntimeException(e);
            }
        });

        // 要检测的图片所在目录
        int minDwDh = Math.min((int)video.get(Videoio.CAP_PROP_FRAME_WIDTH), (int)video.get(Videoio.CAP_PROP_FRAME_HEIGHT));
        int thickness = minDwDh / ODConfig.lineThicknessRatio;
        double fontSize = minDwDh / ODConfig.fontSizeRatio;
        int fontFace = Imgproc.FONT_HERSHEY_SIMPLEX;
        long start_time = System.currentTimeMillis();
        double ratio=0;
        double dw=0;
        double dh=0;


        Mat img = new Mat();

        // 跳帧检测，一般设置为3，毫秒内视频画面变化是不大的，快了无意义，反而浪费性能
        int detect_skip = 4;

        // 跳帧计数
        int detect_skip_index = 1;

        float[][] outputData = null;

        while (video.read(img)) {
            if ((detect_skip_index % detect_skip == 0) || outputData == null){
                Mat image = img.clone();
                Imgproc.cvtColor(image, image, Imgproc.COLOR_BGR2RGB);


                // 在这里先定义下框的粗细、字的大小、字的类型、字的颜色(按比例设置大小粗细比较好一些)
                start_time = System.currentTimeMillis();
                // 更改 image 尺寸
                Letterbox letterbox = new Letterbox();
                image = letterbox.letterbox(image);

                ratio  = letterbox.getRatio();
                dw = letterbox.getDw();
                dh = letterbox.getDh();
                int rows  = letterbox.getHeight();
                int cols  = letterbox.getWidth();
                int channels = image.channels();

                // 将Mat对象的像素值赋值给Float[]对象
                float[] pixels = new float[channels * rows * cols];
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        double[] pixel = image.get(j,i);
                        for (int k = 0; k < channels; k++) {
                            // 这样设置相当于同时做了image.transpose((2, 0, 1))操作
                            pixels[rows*cols*k+j*cols+i] = (float) pixel[k]/255.0f;
                        }
                    }
                }

                // 创建OnnxTensor对象
                long[] shape = { 1L, (long)channels, (long)rows, (long)cols };
                OnnxTensor tensor = OnnxTensor.createTensor(environment, FloatBuffer.wrap(pixels), shape);
                HashMap<String, OnnxTensor> stringOnnxTensorHashMap = new HashMap<>();
                stringOnnxTensorHashMap.put(session.getInputInfo().keySet().iterator().next(), tensor);

                // 运行推理
                try (OrtSession.Result output = session.run(stringOnnxTensorHashMap)) {
                    outputData = ((float[][][]) output.get(0).getValue())[0];
                }

                outputData = transposeMatrix(outputData);


            }else{
                detect_skip_index = detect_skip_index + 1;
            }

                Map<Integer, List<float[]>> class2Bbox = new HashMap<>();
                for (float[] bbox : outputData) {


                    float[] conditionalProbabilities = Arrays.copyOfRange(bbox, 4, bbox.length);
                    int label = argmax(conditionalProbabilities);
                    float conf = conditionalProbabilities[label];
                    if (conf < confThreshold) continue;

                    bbox[4] = conf;

                    // xywh to (x1, y1, x2, y2)
                    xywh2xyxy(bbox);

                    // skip invalid predictions
                    if (bbox[0] >= bbox[2] || bbox[1] >= bbox[3]) continue;


                    class2Bbox.putIfAbsent(label, new ArrayList<>());
                    class2Bbox.get(label).add(bbox);
                }

                List<Detection> detections = new ArrayList<>();
                for (Map.Entry<Integer, List<float[]>> entry : class2Bbox.entrySet()) {
                    int label = entry.getKey();
                    List<float[]> bboxes = entry.getValue();
                    bboxes = nonMaxSuppression(bboxes, nmsThreshold);
                    for (float[] bbox : bboxes) {
                        String labelString = labels[label];
                        detections.add(new Detection(labelString,entry.getKey(), Arrays.copyOfRange(bbox, 0, 4), bbox[4]));

                    }
                }




                System.out.println("over");
            }
        }




    public static void scaleCoords(float[] bbox, float orgW, float orgH, float padW, float padH, float gain) {
        // xmin, ymin, xmax, ymax -> (xmin_org, ymin_org, xmax_org, ymax_org)
        bbox[0] = Math.max(0, Math.min(orgW - 1, (bbox[0] - padW) / gain));
        bbox[1] = Math.max(0, Math.min(orgH - 1, (bbox[1] - padH) / gain));
        bbox[2] = Math.max(0, Math.min(orgW - 1, (bbox[2] - padW) / gain));
        bbox[3] = Math.max(0, Math.min(orgH - 1, (bbox[3] - padH) / gain));
    }
    public static void xywh2xyxy(float[] bbox) {
        float x = bbox[0];
        float y = bbox[1];
        float w = bbox[2];
        float h = bbox[3];

        bbox[0] = x - w * 0.5f;
        bbox[1] = y - h * 0.5f;
        bbox[2] = x + w * 0.5f;
        bbox[3] = y + h * 0.5f;
    }

    public static float[][] transposeMatrix(float [][] m){
        float[][] temp = new float[m[0].length][m.length];
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length; j++)
                temp[j][i] = m[i][j];
        return temp;
    }

    public static List<float[]> nonMaxSuppression(List<float[]> bboxes, float iouThreshold) {

        List<float[]> bestBboxes = new ArrayList<>();

        bboxes.sort(Comparator.comparing(a -> a[4]));

        while (!bboxes.isEmpty()) {
            float[] bestBbox = bboxes.remove(bboxes.size() - 1);
            bestBboxes.add(bestBbox);
            bboxes = bboxes.stream().filter(a -> computeIOU(a, bestBbox) < iouThreshold).collect(Collectors.toList());
        }

        return bestBboxes;
    }

    public static float computeIOU(float[] box1, float[] box2) {

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
    public static int argmax(float[] a) {
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

    public static Map<String, String> getImagePathMap(String imagePath){
        Map<String, String> map = new TreeMap<>();
        File file = new File(imagePath);
        if(file.isFile()){
            map.put(file.getName(), file.getAbsolutePath());
        }else if(file.isDirectory()){
            for(File tmpFile : Objects.requireNonNull(file.listFiles())){
                map.putAll(getImagePathMap(tmpFile.getPath()));
            }
        }
        return map;
    }



}
