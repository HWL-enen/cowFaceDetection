import org.ini4j.Ini;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

// 处理 INI 文件的工具类
class INIHandler {
    private static final String INI_FILE_PATH = "face_recognition.ini";
    public static Ini ini;

    public INIHandler() {
        try{    this.ini = new Ini(new File(INI_FILE_PATH)); }catch (Exception e) {}

    }

    // 写入单个人脸特征和人员姓名到 INI 文件
    public void writeSingleFaceData(String personName, float[] features) {

        try {

            int sum = 0;
            while (true) {
                if(!ini.containsKey(String.format("%07d",sum))){
                    break;
                }
                sum++;
            }
            StringBuilder sb = new StringBuilder();
            for (float feature : features) {
                sb.append(feature).append(",");
            }
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            ini.put(String.format("%07d",sum), "name", personName);
            ini.put(String.format("%07d",sum), "features", sb.toString());
            ini.store();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 写入多个人脸特征和人员姓名到 INI 文件
    public void writeFaceData(String[] personNames, float[][] features) {
        if (personNames.length != features.length) {
            throw new IllegalArgumentException("The number of person names and features must be the same.");
        }
        try {

            for (int i = 0; i < personNames.length; i++) {
                StringBuilder sb = new StringBuilder();
                for (float feature : features[i]) {
                    sb.append(feature).append(",");
                }
                if (sb.length() > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
                ini.put(personNames[i], "features", sb.toString());
            }
            ini.store();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 从 INI 文件中读取所有人员的特征信息
    public Map<String, float[]> readAllFaceData() {
        Map<String, float[]> faceData = new HashMap<>();


            for (String personName : ini.keySet()) {
                String featureStr = ini.get(personName, "features");
                String[] featureArray = featureStr.split(",");
                float[] features = new float[featureArray.length];
                for (int i = 0; i < featureArray.length; i++) {
                    features[i] = Float.parseFloat(featureArray[i]);
                }
                faceData.put(personName, features);
            }

        return faceData;
    }
}

// 人脸识别类
public class FaceRecognizer {
    private INIHandler iniHandler;
    private static final double SIMILARITY_THRESHOLD = 0.98; // 可根据实际情况调整这个阈值
    public FaceRecognizer() {
        this.iniHandler = new INIHandler();
    }

    // 插入单个人脸数据到 INI 文件
    public void insertSingleFace(String personName, float[] features) {
        iniHandler.writeSingleFaceData(personName, features);
    }

    // 插入多个人脸数据到 INI 文件
    public void insertFaces(String[] personNames, float[][] features) {
        iniHandler.writeFaceData(personNames, features);
    }

    // 识别单个人脸
    public String recognizeSingleFace(float[] features) {
        Map<String, float[]> faceData = iniHandler.readAllFaceData();
        double maxSimilarity = Double.MIN_VALUE;
        String recognizedPerson = null;
        for (Map.Entry<String, float[]> entry : faceData.entrySet()) {
            String personName = entry.getKey();
            float[] storedFeatures = entry.getValue();
            double similarity = calculateCosineSimilarity(features, storedFeatures);
            if (similarity > maxSimilarity) {
                maxSimilarity = similarity;
                recognizedPerson = personName;
            }
        }
        // 添加阈值判断
        if (maxSimilarity > SIMILARITY_THRESHOLD) {
            return recognizedPerson;
        } else {
            return null; // 如果相似度小于等于阈值，返回 null 表示未识别到匹配的人脸
        }
    }

    // 识别多个人脸
    public String[] recognizeFaces(float[][] features) {
        String[] recognizedPersons = new String[features.length];
        for (int i = 0; i < features.length; i++) {
            recognizedPersons[i] = recognizeSingleFace(features[i]);
        }
        return recognizedPersons;
    }

    // 计算两个特征向量之间的余弦相似度
    private double calculateCosineSimilarity(float[] features1, float[] features2) {
        double dotProduct = 0;
        double normFeatures1 = 0;
        double normFeatures2 = 0;
        for (int i = 0; i < features1.length; i++) {
            dotProduct += features1[i] * features2[i];
            normFeatures1 += features1[i] * features1[i];
            normFeatures2 += features2[i] * features2[i];
        }
        normFeatures1 = Math.sqrt(normFeatures1);
        normFeatures2 = Math.sqrt(normFeatures2);
        if (normFeatures1 == 0 || normFeatures2 == 0) {
            return 0;
        }
        return dotProduct / (normFeatures1 * normFeatures2);
    }
}