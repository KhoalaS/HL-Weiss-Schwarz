package com.khoale.hlcards.Controller;

import com.khoale.hlcards.Entity.Cards;
import com.khoale.hlcards.Entity.User;
import com.khoale.hlcards.Repository.CardRepo;
import com.khoale.hlcards.Repository.UserRepo;
import com.khoale.hlcards.Security.PlayerUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@RestController
public class CardController {

    @Autowired
    private CardRepo cardRepo;
    @Autowired
    private UserRepo userRepo;


    @GetMapping("/cards")
    public List<Cards>all(){
        return cardRepo.findAll();
    }

    @GetMapping("/cards/{id}")
    public Optional<Cards> singleCard(@PathVariable Integer id){
        return cardRepo.findById(id);
    }

    @GetMapping("/booster")
    public ResponseEntity<List<Cards>> booster(@AuthenticationPrincipal PlayerUserDetails p_user, Model model){
        if(p_user != null){
            List<Cards> c = cardRepo.openBooster(pull_Foil());
            User user = userRepo.findByEmail(p_user.getUsername()).get(0);
            user.addCards(c);
            userRepo.save(user);
            return ResponseEntity.ok(c);
        }
        return ResponseEntity.notFound().build();
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
