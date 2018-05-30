# Spring Boot Kafka Websocket

This is a sample code for Websocket using SockJs with Kafka, Spring boot and MVC.
This an example to post a message into the kafka topic AND websocket it to the user accessing the application.

#1. Start a Kafka instance at localhost:9092

####  You can start a kafka instance by any method. 

OR

I have provided you two docker methods, you can follow either of them.
####  Docker Kafka instance without GUI.

sudo docker pull spotify/kafka
sudo docker run -p 2181:2181 -p 9092:9092 --env ADVERTISED_HOST=localhost --env ADVERTISED_PORT=9092 spotify/kafka

OR

####  Docker Kafka instance with a GUI.
a.Install Docker
b. http://www.landoop.com/downloads/lenses/ 
c. Enter your email id in this page.
d. Lenses will send you an email with the below content. Run that command and Kafka is ready with the GUI.

sudo docker pull landoop/kafka-lenses-dev
sudo docker run -e ADV_HOST=127.0.0.1 -e EULA="https://dl.lenses.stream/d/?id=<<KEY you got from email>>" --rm -p 3030:3030 -p 9092:9092 -p 2181:2181 -p 8081:8081 -p 9581:9581 -p 9582:9582 -p 9584:9584 -p 9585:9585 landoop/kafka-lenses-dev

d. You can access the kafka topic for this example at http://localhost:3030/#/topics/kafka-local-example-topic
(credtials you would have got in email - admin/admin)

#2. Start the spring boot app
a. Start the spring boot app from IDE 
   OR go to poc-kafka-websocket folder execute mvn spring-boot:run

b. Access http://localhost:8080/ws in browser, click connect button.

#3. Make a post call to add a message in the kafk topic 'kafka-local-example-topic'

Make a post call to http://localhost:8080/message/send , Headers -> content type - application/json, body -> {"message":"messageNew"}
Look at the KafkaController to know how to post the data.

#4. In the browser (http://localhost:8080/ws) you should be able to see the new message 'messageNew'
Repeat step3 or posting more new messages to immediately websocketing it in the browser.
