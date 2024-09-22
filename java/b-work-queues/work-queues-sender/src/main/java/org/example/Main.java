package org.example;


import com.rabbitmq.client.*;

public class Main {
    private final static String TASK_QUEUE_NAME  = "task_queue";



    public static void main(String[] argv) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("your-ip-only");
        factory.setPassword("");
        factory.setUsername("");
        factory.setPort(5672);
        String[] myArgv=new String[]{"hi","1","2","3"};
        String message = String.join(" ", myArgv);
        try{
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
            //channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            channel.basicPublish("", TASK_QUEUE_NAME,
                    MessageProperties.PERSISTENT_TEXT_PLAIN,
                    message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
            channel.close();
            connection.close();

        }catch(Exception e){

        }

    }
}

