package com.khoale.hlcards.Repository;

import com.khoale.hlcards.Entity.Cards;
import com.khoale.hlcards.Logic.PackOpening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public interface CardRepo extends JpaRepository<Cards, Integer> {

    @Query(value="SELECT png FROM cards WHERE rarity='SSP' ORDER BY RAND () LIMIT 1", nativeQuery = true)
    String getRandom_SSP();

    @Query(value="SELECT png FROM cards WHERE rarity='C' ORDER BY RAND () LIMIT 4", nativeQuery = true)
    List<String> getRandom_C();

    @Query(value="SELECT png FROM cards WHERE rarity='U' ORDER BY RAND () LIMIT 2", nativeQuery = true)
    List<String> getRandom_U();

    @Query(value="SELECT png FROM cards WHERE rarity='R' ORDER BY RAND () LIMIT 1", nativeQuery = true)
    String getRandom_R();

    @Query(value="SELECT png FROM cards WHERE rarity=rar ORDER BY RAND () LIMIT 1", nativeQuery = true)
    String getRandom_Foil(
            @Param("rar") String rar
    );

    @Query(value =
            "(SELECT png FROM cards WHERE rarity='C' ORDER BY RAND () LIMIT 4) UNION ALL" +
            "(SELECT png FROM cards WHERE rarity='U' ORDER BY RAND () LIMIT 2) UNION ALL" +
            "(SELECT png FROM cards WHERE rarity='R' ORDER BY RAND () LIMIT 1) UNION ALL" +
            "(SELECT png FROM cards WHERE rarity= :rar ORDER BY RAND () LIMIT 1)", nativeQuery = true
    )
    List<String> openBooster(
            @Param("rar") String rar
    );




}
