package org.example;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private final static String EXCHANGE_NAME  = "direct_logs";



    public static void main(String[] argv) throws Exception {

        String[] testArgs1 = {"error", "This is a test error message"};
        String[] testArgs2 = {"info", "This is a test info message"};
        String[] testArgs3 = {"warn", "This is a test warn message"};

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPassword("test");
        factory.setUsername("test");
        factory.setPort(5672);
        try{
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, "direct");
            //fanout is type of exchange


            String severity = getSeverity(testArgs1);
            String message = getMessage(testArgs1);

            channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
            channel.close();
            connection.close();

        }catch(Exception e){

        }

    }

    // Extracts the severity from the test arguments, defaults to "info" if not provided
    private static String getSeverity(String[] argv) {
        return (argv.length > 0) ? argv[0] : "info";
    }

    // Extracts the message from the test arguments, defaults to "Hello World!" if not provided
    private static String getMessage(String[] argv) {
        return (argv.length > 1) ? argv[1] : "Hello World!";
    }
}

