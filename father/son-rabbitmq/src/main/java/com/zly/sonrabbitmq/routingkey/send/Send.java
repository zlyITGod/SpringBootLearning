package com.zly.sonrabbitmq.routingkey.send;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zly.sonrabbitmq.util.ConnectionUtil;

public class Send {

    private final static String EXCHANGE_NAME = "DDDDD_exchange_direct";

    public static void main(String[] args) throws Exception{
        //获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        //声明exchange  交换机种类为direct
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        //消息内容  根据交换机的名字发到相对应的队列里
        String message = "删除商品";
        channel.basicPublish(EXCHANGE_NAME,"insert",null,message.getBytes());
        System.out.println("[x] Sent '" +message + "'");

        // 消息内容
        /*for (int i = 0; i < 100; i++) {
            // 消息内容
            String message = "" + i;
            channel.basicPublish(EXCHANGE_NAME, "",null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");

            Thread.sleep(i * 10);
        }*/


        channel.close();
        connection.close();
    }

}
