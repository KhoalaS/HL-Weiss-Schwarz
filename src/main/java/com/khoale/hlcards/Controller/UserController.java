package com.khoale.hlcards.Controller;

import com.khoale.hlcards.Entity.Cards;
import com.khoale.hlcards.Entity.User;
import com.khoale.hlcards.Repository.CardRepo;
import com.khoale.hlcards.Repository.UserRepo;
import com.khoale.hlcards.Security.PlayerUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CardRepo cardRepo;

    @GetMapping("")
    public String getHome(){
        return "user";
    }


    @GetMapping("/getInv")
    public ResponseEntity<List<Cards>> userWindow(Model model, @AuthenticationPrincipal PlayerUserDetails p_user){
        if(p_user!=null){
            User user = userRepo.findByEmail(p_user.getUsername()).get(0);
            List<Integer> l = cardRepo.getUsersCards(user.getUid());
            return ResponseEntity.ok(cardRepo.findAllById(l));
        }
        return ResponseEntity.notFound().build();
    }
}
