package com.securitySimulator.repository;

import com.securitySimulator.model.entities.Apartment;
import com.securitySimulator.model.entities.Building;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<Building, Long> {
}
