package com.project.service;

import com.project.domain.Order;
import com.project.exeption.OrderNotFoundException;
import com.project.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;

    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public Order getOrder(final Long orderId) throws OrderNotFoundException {
        return repository.findById(orderId).orElseThrow(OrderNotFoundException::new);
    }

    public Order saveOrder(final Order order) {
        return repository.save(order);
    }

    public void deleteOrder(final Long id) {
        repository.deleteById(id);
    }
}
