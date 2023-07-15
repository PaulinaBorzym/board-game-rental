package com.project.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.enums.GameType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

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

    private String description;


    public Game(Long gameId,String title, double price, String publicationYear, GameType type, String description) {
        this.gameId = gameId;
        this.title = title;
        this.price = price;
        this.publicationYear = publicationYear;
        this.type = type;
        this.description = description;
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
    @JsonManagedReference(value = "GameReference")
    public Rent getRent() {
        return rent;
    }

    @NonNull
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameId=" + gameId +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", publicationYear='" + publicationYear + '\'' +
                ", type=" + type +
                ", rent=" + rent +
                ", description='" + description + '\'' +
                '}';
    }
}
