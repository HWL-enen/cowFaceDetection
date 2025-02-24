import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;

public class RecentFiveMinutesStorage {
    private final LinkedHashMap<String, LocalDateTime> storage;
    private static final String LOG_FILE_NAME = "decetion.log";

    public RecentFiveMinutesStorage() {
        this.storage = new LinkedHashMap<>();
    }

    public void put(String key, LocalDateTime time) {
        cleanUpExpiredEntries();
        storage.put(key, time);
    }

    public Map<String, LocalDateTime> getAllValidEntries() {
        cleanUpExpiredEntries();
        return new LinkedHashMap<>(storage);
    }

    private void cleanUpExpiredEntries() {
        LocalDateTime now = LocalDateTime.now();
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE_NAME, true))) {
            storage.entrySet().removeIf(entry -> {
                LocalDateTime entryTime = entry.getValue();
                long minutesDiff = ChronoUnit.MINUTES.between(entryTime, now);
                if (minutesDiff > 5) {
                    // 记录过期消息到日志文件
                    writer.println(entry.getKey() + ": " + entry.getValue());
                    return true;
                }
                return false;
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        RecentFiveMinutesStorage storage = new RecentFiveMinutesStorage();

        // 添加一些示例数据
        LocalDateTime oldTime = LocalDateTime.now().minusMinutes(6);
        storage.put("key0", oldTime);

        storage.put("key1", LocalDateTime.now());
        Thread.sleep(1000);
        storage.put("key2", LocalDateTime.now());
        Thread.sleep(5000);
        storage.put("key3", LocalDateTime.now());

        // 获取所有有效的键值对
        Map<String, LocalDateTime> validEntries = storage.getAllValidEntries();
        for (Map.Entry<String, LocalDateTime> entry : validEntries.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}