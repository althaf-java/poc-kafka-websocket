package com.altaf.example.websocket.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Value("${spring.websocket.destination.prefix}")
	private String webSocketDestinationPrefix;
	
	@Value("${spring.websocket.stomp.endpoint}")
	private String webSocketStompEndpoint;
	
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {

        stompEndpointRegistry.addEndpoint(webSocketStompEndpoint).withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        registry.enableSimpleBroker(webSocketDestinationPrefix);
        //@TODO: This is commented out since the application base path is not known now.
        //registry.setApplicationDestinationPrefixes("/app");
    }

}
