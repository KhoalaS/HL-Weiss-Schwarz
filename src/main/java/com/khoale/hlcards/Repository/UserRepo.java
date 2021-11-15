package com.khoale.hlcards.Repository;

import com.khoale.hlcards.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserRepo extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM User WHERE email = :email", nativeQuery = true)
    public List<User> findByEmail(@Param("email") String email);
}
