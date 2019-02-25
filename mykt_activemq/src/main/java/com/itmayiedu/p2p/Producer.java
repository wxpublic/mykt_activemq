package com.itmayiedu.p2p;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @version 1.0
 * @Description: 点对点通讯方式---生产者
 * @author: ChenRuiQing.
 * Create Time:  2019-02-25 下午 3:14
 */
public class Producer {
    public static void main(String[] args) throws JMSException {
        // ConnectionFactory ：连接工厂，JMS 用它创建连接
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD, "tcp://127.0.0.1:61616");
        // JMS 客户端到JMS Provider 的连接
        Connection connection = connectionFactory.createConnection();
        connection.start();
        // Session： 一个发送或接收消息的线程
        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
        // Destination ：消息的目的地;消息发送给谁.
        // 获取session注意参数值my-queue是Query的名字
        Destination destination = session.createQueue("my-queue");
        // MessageProducer：消息生产者
        MessageProducer producer = session.createProducer(destination);
        // 设置不持久化
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        // 发送一条消息
        for (int i = 1; i <= 5; i++) {
            System.out.println("我是生产者"+i);
            sendMsg(session, producer, "我是生产者"+i);
        }
        System.out.println("生产者发送消息完毕!!!");
    }

    /**
     * 在指定的会话上，通过指定的消息生产者发出一条消息
     *
     * @param session
     *            消息会话
     * @param producer
     *            消息生产者
     */
    public static void sendMsg(Session session, MessageProducer producer, String i) throws JMSException {
        // 创建一条文本消息
        TextMessage message = session.createTextMessage("Hello ActiveMQ！" + i);
        // 通过消息生产者发出消息
        producer.send(message);
    }

}
