package com.khoale.hlcards.Entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Cards {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String idol;

    private String png;

    private String rarity;

    @ManyToMany(mappedBy = "cards")
    Set<User> players;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdol() {
        return idol;
    }

    public void setIdol(String idol) {
        this.idol = idol;
    }

    public String getPng() {
        return png;
    }

    public void setPng(String png) {
        this.png = png;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public Cards(Integer id, String idol, String png, String rarity) {
        this.id = id;
        this.idol = idol;
        this.png = png;
        this.rarity = rarity;
    }

    public Cards(){}

    @Override
    public String toString() {
        return "{\"Cards\":{"
                + "\"id\":\"" + id + "\""
                + ", \"idol\":\"" + idol + "\""
                + ", \"png\":\"" + png + "\""
                + ", \"rarity\":\"" + rarity + "\""
                + "}}";
    }
}
