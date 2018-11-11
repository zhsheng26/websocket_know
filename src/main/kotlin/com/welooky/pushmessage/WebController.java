package com.welooky.pushmessage;

import com.welooky.pushmessage.entity.ChatMessage;
import com.welooky.pushmessage.entity.Shout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    private Logger logger = LoggerFactory.getLogger(WebController.class);

    @GetMapping("index")
    public String home() {
        return "index";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }
    @MessageMapping("/macro")
    @SendTo("topic")
    public Shout handleSubscription(Shout incoming) {
        logger.info("/macro Received message: " + incoming.getMessage());
        Shout shout = new Shout();
        shout.setMessage("i am ok");
        return shout;
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}
