package com.project.mapper;

import com.project.domain.Cart;
import com.project.domain.Game;
import com.project.domain.Order;
import com.project.dto.CartDto;
import com.project.dto.GameDto;
import com.project.dto.OrderDto;

import java.util.List;
import java.util.stream.Collectors;

public class CartMapper {
    public Cart mapToCart(final CartDto cartDto) {
        return new Cart(
                cartDto.getTotalPrice()
        );
    }

    public CartDto mapToCartDto(final Cart cart){
        return new CartDto(
                cart.getCartId(),
                cart.getTotalPrice()
        );
    }

    public List<CartDto> mapToCartDtoList(final List<Cart> cartList) {
        return cartList.stream()
                .map(this::mapToCartDto)
                .collect(Collectors.toList());
    }
}
