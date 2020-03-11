package com.fabioqmarsiaj.estore.repositories;

import com.fabioqmarsiaj.estore.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
