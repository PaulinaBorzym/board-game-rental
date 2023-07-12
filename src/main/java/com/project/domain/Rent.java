package com.project.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "RENTS")
public class Rent {
    private Long id;
    private User user;
    private Game game;
    private LocalDate startDate;
    private LocalDate endDate;
    private double price;


    public Rent(User user, Game game, LocalDate startDate, LocalDate endDate, double price) {
        this.user = user;
        this.game = game;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    public User getUser() {
        return user;
    }

    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true)
    public Long getId() {
        return id;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "GAME_ID")
    public Game getGame() {
        return game;
    }
    @NonNull
    @Column(name = "START_DATE")
    public LocalDate getStartDate() {
        return startDate;
    }

    @NonNull
    @Column(name = "END_DATE")
    public LocalDate getEndDate() {
        return endDate;
    }

    @NonNull
    @Column(name = "PRICE")
    public double getPrice() {
        return price;
    }
}
