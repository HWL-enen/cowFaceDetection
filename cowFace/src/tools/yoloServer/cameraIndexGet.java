
import org.opencv.videoio.VideoCapture;

public class cameraIndexGet implements Runnable{

    public void indexGet() {
        Main.cameraIndex.clear();

        int maxCameraIndex = 1; // 最大尝试检测的摄像头编号，可按需调整

        for (int i = 0; i < maxCameraIndex; i++) {
            VideoCapture capture = new VideoCapture(i);
            if (capture.isOpened()) {
                System.out.println("检测到可用摄像头，编号为: " + i);
                Main.cameraIndex.add(i);
                control.indexBlocks.add(new indexBlock(i));
                capture.release();
            } else {
                System.out.println("继续查找");
            }
        }


    }


    @Override
    public void run() {
        indexGet();
    }
}
