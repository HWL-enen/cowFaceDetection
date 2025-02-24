import ai.onnxruntime.*;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.highgui.HighGui;

import java.io.File;
import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Map;

public class RealTimeObjectDetection {
    static {
        // 加载 OpenCV 本地库
        try {
            String basePath = new File(".").getAbsolutePath();
            String libraryPath = basePath + File.separator + "lib" + File.separator + "opencv_470" + File.separator + "opencv_java470.dll";
            System.load(libraryPath);
            System.out.println("OpenCV 库加载成功！");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("无法加载本地库: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // 打开摄像头
        VideoCapture capture = new VideoCapture(0);
        if (!capture.isOpened()) {
            System.out.println("无法打开摄像头");
            return;
        }

        try (OrtEnvironment env = OrtEnvironment.getEnvironment();
             OrtSession session = env.createSession("res/model/yolo11n.onnx", new OrtSession.SessionOptions())) {

            Mat frame = new Mat();
            while (true) {
                // 读取一帧图像
                if (capture.read(frame)) {
                    // 图像预处理
                    Mat resizedFrame = new Mat();
                    Imgproc.resize(frame, resizedFrame, new Size(640, 640));
                    Mat normalizedFrame = new Mat();
                    resizedFrame.convertTo(normalizedFrame, CvType.CV_32F, 1.0 / 255.0);

                    // 将图像数据转换为 FloatBuffer
                    FloatBuffer inputBuffer = FloatBuffer.allocate((int) (normalizedFrame.total() * normalizedFrame.channels()));
                    float[] data = new float[(int) (normalizedFrame.total() * normalizedFrame.channels())];
                    normalizedFrame.get(0, 0, data);
                    inputBuffer.put(data);
                    inputBuffer.rewind();

                    // 创建输入张量
                    long[] inputShape = {1, 3, 640, 640};
                    OnnxTensor inputTensor = OnnxTensor.createTensor(env, inputBuffer, inputShape);

                    // 运行推理
                    Map<String, OnnxTensor> inputs = new HashMap<>();
                    inputs.put(session.getInputNames().iterator().next(), inputTensor);
                    try (OrtSession.Result output = session.run(inputs)) {
                        // 正确将 OnnxValue 转换为 OnnxTensor
                        OnnxValue onnxValue = output.get(0);
                        if (onnxValue instanceof OnnxTensor) {
                            OnnxTensor outputTensor = (OnnxTensor) onnxValue;
                            float[][][] outputData = (float[][][]) outputTensor.getValue();

                            // 简单示例：假设输出数据包含检测框信息，这里简单处理显示检测框
                            for (float[][] detections : outputData) {
                                for (float[] detection : detections) {
                                    if (detection[4] > 0.5) { // 假设第 5 个值为置信度，阈值设为 0.5
                                        int x1 = (int) (detection[0] * frame.width());
                                        int y1 = (int) (detection[1] * frame.height());
                                        int x2 = (int) (detection[2] * frame.width());
                                        int y2 = (int) (detection[3] * frame.height());
                                        Imgproc.rectangle(frame, new Point(x1, y1), new Point(x2, y2), new Scalar(0, 255, 0), 2);
                                    }
                                }
                            }
                        } else {
                            System.err.println("输出不是 OnnxTensor 类型");
                        }
                    }

                    // 显示处理后的帧
                    HighGui.imshow("Real-time Object Detection", frame);

                    // 按 'q' 键退出循环
                    if (HighGui.waitKey(1) == 'q') {
                        break;
                    }
                }
            }
        } catch (OrtException e) {
            e.printStackTrace();
        } finally {
            // 释放摄像头资源
            capture.release();
            // 关闭所有 OpenCV 窗口
            HighGui.destroyAllWindows();
        }
    }
}