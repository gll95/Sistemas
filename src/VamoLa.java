import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class VamoLa {
  
  public static void main(String args[]) throws IOException, TimeoutException {
	  
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
	    System.out.print("user: ");
	    Scanner scan = new Scanner(System.in);
	    String fila = scan.nextLine();
	    channel.queueDeclare(fila, false, false, false, null);
	    Consumer consumer = new DefaultConsumer(channel) {
	      @Override
	      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
	          throws IOException {
	        String message = new String(body, "UTF-8");
	        System.out.println(" [x] Received '" + message + "'");
	      }
	    };
	    
	    channel.basicConsume(fila, true, consumer);	    
	    
	    //Fim da leitura
	    
	    //Inicio da escrita
		  System.out.println("Destinatario: ");
		  String message = "";
		  Scanner sc = new Scanner(System.in);
		  message = sc.nextLine();
		  String dest = message;
		  System.out.println("Mensagem: ");
		  message = sc.nextLine();
		  while(!message.equals("exit")) {		  
			  channel.basicPublish("", dest, null, message.getBytes("UTF-8"));
			  System.out.println(" [x] Sent '" + message + "'");
			  System.out.println("Mensagem: ");
			  message = sc.nextLine();
		  }  

		    channel.close();
		    connection.close();
	  
	  }
}