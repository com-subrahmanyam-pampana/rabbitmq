Verify RabbitMQ Status
To check RabbitMQ server information & status, we can use following command,
rabbitmqctl status


# RabbitMQ Management plugin
1.RabbitMQ Management plugin is  a web tool to access RabbitMQ server from browser
2.rabbitmq-plugins enable rabbitmq_management
3.To access RabbitMQ Dashboard,
# From Same System : http://localhost:15672
# From Same Network : http://ip-address:15672 like http://192.168.0.151:15672

You May need to open the port

# Default User Access
Default it creates a user guest with password guest. Unconfigured clients will in general use these credentials. By default, these credentials can only be used when connecting to the RabbitMQ as localhost so you will need to take action before connecting from any other machine.

# Create New User & Assign Permissions
Now we have to create new user and assign administrator permissions,
1.Check current Users
rabbitmqctl list_users
2.Create new user with password 
echo 'hello@123' | rabbitmqctl add_user 'admin'
3.Assign the 'administrator' permissions
rabbitmqctl set_permissions -p "/" "admin" ".*" ".*" ".*"
4.Set the user tag 
rabbitmqctl set_user_tags admin administrator
5.Check current user again
rabbitmqctl list_users
Now with new user you can able access the RabbitMQ Dashboard.

 # Rabbi Mq ports:
RabbitMQ Management console:

PORT 15672 for RabbitMQ version 3.x
PORT 55672 for RabbitMQ pre 3.x
Make sure that the rabbitmq_management plugin is enabled, otherwise you won't be able to access management console on those ports.

PORT 5672 RabbitMQ main port (AMQP)
PORT 5671 TLS-encrypted AMQP (if enabled)
For a cluster of nodes, they must be open to each other on 35197, 4369 and 5672.

For any servers that want to use the message queue, only 5672 (or possibly 5671) is required.
