package com.project.controller;

import com.project.domain.Order;
import com.project.dto.CartDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/cart")
public class CartController {
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CartDto createCart(@RequestBody CartDto cartDto)  {
        return cartDto;
    }
    @PostMapping(value = "{cartId}/{gameId}")
    public CartDto addToCart(@PathVariable Long cartId, @PathVariable Long gameId) {
        return new CartDto(1,new BigDecimal(1));
    }
    @DeleteMapping(value = "{gameId}")
    public void deleteCart(@PathVariable Long cartId) {
    }
    @DeleteMapping(value = "{cartId}/{gameId}")
    public void deleteFromCart(@PathVariable Long cartId, @PathVariable Long gameId) {
    }
    @GetMapping(value = "{cartId}")
    public List<CartDto> getProducts(@PathVariable Long cartId) {
        return new ArrayList<>();
    }
    @PostMapping(value = "{cartId}/order")
    public void createOrder(@PathVariable Long cartId, @RequestBody Order order) {}
}
