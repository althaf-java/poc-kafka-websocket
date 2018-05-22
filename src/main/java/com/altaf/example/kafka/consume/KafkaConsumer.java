package com.altaf.example.kafka.consume;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.altaf.example.util.JsonHelper;
import com.altaf.example.websocket.model.Message;
import com.altaf.example.websocket.model.Response;
import com.altaf.example.websocket.service.WebsocketService;

@Component
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private WebsocketService websocketService;
    
    private CountDownLatch latch = new CountDownLatch(1);
   
    @KafkaListener(topics = "${spring.kafka.topics}")
    public void consumer(String message) {

        logger.info("consumer topic string get : {}", message);

        Message messageReq = new Message();
        messageReq.setMessage(message);

        logger.info("send websocket request : {}", JsonHelper.toJson(messageReq).toString());

        Response response = websocketService.send(messageReq);

        logger.info("send websocket response : {}", JsonHelper.toJson(response).toString());

        latch.countDown();
	}

	public CountDownLatch getLatch() {
		return latch;
	}

}
