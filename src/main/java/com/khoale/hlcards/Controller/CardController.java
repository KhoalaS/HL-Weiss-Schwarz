package com.khoale.hlcards.Controller;

import com.khoale.hlcards.Repository.CardRepo;
import com.khoale.hlcards.WebSocket.StompMessage;
import com.khoale.hlcards.WebSocket.StompResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        List<String> val=cardRepo.openBooster(pull_Foil());
        String[] res = val.toArray(new String[0]);
        return new StompResponse(res);
    }

    public String pull_Foil(){

        double prob = Math.random();

        if (prob <= 0.25){
            return "RR";
        }else if (prob <= 0.375){
            double prob_Foil = Math.random()*36;
            if (prob_Foil <= 20){
                return "SR";
            }else if (prob_Foil <= 30){
                return "RRR";
            }else if(prob_Foil <= 35){
                return "SP";
            }else{
                return "SSP";
            }
        }else if (prob <= 0.625){
            return "CR";
        }else{
            return "U";
        }
    }
}
