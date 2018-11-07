package com.welooky.pushmessage;

import com.welooky.pushmessage.entity.Shout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    private Logger logger = LoggerFactory.getLogger(WebController.class);

    @GetMapping("index")
    public String home() {
        return "index";
    }

    //处理/app/macro/到达的消息
    @MessageMapping("macro")
    public void handleShout(Shout incoming) {
        logger.info("/macro Received message: " + incoming.getMessage());
    }

    //用这个方法来处理对“/app/hello”目的地的订阅
    @SubscribeMapping("/hello")
    public Shout handleSubscription(Shout incoming) {
        logger.info("/hello Received message: " + incoming.getMessage());
        Shout shout = new Shout();
        shout.setMessage("i am ok");
        return shout;
    }
}
