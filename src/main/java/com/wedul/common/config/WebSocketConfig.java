package com.wedul.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * 메시지 전송을 위한 웹소켓 config 설정 
 * 
 * @author jeongcheol
 *
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
		// 연결을 사용할 end point 등록 
        registry.addEndpoint("/stomp-chat").setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
    	// 전송 
        registry.setApplicationDestinationPrefixes("/publish"); 
        
        // 구독중인 사용자 모두에게 전파 
        registry.enableSimpleBroker("/subscribe"); 
    }	

}
