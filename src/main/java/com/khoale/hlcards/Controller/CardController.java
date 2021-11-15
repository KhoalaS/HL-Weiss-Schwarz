package com.khoale.hlcards.Controller;

import com.khoale.hlcards.Entity.Cards;
import com.khoale.hlcards.Repository.CardRepo;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardController {

    private CardRepo cardRepo;

    public CardController(CardRepo cardRepo){
        this.cardRepo = cardRepo;
    }

    @GetMapping("/cards")
    public List<Cards>all(){
        return cardRepo.findAll();
    }

    @GetMapping("/booster")
    public Cards booster( ){
        List<Object[]> cardsList= cardRepo.getRandom_R();
        Object[] card = cardsList.get(0);
        return new Cards((Integer) card[0],(String) card[1],(String) card[2],(String) card[3]);
    }

    @GetMapping("/cards/{id}")
    public Cards replaceCard(@RequestBody Cards newCard, @PathVariable Integer id){
        return cardRepo.findById(id)
                .map(card -> {
                    card.setIdol(newCard.getIdol());
                    card.setPng(newCard.getPng());
                    card.setRarity(newCard.getRarity());
                    return cardRepo.save(card);
                })
                .orElseGet(()->{
                    newCard.setId(id);
                    return cardRepo.save(newCard);
                });
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
