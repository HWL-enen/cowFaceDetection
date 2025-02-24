import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;


public class cameraTest {
    private VideoCapture capture;
    private ImageView imageView;

    private boolean isCapturing = true;
    private static final int FPS = 30; // 帧率

    private int com;

    public cameraTest(int com, ImageView imageView) {
        this.com = com;
        this.imageView = imageView;
    }

    public cameraTest(ImageView imageView, int com) {
        this.imageView = imageView;
        this.com = com;
    }

    cameraTest(int com) {
        this.com = com;
    }

    public void setCom(int com) {
        this.com = com;
    }

    public void startCameraCapture() {
        // 尝试打开默认摄像头
        isCapturing = true;
        capture = new VideoCapture(com);
        if (capture.isOpened()) {
            // 创建一个线程来持续采集和更新图像
            Thread captureThread = new Thread(() -> {
                while (isCapturing) {
                    Mat frame = new Mat();
                    if (capture.read(frame)) {
                        // 将 Mat 转换为 JavaFX Image
                        Image fxImage = matToFXImage(frame);
                        // 在 JavaFX 应用线程中更新 ImageView
                        Platform.runLater(() -> imageView.setImage(fxImage));
                    }
                    // 控制帧率
                    try {
                        Thread.sleep(1000 / FPS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                capture.release();
            });
            captureThread.setDaemon(true);
            captureThread.start();
        } else {
            System.err.println("无法打开摄像头");
        }
    }

    private  Image matToFXImage(Mat mat) {
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
        stopCapture();
        if (capture != null) {
            capture.release();
        }
    }

    public void stopCapture() {
        isCapturing = false;
    }
}