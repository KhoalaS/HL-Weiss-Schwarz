package com.khoale.hlcards.Security;

import com.khoale.hlcards.Entity.User;
import com.khoale.hlcards.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class PlayerUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username).get(0);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new PlayerUserDetails(user);
    }

}