import ai.onnxruntime.*;

import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Map;

public class change {
    public static void main(String[] args) throws OrtException {

            // 步骤 1: 创建 OrtEnvironment 实例
            // OrtEnvironment 是 ONNX Runtime 的全局环境，在使用 ONNX Runtime 进行推理之前必须先创建它
            OrtEnvironment env = OrtEnvironment.getEnvironment();

            // 步骤 2: 创建 OrtSession.SessionOptions 实例
            // 这个实例用于配置 ONNX 会话的选项，例如是否启用 GPU 加速等。这里使用默认配置
            OrtSession.SessionOptions options = new OrtSession.SessionOptions();

            // 步骤 3: 加载 ONNX 模型
            // 通过 OrtEnvironment 和 OrtSession.SessionOptions 创建一个 OrtSession 实例，加载指定路径的 ONNX 模型
            OrtSession session = env.createSession("res/model/yolo11n.onnx", options);

            // 步骤 4: 准备输入数据
            // 这里假设模型输入是一个形状为 [1, 3, 640, 640] 的浮点张量
            int batchSize = 1;
            int channels = 3;
            int height = 640;
            int width = 640;
            long[] inputShape = {batchSize, channels, height, width};
            FloatBuffer inputBuffer = FloatBuffer.allocate((int) (batchSize * channels * height * width));
            // 这里可以根据实际情况填充输入数据，例如将图像数据转换为浮点数并填充到 buffer 中
            // 为了简单起见，这里填充全 0
            for (int i = 0; i < inputBuffer.capacity(); i++) {
                inputBuffer.put(0.0f);
            }
            inputBuffer.rewind();
            OnnxTensor inputTensor = OnnxTensor.createTensor(env, inputBuffer, inputShape);

            // 步骤 5: 准备输入映射
            // 将输入张量与模型的输入名称关联起来，这里假设模型的输入名称为 "input"
            Map<String, OnnxTensor> inputs = new HashMap<>();
            inputs.put("images", inputTensor);

            // 步骤 6: 创建运行选项
            // 运行选项用于配置推理过程中的一些参数，这里使用默认配置
            OrtSession.RunOptions runOptions = new OrtSession.RunOptions();

            // 步骤 7: 运行模型推理
            // 调用 OrtSession 的 run 方法进行推理，传入运行选项和输入映射，得到推理结果
            OrtSession.Result result = session.run(inputs, runOptions);

            // 步骤 8: 获取模型输出
            // 假设模型只有一个输出，获取第一个输出张量
            OnnxTensor outputTensor = (OnnxTensor) result.get(0);

            // 步骤 9: 获取输出张量的值并转换为 Java 数组
            // 这里假设输出是一个三维的浮点数组
            float[][][] outputArray = (float[][][]) outputTensor.getValue();

            // 步骤 10: 打印输出维度
            System.out.println("Output dimensions: " + outputArray.length + " x " + outputArray[0].length + " x " + outputArray[0][0].length);

            // 步骤 11: 释放资源
            // 推理完成后，需要释放结果、会话和环境的资源
            result.close();
            session.close();
            env.close();

}
}
