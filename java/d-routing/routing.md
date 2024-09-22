we're going to make it possible to subscribe only to a subset of the messages. For example, we will be able to direct only critical error messages to the log file (to save disk space), while still being able to print all of the log messages on the console.


The consumer listens to the direct_logs exchange for messages with severity levels of "info", "warning", or "error".

When a message is published with one of these routing keys, the consumer receives it and prints the message along with the severity (routing key).