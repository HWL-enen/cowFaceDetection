import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;

public class Yololog {
    public LinkedHashMap<String, LocalDateTime> storage;
    private static final String LOG_FILE_NAME = "decetion.log";

    public Yololog() {
        this.storage = new LinkedHashMap<>();
    }

    public void put(String key, LocalDateTime time) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE_NAME, true))) {
            cleanUpExpiredEntries();
            if (!storage.containsKey(key)) {
                storage.put(key, time);
            }
            writer.println(key + ": " + time);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void put_str(String key) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE_NAME, true))) {
            cleanUpExpiredEntries();
            if (!storage.containsKey(key)) {
                storage.put(key, LocalDateTime.now());
            }
            writer.println(key + ": " + LocalDateTime.now());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, LocalDateTime> getAllValidEntries() {
        cleanUpExpiredEntries();
        return new LinkedHashMap<>(storage);
    }

    private void cleanUpExpiredEntries() {
        LocalDateTime now = LocalDateTime.now();

            storage.entrySet().removeIf(entry -> {
                LocalDateTime entryTime = entry.getValue();
                long minutesDiff = ChronoUnit.MINUTES.between(entryTime, now);
                if (minutesDiff > 5) {
                    // 记录过期消息到日志文件
                    return true;
                }
                return false;
            });

    }

}