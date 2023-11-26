package com.securitySimulator.repository;

import com.securitySimulator.model.entities.Apartment;
import com.securitySimulator.model.entities.Floor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FloorRepository extends JpaRepository<Floor, Long> {
}
