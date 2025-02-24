public class myColor {


    public myColor(String textid) {
        textidColor = textid;
    }

    public static String textidColor;
    public static String backgroundColor;
    public static String rollBackColor;
    public static String offRollColor;
    public static String chooseGroundColor;
    public static String OnchooseGroundColor;
    public static String OnchooseGroundColor1;
    public static String homeBackColor;

    public static String invertColor(String colorCode) {
        // 去除颜色代码中的 # 符号
        colorCode = colorCode.startsWith("#")? colorCode.substring(1) : colorCode;
        // 将颜色代码拆分为 RGB 分量
        int r = Integer.parseInt(colorCode.substring(0, 2), 16);
        int g = Integer.parseInt(colorCode.substring(2, 4), 16);
        int b = Integer.parseInt(colorCode.substring(4, 6), 16);
        // 计算反色的 RGB 分量
        int invertedR = 255 - r;
        int invertedG = 255 - g;
        int invertedB = 255 - b;
        // 将反色的 RGB 分量转换为十六进制字符串
        String invertedColorCode = String.format("#%02X%02X%02X", invertedR, invertedG, invertedB);
        return invertedColorCode;
    }
}
