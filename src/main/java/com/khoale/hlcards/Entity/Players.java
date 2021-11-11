package com.khoale.hlcards.Entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Players {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;

    private String email;

    private String pw;

    private Long currency;

    @ManyToMany
    @JoinTable(
            name = "player_deck",
            joinColumns = @JoinColumn(name = "player_ID"),
            inverseJoinColumns = @JoinColumn(name = "card_ID")
    )
    Set<Cards> cards;


    public Long getCurrency() {
        return currency;
    }

    public void setCurrency(Long currency) {
        this.currency = currency;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public Players(String email, String pw, Long currency) {
        this.email = email;
        this.pw = pw;
        this.currency = currency;
    }
}
