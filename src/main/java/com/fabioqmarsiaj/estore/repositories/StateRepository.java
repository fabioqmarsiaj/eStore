package com.fabioqmarsiaj.estore.repositories;

import com.fabioqmarsiaj.estore.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {
}
