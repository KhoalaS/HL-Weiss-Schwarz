package com.khoale.hlcards.repository;

import com.khoale.hlcards.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepo extends JpaRepository<Cards, Integer> {
}
