package com.mycompany.app;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

public class App {
  private final static String QUEUE_NAME = "hello";

  public static void main(String[] argv) throws Exception {
    String uri = "amqps://xoksfvsb:OsxAoMLf-4tA8vrMDU9mf183dRUK8mNC@jackal.rmq.cloudamqp.com/xoksfvsb";

    ConnectionFactory factory = new ConnectionFactory();
    factory.setUri(uri);

    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
      String message = new String(delivery.getBody(), "UTF-8");
      System.out.println(" [x] Received '" + message + "'");
    };
    channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
  }
}
