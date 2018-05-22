package com.altaf.example.kafka.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.altaf.example.websocket.model.Message;

@RestController
public class KafkaController {

    private KafkaTemplate<String, Object> kafkaTemplate;
    
    @Value("${spring.kafka.topics}")
    private String kafkaTopics;

    @Autowired
    KafkaController(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/message/send")
    public String sendMessage(@RequestBody Message message) {
        kafkaTemplate.send(kafkaTopics, message.getMessage());
        return message.getMessage();
    }


}
