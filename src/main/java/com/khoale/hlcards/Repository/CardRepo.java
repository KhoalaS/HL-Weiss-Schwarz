package com.khoale.hlcards.Repository;

import com.khoale.hlcards.Entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepo extends JpaRepository<Cards, Integer> {

    @Query(value="SELECT png FROM cards WHERE rarity='SSP' ORDER BY RANDOM () LIMIT 1", nativeQuery = true)
    String getRandom_SSP();

    @Query(value="SELECT png FROM cards WHERE rarity='C' ORDER BY RANDOM () LIMIT 4", nativeQuery = true)
    List<String> getRandom_C();

    @Query(value="SELECT png FROM cards WHERE rarity='U' ORDER BY RANDOM () LIMIT 2", nativeQuery = true)
    List<String> getRandom_U();

    @Query(value="SELECT * FROM cards WHERE rarity='R' ORDER BY RANDOM () LIMIT 1", nativeQuery = true)
    List<Object[]> getRandom_R();

    @Query(value="SELECT png FROM cards WHERE rarity=rar ORDER BY RANDOM () LIMIT 1", nativeQuery = true)
    String getRandom_Foil(
            @Param("rar") String rar
    );

    @Query(value =
            "(SELECT * FROM cards WHERE rarity='C' ORDER BY RANDOM () LIMIT 3) UNION ALL" +
            "(SELECT * FROM cards WHERE rarity='U' ORDER BY RANDOM () LIMIT 2) UNION ALL" +
            "(SELECT * FROM cards WHERE rarity='R' ORDER BY RANDOM () LIMIT 1) UNION ALL" +
            "(SELECT * FROM cards WHERE rarity='CC' ORDER BY RANDOM () LIMIT 1) UNION ALL" +
            "(SELECT * FROM cards WHERE rarity= :rar ORDER BY RANDOM () LIMIT 1)", nativeQuery = true
    )
    List<Cards> openBooster(
            @Param("rar") String rar
    );

    @Query(value ="SELECT card_id FROM player_deck WHERE user_id = :uid" ,nativeQuery = true)
    List<Integer> getUsersCards(
            @Param("uid") Integer uid
    );



}
