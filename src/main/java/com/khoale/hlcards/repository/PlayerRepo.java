package com.khoale.hlcards.repository;

import com.khoale.hlcards.entity.Players;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public interface PlayerRepo extends JpaRepository<Players, Integer> {

}
