import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoWriter;
import org.opencv.videoio.Videoio;

public class RtmpStreamer {
    static {
        // 加载 OpenCV 本地库
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        // RTMP 推流地址
        String rtmpUrl = "rtmp://192.168.23.1:12/live/stream";

        // 视频参数
        int frameWidth = 640;
        int frameHeight = 480;
        int fps = 30;

        // 创建视频写入器
        VideoWriter writer = new VideoWriter(
                rtmpUrl,
                VideoWriter.fourcc('F', 'L', 'V', '1'),
                fps,
                new org.opencv.core.Size(frameWidth, frameHeight)
        );

        if (!writer.isOpened()) {
            System.err.println("无法打开 RTMP 流。");
            return;
        }

        try {
            // 模拟推流 100 帧
            for (int i = 0; i < 100; i++) {
                // 创建一个空白的 Mat 对象作为示例帧
                Mat frame = new Mat(frameHeight, frameWidth, org.opencv.core.CvType.CV_8UC3);
                // 这里可以对 frame 进行图像处理

                // 写入帧到 RTMP 流
                writer.write(frame);

                // 释放当前帧的资源
                frame.release();

                // 模拟帧率
                Thread.sleep(1000 / fps);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放视频写入器
            writer.release();
        }
    }
}