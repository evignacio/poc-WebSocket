package com.example.websocketserver.infrastructure;

import com.example.websocketserver.dto.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.AbstractSubProtocolEvent;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@Component
public class WebSocketEventListener {

    @Autowired
    private SimpMessageSendingOperations sendingOperations;

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketEventListener.class);

    @EventListener
    public void connectHandler(SessionConnectedEvent event) {
       WebSocketEventListener.LOGGER.info("A new user was connected");
    }

    @EventListener
    public void disconnectHandler(SessionDisconnectEvent event) {
        Long userId = Long.valueOf(Objects.requireNonNull(this.getSession(event)).get("userId").toString());
        WebSocketEventListener.LOGGER.info(String.format("User id: %d was disconnected", userId));
        this.sendingOperations.convertAndSend("/topic/broker", new Message(userId, LocalDateTime.now()));
    }

    private Map<String, Object> getSession(AbstractSubProtocolEvent event) {
        StompHeaderAccessor header = StompHeaderAccessor.wrap(event.getMessage());
        return header.getSessionAttributes();
    }
}
