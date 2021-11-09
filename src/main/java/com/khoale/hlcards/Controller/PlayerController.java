package com.khoale.hlcards.Controller;

import com.khoale.hlcards.entity.Cards;
import com.khoale.hlcards.entity.Players;
import com.khoale.hlcards.repository.PlayerRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private PlayerRepo playerRepo;

    public PlayerController(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }

    @GetMapping("")
    @ResponseBody
    public Players getPlayer(@RequestParam String id){
        Integer temp = Integer.parseInt(id);
        return playerRepo.getById(temp);
    }

    @GetMapping("/players/insert")
    public void insertQu(){
        playerRepo.save(new Players("gds@gmail.com","asdad", 1000L));
    }
}
