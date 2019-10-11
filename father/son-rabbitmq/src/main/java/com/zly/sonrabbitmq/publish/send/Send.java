package com.zly.sonrabbitmq.publish.send;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zly.sonrabbitmq.util.ConnectionUtil;

public class Send {

    private final static String EXCHANGE_NAME = "DDDDD_exchange_fanout";

    public static void main(String[] args) throws Exception{
        //获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        //声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        //消息内容
        /*String message = "Hello RabbitMQ";
        channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes());
        System.out.println("[x] Sent '" +message + "'");*/

        // 消息内容
        for (int i = 0; i < 100; i++) {
            // 消息内容
            String message = "" + i;
            channel.basicPublish(EXCHANGE_NAME, "",null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");

            Thread.sleep(i * 10);
        }


        channel.close();
        connection.close();
    }

}
