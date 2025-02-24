import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.concurrent.CompletableFuture;

public class mqtt {
    public String broker = String.format("tcp://%s:%s",mqttClient.host,mqttClient.port);
    public String clientId ;
    public String topic = "CowFace";

    public MemoryPersistence persistence = new MemoryPersistence();
    public MqttClient sampleClient;
    // 设置账号和密码
    public String username ;
    public String password ;
    public int isLogin = 0;

    public mqtt(String password, String username) {
        this.password = password;
        this.username = username;
        this.clientId = username;
    }

    public void start(){
        try {
            // 创建MqttClient实例
            sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);


            connOpts.setUserName(username);
            connOpts.setPassword(password.toCharArray());
            connOpts.setConnectionTimeout(11);




            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
                    
                    Main.yololog.put_str("Connecting to broker: " + broker);
                    sampleClient.connect(connOpts);
                    Main.yololog.put_str("Connected");
                } catch (MqttException e) {
                    Main.yololog.put_str("Connection failed: " + e.getMessage());
                    isLogin = 2;
                }
            });
            future.thenAccept(v -> {
                if (sampleClient.isConnected()) {
                    // 连接成功后进行后续操作，如订阅、发布
                    try {
                        isLogin = 1;
                        // 订阅主题
                        sampleClient.subscribe(topic);
                        Main.yololog.put_str("Subscribed to topic: " + topic);

                        // 设置消息回调
                        sampleClient.setCallback(new MqttCallback() {
                            @Override
                            public void connectionLost(Throwable cause) {
                                Main.yololog.put_str("Connection lost: " + cause.getMessage());
                            }

                            @Override
                            public void messageArrived(String topic, MqttMessage message) throws Exception {
                                Main.yololog.put_str("Received message: " + new String(message.getPayload()) + " from topic: " + topic);
                            }

                            @Override
                            public void deliveryComplete(IMqttDeliveryToken token) {
                                // 发布消息时才会用到，这里可忽略
                            }
                        });
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                } else {
                    Main.yololog.put_str("客户机未连接");
                }
            });




        } catch (Exception e){
            isLogin = 2;
            Main.yololog.put_str("Error: " + e.getMessage());
        }
    }
    public void pushMsg(String pubTopic,String msg)  {
        try{
            MqttMessage message = new MqttMessage(msg.getBytes());
            message.setQos(mqttClient.Qos);
            // 发布消息
            sampleClient.publish(pubTopic, message);
            Main.yololog.put_str("Message published");
        }catch (MqttException me){
            Main.yololog.put_str("reason " + me.getReasonCode());
            Main.yololog.put_str("msg " + me.getMessage());
            Main.yololog.put_str("loc " + me.getLocalizedMessage());
            Main.yololog.put_str("cause " + me.getCause());
            Main.yololog.put_str("excep " + me);

        }
    }

    public void close(){
        try{
            sampleClient.close();
        }catch (MqttException me){
            Main.yololog.put_str("reason " + me.getReasonCode());
        }

    }




}
