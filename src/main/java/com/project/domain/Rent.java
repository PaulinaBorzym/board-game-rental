package com.project.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@NoArgsConstructor
@Entity
@Table(name = "GAME_RENTS")
public class Rent {
    private Long rentId;
    private User user;
    private Game game;
    private LocalDate startDate;
    private LocalDate endDate;
    private double price;


    public Rent(Long rentId, User user, Game game, LocalDate startDate, LocalDate endDate, double price) {
        this.rentId = rentId;
        this.user = user;
        this.game = game;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "USER_ID",referencedColumnName = "USER_ID")
    @JsonBackReference(value = "UserReference")
    public User getUser() {
        return user;
    }

    @Id
    @GeneratedValue
    @NonNull
    @Column(name = "RENT_ID", unique = true)
    public Long getRentId() {
        return rentId;
    }

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "GAME_ID")
    @JsonBackReference(value = "GameReference")
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

    @Override
    public String toString() {
        return "Rent{" +
                "rentId=" + rentId +
                ", userPhone=" + user.getPhoneNumber() +
                ", gameTitle=" + game.getTitle() +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", price=" + price +
                '}';
    }
}
