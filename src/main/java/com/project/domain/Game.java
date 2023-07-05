package com.project.domain;

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
    private String gameName;
    private String description;
    private double price;
    private List<User> users;

    public Game(String gameName, String description, double price) {
        this.gameName = gameName;
        this.description = description;
        this.price = price;
    }

    @Id
    @GeneratedValue
    @NonNull
    @Column(name = "GAME_ID", unique = true)
    public Long getGameId() {
        return gameId;
    }

    @NonNull
    @Column(name = "GAME_NAME")
    public String getGameName() {
        return gameName;
    }

    @NonNull
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    @NonNull
    @Column(name = "PRICE")
    public double getPrice() {
        return price;
    }

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "games")
    public List<User> getUsers() {
        return users;
    }
}
