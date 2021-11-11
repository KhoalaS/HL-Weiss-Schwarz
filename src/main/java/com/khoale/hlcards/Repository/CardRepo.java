package com.khoale.hlcards.Repository;

import com.khoale.hlcards.Entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public interface CardRepo extends JpaRepository<Cards, Integer> {

    @Query(value="SELECT png FROM cards WHERE rarity='SSP' ORDER BY RAND () LIMIT 1", nativeQuery = true)
    String getRandomSSP();


}
