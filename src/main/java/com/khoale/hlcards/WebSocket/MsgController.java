package com.khoale.hlcards.WebSocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class MsgController {
    /*
    @MessageMapping("/hello")
    @SendTo("/topic/response")
    public StompResponse response(StompMessage message) throws Exception{
        Thread.sleep(1000);
        HtmlUtils.htmlEscape(message.getName())
        return new StompResponse("Hello!");
    }*/
}
