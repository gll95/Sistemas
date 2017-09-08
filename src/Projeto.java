import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;
import java.*;

public class Projeto {

  private final static String QUEUE_NAME = "hello";
  
  
  public Projeto() throws IOException, TimeoutException {
	 /*new Thread(){
		 @Override
		 public void run() {
			 try {
				ler();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	 }.start();*/
	 //System.out.println("hjvjh");
	 ler();
	  escrever();
  }

  public void ler() throws IOException, TimeoutException {
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
	  
  }
  
  public void escrever() throws IOException, TimeoutException {
	  ConnectionFactory factory = new ConnectionFactory();
	  factory.setHost("crane.rmq.cloudamqp.com");
	  factory.setUsername("amnqwcpe");
	  factory.setVirtualHost("amnqwcpe");
	  factory.setPassword("_46aAmifOIjj-2umcCtC3lRfRl8u8DgO");
	  Connection connection = factory.newConnection();
	  Channel channel = connection.createChannel();
	  //channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	  System.out.println("Destinatario: ");
	  String message = "";
	  Scanner sc = new Scanner(System.in);
	  message = sc.nextLine();
	  String dest = message;
	  System.out.println("Mensagem: ");
	  for(int i=0;i<10;i++) {		  
		  message = sc.nextLine();
		  channel.basicPublish("", dest, null, message.getBytes("UTF-8"));
		  System.out.println(" [x] Sent '" + message + "'");
	  }  

	    channel.close();
	    connection.close();
  }
  
  public static void main(String args[]) throws IOException, TimeoutException {
	  new Projeto();
	  }
}