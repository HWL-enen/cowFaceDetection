import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class jiShiDa {
    public static void main(String[] args) {
        try {
            // 定义参数
            String channelKey = "cb6d2a0fa60842a18f2f7f2f0664cd5f";
            String head = "ceshi";
            String body = "123";

            // 对参数进行 URL 编码，防止出现特殊字符导致请求出错
            String encodedChannelKey = URLEncoder.encode(channelKey, "UTF-8");
            String encodedHead = URLEncoder.encode(head, "UTF-8");
            String encodedBody = URLEncoder.encode(body, "UTF-8");

            // 构建完整的请求 URL
            String urlString = "http://push.ijingniu.cn/send?key=" + encodedChannelKey +
                    "&head=" + encodedHead +
                    "&body=" + encodedBody;

            // 创建 URL 对象
            URL url = new URL(urlString);

            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置请求方法为 GET
            connection.setRequestMethod("GET");

            // 获取响应状态码
            int responseCode = connection.getResponseCode();
            System.out.println("响应状态码: " + responseCode);

            // 读取响应内容
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // 输出响应内容
            System.out.println("响应内容: " + response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}