package com.zly.sonrabbitmq.topic.recv;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.zly.sonrabbitmq.util.ConnectionUtil;

public class Recv {

    private final static String QUEUE_NAME = "ddd_topic_01";

    //private final static String EXCHANGE_NAME = "DDDDD_exchange_fanout";
    private final static String EXCHANGE_NAME = "DDDDD_exchange_topic";

    public static void main(String[] args) throws Exception{

        //获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //绑定队列到交换机
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"ddd.*");

        //同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);

        //定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        //监听队列,手动返回完成
        channel.basicConsume(QUEUE_NAME, false, consumer);

        //获取消息
        while (true) {

            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [Recv] Received '" + message + "'");
            Thread.sleep(10);
            // 返回确认状态，注释掉表示使用自动确认模式
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);

        }


    }



}
