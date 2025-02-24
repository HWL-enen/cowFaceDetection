import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HardwareInfoDetector {
    public static void main(String[] args) {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            getWindowsHardwareInfo();
        } else if (os.contains("linux")) {
            getLinuxHardwareInfo();
        } else {
            System.out.println("Unsupported operating system");
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
                    System.out.println("Windows CPU Info: " + cpuLine);
                }
            }
            cpuReader.close();

            // 获取内存信息
            ProcessBuilder memoryProcessBuilder = new ProcessBuilder("wmic", "memorychip", "get", "capacity");
            Process memoryProcess = memoryProcessBuilder.start();
            BufferedReader memoryReader = new BufferedReader(new InputStreamReader(memoryProcess.getInputStream()));
            String memoryLine;
            while ((memoryLine = memoryReader.readLine())!= null) {
                if (!memoryLine.trim().isEmpty()) {
                    System.out.println("Windows Memory Info: " + memoryLine);
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
                    System.out.println("Windows GPU Info: " + gpuLine);
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
                    System.out.println("Linux CPU Info: " + cpuLine);
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
                    System.out.println("Linux Memory Info: " + memoryLine);
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
                    System.out.println("Linux GPU Info: " + gpuLine);
                }
            }
            gpuReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}