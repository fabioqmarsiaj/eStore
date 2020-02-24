package com.fabioqmarsiaj.estore.repositories;

import com.fabioqmarsiaj.estore.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
