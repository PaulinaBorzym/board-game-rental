package com.project.domain;

import com.project.enums.GameType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Games")
public class Game {

    private Long gameId;
    private String title;
    private double price;
    private String publicationYear;
    private GameType type;
    private Rent rent;


    public Game(String title, double price, String publicationYear, GameType type) {
        this.title = title;
        this.price = price;
        this.publicationYear = publicationYear;
        this.type = type;
    }

    @Id
    @GeneratedValue
    @NonNull
    @Column(name = "GAME_ID", unique = true)
    public Long getGameId() {
        return gameId;
    }

    @NonNull
    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    @NonNull
    @Column(name = "PRICE")
    public double getPrice() {
        return price;
    }

    @NonNull
    @Column(name = "PUBLICATION_YEAR")
    public String getPublicationYear() {
        return publicationYear;
    }
    @NonNull
    @Column(name = "TYPE")
    public GameType getType() {
        return type;
    }

    @OneToOne(mappedBy = "game")
    public Rent getRent() {
        return rent;
    }
}
