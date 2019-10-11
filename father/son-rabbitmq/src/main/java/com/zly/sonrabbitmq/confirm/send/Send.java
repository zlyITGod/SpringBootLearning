package com.zly.sonrabbitmq.confirm.send;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zly.sonrabbitmq.util.ConnectionUtil;

public class Send {

    private final static String QUEUE_NAME = "ddddd_test_02";

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();

        // 声明（创建）队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 消息内容
        for (int i = 0; i < 100; i++) {
            // 消息内容
            String message = "" + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");

            Thread.sleep(i * 10);
        }

        //关闭通道和连接
        channel.close();
        connection.close();
    }


}
