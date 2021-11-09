package com.khoale.hlcards.Controller;

import com.khoale.hlcards.entity.Cards;
import com.khoale.hlcards.repository.CardRepo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cards")
public class CardController {

    private CardRepo cardRepo;

    public CardController(CardRepo cardRepo){
        this.cardRepo = cardRepo;
    }

    public void insertCard(){
        cardRepo.save(new Cards("Guwa","asdas.png","SSR"));
    }
}
