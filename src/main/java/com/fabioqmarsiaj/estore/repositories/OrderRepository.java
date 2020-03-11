package com.fabioqmarsiaj.estore.repositories;

import com.fabioqmarsiaj.estore.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
