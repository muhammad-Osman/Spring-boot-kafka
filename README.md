to start kafka

1. we need to start zookeeper
cd into kafka folder and run the command
# Run the following commands in order to start all services in the correct order:
## if it doesn't work on CMD please open window powershell

## .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

# Open another terminal session and run:
## Start the Kafka broker service

## .\bin\windows\kafka-server-start.bat .\config\server.properties

# So before you can write your first events, you must create a topic. 
# Open another terminal session and run:

## .\bin\windows\kafka-topics.bat --create --topic topic-example --bootstrap-server localhost:9092

# So we need to write events in kafka topics in order to do this we need kafka producer

## .\bin\windows\kafka-console-producer.bat --topic topic-example --bootstrap-server localhost:9092

# To read the event 

## .\bin\windows\kafka-console-consumer.bat --topic topic-example --bootstrap-server localhost:9092