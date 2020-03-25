package com.fabioqmarsiaj.estore.repositories;

import com.fabioqmarsiaj.estore.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
