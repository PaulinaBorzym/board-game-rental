package com.project.repository;

import com.project.domain.Cart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends CrudRepository<Cart,Long> {
    @Override
    List<Cart> findAll();

    @Override
    Cart save(Cart cart);

    @Override
    Optional<Cart> findById(Long id);
}
