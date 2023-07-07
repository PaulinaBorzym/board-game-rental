package com.project.service;

import com.project.domain.Cart;
import com.project.domain.User;
import com.project.exeption.CartNotFoundException;
import com.project.exeption.UserNotFoundException;
import com.project.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository repository;

    public List<Cart> getAllCarts(){
        return repository.findAll();
    }

    public Cart getCart(final Long cartId) throws CartNotFoundException {
        return repository.findById(cartId).orElseThrow(CartNotFoundException::new);
    }

    public Cart saveCart(final Cart cart){
        return repository.save(cart);
    }

    public void deleteCart(final Long cartId){
        repository.deleteById(cartId);
    }

}
