package com.project.domain;

import com.sun.istack.NotNull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CART")
public class Cart {
    Long cartId;
    BigDecimal totalPrice;
    private Game game;

    public Cart(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Id
    @GeneratedValue
    @Column(name = "CART_ID",unique = true)
    public Long getCartId() {
        return cartId;
    }

    @Column(name = "TOTAL_PRICE")
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    @ManyToOne
    @JoinColumn(name = "GAME_ID")
    public Game getGame() {
        return game;
    }
}
