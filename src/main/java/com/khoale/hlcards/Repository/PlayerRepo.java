package com.khoale.hlcards.Repository;

import com.khoale.hlcards.Entity.Players;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepo extends JpaRepository<Players, Integer> {

}
