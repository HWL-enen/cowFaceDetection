import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttSubscriber {
    public static void main(String[] args) {
        String broker = "tcp://123.57.2.28:1883";
        String clientId = "HZ001";
        String topic = "CowFace";
        int qos = 1;
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            // 创建MqttClient实例
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);

            // 设置账号和密码
            String username = "HZ001";
            String password = "HZ001";
            connOpts.setUserName(username);
            connOpts.setPassword(password.toCharArray());

            // 连接到MQTT代理
            System.out.println("Connecting to broker: " + broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");
            // 订阅主题
            sampleClient.subscribe(topic);
            System.out.println("Subscribed to topic: " + topic);

            // 设置消息回调
            sampleClient.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    System.out.println("Connection lost: " + cause.getMessage());
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    System.out.println("Received message: " + new String(message.getPayload()) + " from topic: " + topic);
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    // 发布消息时才会用到，这里可忽略
                }
            });

        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }
}