package com.khoale.hlcards.Controller;

import com.khoale.hlcards.Entity.Cards;
import com.khoale.hlcards.Repository.CardRepo;
import com.khoale.hlcards.WebSocket.StompMessage;
import com.khoale.hlcards.WebSocket.StompResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@RestController
@RequestMapping("/cards")
public class CardController {

    private CardRepo cardRepo;

    public CardController(CardRepo cardRepo){
        this.cardRepo = cardRepo;
    }

    @MessageMapping("/pull")
    @SendTo("/topic/result")
    public StompResponse testBTN(StompMessage message){
        String val=cardRepo.getRandomSSP();
        return new StompResponse(val);
    }
}
