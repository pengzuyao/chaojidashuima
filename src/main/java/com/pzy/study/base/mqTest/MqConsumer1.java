package com.pzy.study.base.mqTest;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-06-16
 */
public class MqConsumer1 {

        public static void getMessage() throws  Exception{
            Connection connection = MqConnectionUtil.getConnection();
            Channel channel = connection.createChannel();
            DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println(new String(body , "utf-8"));
                    //System.out.println("消息消费成功");
                    //channel.basicAck(envelope.getDeliveryTag() ,false);
                }

            };
            //开始消费
           channel.basicConsume(MqConnectionUtil.QUEUE_NAME1 ,true , defaultConsumer);
        }

    public static void main(String[] args) throws  Exception{
        getMessage();
    }
}
