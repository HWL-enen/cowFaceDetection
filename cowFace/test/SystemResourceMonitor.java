import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SystemResourceMonitor {

    public static void main(String[] args) {
        try {
            double cpuUsage = getCPUUsage();
            double memoryUsage = getMemoryUsage();
            double gpuUsage = getGPUUsage();

            System.out.printf("CPU 利用率: %.2f%%%n", cpuUsage);
            System.out.printf("内存利用率: %.2f%%%n", memoryUsage);
            System.out.printf("GPU 利用率: %.2f%%%n", gpuUsage);


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 获取 CPU 利用率
    public static double getCPUUsage() throws IOException, InterruptedException {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            // Windows 系统
            ProcessBuilder pb = new ProcessBuilder("wmic", "cpu", "get", "loadpercentage");
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().matches("\\d+")) {
                    return Double.parseDouble(line.trim());
                }
            }
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            // Linux 或 macOS 系统
            ProcessBuilder pb = new ProcessBuilder("top", "-bn1");
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("%Cpu(s)")) {
                    int startIndex = line.indexOf(":") + 1;
                    int endIndex = line.indexOf("us,");
                    String cpuUsageStr = line.substring(startIndex, endIndex).trim();
                    return Double.parseDouble(cpuUsageStr);
                }
            }
        }
        return -1;
    }

    // 获取内存利用率
    public static double getMemoryUsage() throws IOException, InterruptedException {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            // Windows 系统
            ProcessBuilder pb = new ProcessBuilder("wmic", "OS", "get", "FreePhysicalMemory,TotalVisibleMemorySize", "/VALUE");
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            long totalMemory = 0;
            long freeMemory = 0;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("TotalVisibleMemorySize=")) {
                    totalMemory = Long.parseLong(line.split("=")[1]);
                } else if (line.startsWith("FreePhysicalMemory=")) {
                    freeMemory = Long.parseLong(line.split("=")[1]);
                }
            }
            if (totalMemory > 0) {
                return (double) (totalMemory - freeMemory) / totalMemory * 100;
            }
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            // Linux 或 macOS 系统
            ProcessBuilder pb = new ProcessBuilder("free", "-m");
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            long totalMemory = 0;
            long freeMemory = 0;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Mem:")) {
                    String[] parts = line.trim().split("\\s+");
                    totalMemory = Long.parseLong(parts[1]);
                    freeMemory = Long.parseLong(parts[3]);
                }
            }
            if (totalMemory > 0) {
                return (double) (totalMemory - freeMemory) / totalMemory * 100;
            }
        }
        return -1;
    }

    // 获取 GPU 利用率
    public static double getGPUUsage() throws IOException, InterruptedException {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win") || os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            if (isNvidiaGPUAvailable()) {
                ProcessBuilder pb = new ProcessBuilder("nvidia-smi", "--query-gpu=utilization.gpu", "--format=csv,noheader,nounits");
                Process process = pb.start();
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                if ((line = reader.readLine()) != null) {
                    return Double.parseDouble(line.trim());
                }
            }
        }
        return -1;
    }

    // 检查是否有 NVIDIA GPU 可用
    private static boolean isNvidiaGPUAvailable() throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("nvidia-smi");
        Process process = pb.start();
        return process.waitFor() == 0;
    }
}