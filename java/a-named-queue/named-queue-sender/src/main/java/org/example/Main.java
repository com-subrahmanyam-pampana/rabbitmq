package org.example;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private final static String QUEUE_NAME = "hello";



    public static void main(String[] argv) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("your-ip-address-only");
        factory.setPassword("your-password");
        factory.setUsername("your-user-name");
        factory.setPort(5672);
        ////RabbitMQ main port (AMQP)

        try{
            String message="Hello Rabbit!Let eat some food";
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
            channel.close();
            connection.close();

        }catch(Exception e){
           System.out.println(e);
        }

    }
}

