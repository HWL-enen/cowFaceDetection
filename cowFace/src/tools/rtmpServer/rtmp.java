import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.IOException;
import java.io.OutputStream;

public class rtmp {
    private Process ffmpegProcess;
    private OutputStream outputStream;
    private String name;

    public rtmp(String name) {
        this.name = name;
    }

    // 初始化 FFmpeg 进程
    public void startStreaming(int width, int height, int fps) throws IOException {
        // FFmpeg 命令
        String[] command = {
                "lib/ffmpeg-master-latest-win64-gpl/bin/ffmpeg",
                "-y",
                "-f", "image2pipe",
                "-vcodec", "png",
                "-r", String.valueOf(fps),
                "-i", "-",
                "-c:v", "libx264",
                "-b:v", String.valueOf(rtmpClient.bits),
                "-preset", "ultrafast",
                "-tune", "zerolatency",
                "-pix_fmt", "yuv420p",
                "-f", "flv",
                String.format("rtmp://%s:%s/live/%s",rtmpClient.host, rtmpClient.port,name)
        };

        // 启动 FFmpeg 进程
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        try {
            ffmpegProcess = processBuilder.start();
            outputStream = ffmpegProcess.getOutputStream();
        } catch (IOException e) {
            System.err.println("启动 FFmpeg 进程失败: " + e.getMessage());
            throw e;
        }
    }

    // 推送一帧 Mat 数据
    public void pushFrame(Mat frame) throws IOException {
        if (frame.empty()) {
            return;
        }

        if (outputStream == null) {
            System.err.println("输出流未初始化，无法推送帧数据");
            return;
        }

        // 将 Mat 转换为 PNG 字节数组
        MatOfByte buffer = new MatOfByte();
        Imgcodecs.imencode(".png", frame, buffer);
        byte[] frameBytes = buffer.toArray();

        try {
            // 将帧数据写入 FFmpeg 进程的标准输入流
            outputStream.write(frameBytes);
            outputStream.flush();
        } catch (IOException e) {
            System.err.println("推送帧数据时发生异常: " + e.getMessage());
            throw e;
        }
    }

    // 停止推流
    public void stopStreaming() {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                System.err.println("关闭输出流时发生异常: " + e.getMessage());
                e.printStackTrace();
            }
        }
        if (ffmpegProcess != null) {
            ffmpegProcess.destroy();
        }
    }
}