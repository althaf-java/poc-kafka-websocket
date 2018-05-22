package com.altaf.example.websocket.service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.altaf.example.util.JsonHelper;
import com.altaf.example.websocket.model.Message;
import com.altaf.example.websocket.model.Response;

@Service
public class WebsocketService {

    private static final Logger logger = LoggerFactory.getLogger(WebsocketService.class);

    @Value("${spring.websocket.destination.prefix}")
	private String webSocketDestinationPrefix;
    
    @Value("${spring.websocket.destination.name}")
	private String webSocketDestinationName;
    
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public Response send(@RequestBody Message reqVO) {

        logger.info("get req msg: {}", reqVO.getMessage());

        String message = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now())
                + " Data from Server: " + reqVO.getMessage();

        Response response = new Response(message);
        messagingTemplate.convertAndSend(webSocketDestinationPrefix+webSocketDestinationName, response);

        logger.info("send ws msg: {}", JsonHelper.toJson(response).toString());

        return response;
    }

}
