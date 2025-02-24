import org.ini4j.Ini;


import java.io.File;
import java.io.IOException;


    public class initest {
        public static void main(String[] args) {
            try {
                Ini ini = new Ini();
                // 读取 INI 文件
                ini.load(new File("setting.ini"));


                // 获取配置项的值
                String value = ini.get("mqttServer", "ipv4");
                System.out.println("Database host: " + value);


                // 设置配置项的值
                ini.put("Database", "host", "jntm");


                // 写入 INI 文件
                ini.store(new File("setting.ini"));


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

