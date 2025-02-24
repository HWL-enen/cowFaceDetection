import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class cpuGpuMem {
    public static boolean osType;//flase linux;true win
    public ProcessBuilder processBuilder;
    public static ProcessBuilder pb ;
    // 获取 CPU 利用率
    public static void getCPUUsage() throws IOException, InterruptedException {
        Thread t = new Thread(()->{
            try{
                String os = System.getProperty("os.name").toLowerCase();

                if (os.contains("win")){
                    osType = true;
                }else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
                    osType = false;
                }
                if (osType) {
                    // Windows 系统
                    pb = new ProcessBuilder("wmic", "cpu", "get", "loadpercentage");
                    Process process = pb.start();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.trim().matches("\\d+")) {
                            Home.cpu = Double.parseDouble(line.trim())/100;

                        }
                    }

                    //-------------------------------------------------
                    pb = new ProcessBuilder("wmic", "OS", "get", "FreePhysicalMemory,TotalVisibleMemorySize", "/VALUE");
                    process = pb.start();
                    reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
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
                        Home.mem =  (double) (totalMemory - freeMemory) / totalMemory ;

                    }

                    //----------------------------------------------------------------------

                    if (isNvidiaGPUAvailable()) {
                        ProcessBuilder pb = new ProcessBuilder("nvidia-smi", "--query-gpu=utilization.gpu", "--format=csv,noheader,nounits");
                        process = pb.start();
                        reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                        if ((line = reader.readLine()) != null) {
                            Home.gpu =  Double.parseDouble(line.trim())/100;
                        }
                    }

                }else{
                    // Linux 或 macOS 系统
                    pb = new ProcessBuilder("top", "-bn1");
                    Process process = pb.start();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.contains("%Cpu(s)")) {
                            int startIndex = line.indexOf(":") + 1;
                            int endIndex = line.indexOf("us,");
                            String cpuUsageStr = line.substring(startIndex, endIndex).trim();
                            Home.cpu =  Double.parseDouble(cpuUsageStr)/100;
                        }
                    }


                    //---------------------------------
                    pb = new ProcessBuilder("free", "-m");
                    process = pb.start();
                    reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
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
                        Home.mem =  (double) (totalMemory - freeMemory) / totalMemory ;
                    }

                    if (isNvidiaGPUAvailable()) {
                        ProcessBuilder pb = new ProcessBuilder("nvidia-smi", "--query-gpu=utilization.gpu", "--format=csv,noheader,nounits");
                        process = pb.start();
                        reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                        if ((line = reader.readLine()) != null) {
                           Home.gpu =  Double.parseDouble(line.trim())/100;
                        }
                    }

                }
            }catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();


    }


    // 检查是否有 NVIDIA GPU 可用
    private static boolean isNvidiaGPUAvailable() throws IOException, InterruptedException {
        pb = new ProcessBuilder("nvidia-smi");
        Process process = pb.start();
        return process.waitFor() == 0;
    }
}
