package com.securitySimulator.repository;

import com.securitySimulator.model.entities.Apartment;
import com.securitySimulator.model.entities.Building;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BuildingRepository extends JpaRepository<Building, Long> {
    Optional<List<Building>> findByApartmentsIn(List<Apartment> userApartments);
}
