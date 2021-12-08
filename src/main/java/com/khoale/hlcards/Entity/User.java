package com.khoale.hlcards.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;

    @Column(unique = true)
    private String email;

    private String pw;

    private Long currency;

    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "player_deck",
            joinColumns = @JoinColumn(name = "user_ID"),
            inverseJoinColumns = @JoinColumn(name = "card_ID")
    )
    private Set<Cards> cards = new HashSet<>();

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

    public User(String email, String pw, Long currency) {
        this.email = email;
        this.pw = pw;
        this.currency = currency;
    }

    public User(){
        this.currency = 10000L;
    }

    public Set<Cards> getCards() {
        return cards;
    }

    public void setCards(Set<Cards> cards) {
        this.cards = cards;
    }

    public void addCards(List<Cards> card) {
            this.getCards().addAll(card);
    }

    @Override
    public String toString() {
        return "{\"Players\":{"
                + "\"uid\":\"" + uid + "\""
                + ", \"email\":\"" + email + "\""
                + ", \"pw\":\"" + pw + "\""
                + ", \"currency\":\"" + currency + "\""
                + "}}";
    }
}
