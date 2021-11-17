package com.khoale.hlcards.Controller;

import com.khoale.hlcards.Entity.User;
import com.khoale.hlcards.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class AppController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {

        if(!userRepo.findByEmail(user.getEmail()).isEmpty()){
            return "register_fail";
        }else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(user.getPw());
            user.setPw(encodedPassword);

            userRepo.save(user);
            return "register_success";
        }

    }
}
