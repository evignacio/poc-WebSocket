package com.example.websocketserver.controller;

import com.example.websocketserver.dto.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.Objects;

@Controller
public class BrokerController {

    @SendTo("/topico/broker")
    @MessageMapping("/initials/infos")
    public Message sendInitialsInfos(@Payload Message message, SimpMessageHeaderAccessor simpMessageHeaderAccessor) {
        Objects.requireNonNull(simpMessageHeaderAccessor.getSessionAttributes()).put("userId", message.getIdUser());
        Objects.requireNonNull(simpMessageHeaderAccessor.getSessionAttributes()).put("entryTime", LocalDateTime.now());
        return message;
    }

    @SendTo("/topico/broker")
    @MessageMapping("/send")
    public Message sendMessage(@Payload Message message) {
        return message;
    }
}
