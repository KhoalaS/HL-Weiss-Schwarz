package com.khoale.hlcards.Repository;

import com.khoale.hlcards.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    @Query(value = "SELECT u FROM user u WHERE u.email = :email", nativeQuery = true)
    public User findByEmail(String email);
}
