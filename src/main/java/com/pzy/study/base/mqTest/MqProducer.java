package com.pzy.study.base.mqTest;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-06-16
 */
public class MqProducer {

    public static void main(String[] args)throws Exception {
        Connection connection = MqConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDelete(MqConnectionUtil.EXCHANGE);
        channel.exchangeDeclare(MqConnectionUtil.EXCHANGE , BuiltinExchangeType.TOPIC);
        //channel.queueDeclare(MqConnectionUtil.QUEUE_NAME1 ,true ,false ,false ,null);
        //channel.queueDeclare(MqConnectionUtil.QUEUE_NAME2 ,true ,false ,false ,null);
        //channel.queueDeclare(MqConnectionUtil.QUEUE_NAME3 ,true ,false ,false ,null);
        channel.queueBind(MqConnectionUtil.QUEUE_NAME1 , MqConnectionUtil.EXCHANGE ,"debug.*.B");
        channel.queueBind(MqConnectionUtil.QUEUE_NAME2 , MqConnectionUtil.EXCHANGE ,"error.#");
        channel.queueBind(MqConnectionUtil.QUEUE_NAME3 , MqConnectionUtil.EXCHANGE ,"*.email.*");
        String[] string1 = new String[]{"error" ,"info" ,"debug"};
        String[] string2= new String[]{"user" ,"order" ,"email"};
        String[] string3 = new String[]{"A" ,"B" ,"C"};

        for (int i = 0; i < string1.length; i++) {
            for (int j = 0; j < string2.length; j++) {
                for (int k = 0; k < string3.length; k++) {
                    String message = string1[i]+ "." + string2[j] +"."+ string3[k];
                    channel.basicPublish(MqConnectionUtil.EXCHANGE  ,message,null , message.getBytes());
                }
            }
        }

        channel.close();
        connection.close();

    }
}
