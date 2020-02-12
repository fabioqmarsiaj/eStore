package com.fabioqmarsiaj.estore.repositories;

import com.fabioqmarsiaj.estore.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
