package com.securitySimulator.repository;
import com.securitySimulator.model.entities.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
}
