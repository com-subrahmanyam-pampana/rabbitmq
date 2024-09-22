
 In this one we'll create a Work Queue that will be used to distribute time-consuming tasks among multiple workers.
 Eg tasks:
 images to be resized 
 pdf files to be rendered
process incoming video files by encoding them
# Work Queues (aka: Task Queues):
It is to avoid doing a resource-intensive task immediately and having to wait for it to complete.

# Message Sender(publisher):
1.The publisher will connect to RabbitMQ, send a single message, then exit.
#  consumer (receiver):

We don't have a real-world task, like images to be resized or pdf files to be rendered, so let's fake it by just pretending we're busy - by using the Thread.sleep() function. 