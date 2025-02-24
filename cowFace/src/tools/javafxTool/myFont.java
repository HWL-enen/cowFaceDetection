import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class myFont {
    public static Font Outfit_Medium = loadFontFromResource("res/ttf/OPPOSans-Medium.ttf", FontWeight.NORMAL, FontPosture.REGULAR, 24);
    public static Font Outfit_Regular = loadFontFromResource("res/ttf/OPPOSans-Regular.ttf", FontWeight.NORMAL, FontPosture.REGULAR, 18);
    public static Font Outfit_Regular_9 = loadFontFromResource("res/ttf/OPPOSans-Regular.ttf", FontWeight.NORMAL, FontPosture.REGULAR, 9);
    public static Font Outfit_Bold_32 = loadFontFromResource("res/ttf/OPPOSans-Bold.ttf", FontWeight.BOLD, FontPosture.REGULAR, 32);
    public static Font Outfit_Medium_14 = loadFontFromResource("res/ttf/OPPOSans-Medium.ttf", FontWeight.NORMAL, FontPosture.REGULAR, 14);
    public static Font Outfit_Bold_18 = loadFontFromResource("res/ttf/OPPOSans-Bold.ttf", FontWeight.BOLD, FontPosture.REGULAR, 18);
    public static Font Outfit_Bold_32_num = loadFontFromResource("res/ttf/Outfit-Bold.ttf", FontWeight.BOLD, FontPosture.REGULAR, 32);

    private static Font loadFontFromResource(String fontPath, FontWeight weight, FontPosture posture, double size) {
        try {
            // 获取字体文件的输入流
            var inputStream = Main.class.getResourceAsStream(fontPath);
            // 加载字体
            Font font = Font.loadFont(inputStream, size);
            return Font.font(font.getFamily(), weight, posture, size);
        } catch (Exception e) {
            // 若加载失败，使用默认字体
            System.out.println("Failed to load font from resource: " + fontPath);
            return Font.font("Arial", weight, posture, size);
        }
    }
}
