package com.mycompany.app;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class App {
  private final static String QUEUE_NAME = "hello";

  public static void main(String[] argv) throws Exception {

    String uri = "amqps://xoksfvsb:OsxAoMLf-4tA8vrMDU9mf183dRUK8mNC@jackal.rmq.cloudamqp.com/xoksfvsb";

    ConnectionFactory factory = new ConnectionFactory();
    factory.setUri(uri);

    try (Connection connection = factory.newConnection();
         Channel channel = connection.createChannel()) {
      channel.queueDeclare(QUEUE_NAME, false, false, false, null);
      String message = "Hello World!";
      channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
      System.out.println(" [x] Sent '" + message + "'");
    }
  }
}
