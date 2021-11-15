package com.khoale.hlcards.Controller;

import com.khoale.hlcards.Entity.User;
import com.khoale.hlcards.Repository.UserRepo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
public class UserController {

    private UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("")
    @ResponseBody
    public User getPlayer(@RequestParam String id){
        Integer temp = Integer.parseInt(id);
        return userRepo.getById(temp);
    }

    @GetMapping("/players/insert")
    public void insertQu(){
        userRepo.save(new User("gds@gmail.com","asdad", 1000L));
    }
}
