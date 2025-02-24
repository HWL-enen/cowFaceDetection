import java.time.LocalTime;

public class yoloClient {
    public static double sure;
    public static double faultTolerance;
    public static String modelUrl;
    public static int maxFps;
    public static boolean isPersonDetected;
    public static LocalTime startTime;
    public static LocalTime endTime;

    public static boolean isTimeWithinRange(LocalTime startTime, LocalTime endTime, LocalTime time) {
        if (startTime.isBefore(endTime)) {
            // 正常情况，结束时间晚于开始时间
            return!time.isBefore(startTime) &&!time.isAfter(endTime);
        } else {
            // 跨天情况，结束时间早于开始时间
            return time.isAfter(startTime) || time.isBefore(endTime);
        }
    }
}
