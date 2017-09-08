import com.rabbitmq.client.*;

import java.io.IOException;

public class Recv {

  private final static String QUEUE_NAME = "hello";

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("crane.rmq.cloudamqp.com");
    factory.setUsername("amnqwcpe");
    factory.setVirtualHost("amnqwcpe");
    factory.setPassword("_46aAmifOIjj-2umcCtC3lRfRl8u8DgO");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
    //Ao criar uma fila com nome já criado, ele nao cria nada
    //Mas pode colocar para o send e reciever realizar a tarefa
    //Para ou o send, ou o reciever criar, tanto faz
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

    Consumer consumer = new DefaultConsumer(channel) {
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
          throws IOException {
        String message = new String(body, "UTF-8");
        System.out.println(" [x] Received '" + message + "'");
      }
    };
    channel.basicConsume(QUEUE_NAME, true, consumer);
  }
}