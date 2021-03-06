package com.fabioqmarsiaj.estore.repositories;

import com.fabioqmarsiaj.estore.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
