package com.fabioqmarsiaj.estore.services;

import com.fabioqmarsiaj.estore.domain.Order;
import com.fabioqmarsiaj.estore.repositories.OrderRepository;
import com.fabioqmarsiaj.estore.services.exceptions.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order find(Integer id) {
        Optional<Order> obj = orderRepository.findById(id);
        return obj.orElseThrow(() -> new OrderNotFoundException(
                "Order with Id: + " + id + " not found."
        ));
    }
}
