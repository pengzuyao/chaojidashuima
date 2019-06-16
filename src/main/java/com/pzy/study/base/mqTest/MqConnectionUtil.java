package com.pzy.study.base.mqTest;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-06-16
 */
public class MqConnectionUtil {

    public final static String QUEUE_NAME1 = "firstQueue";
    public final static String QUEUE_NAME2 = "secondQueue";
    public final static String QUEUE_NAME3= "thirdQueue";
    public final static String EXCHANGE = "defaultExchange";
    public final static String EXCHANGE2 = "defaultExchange2";


    public static Connection  getConnection(){
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.1.105");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setVirtualHost("/");
        try {
            return connectionFactory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }
}
