package com.securitySimulator.controller;

import com.securitySimulator.model.entities.Building;
import com.securitySimulator.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/buildings")
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_USER')")
@ComponentScan
public class BuildingController {

    @Autowired
    BuildingRepository buildingRepository;

    @GetMapping
    public ResponseEntity<List<Building>> getAllBuildings() {
        try {
            List<Building> Buildings = new ArrayList<>();

            buildingRepository.findAll().forEach(Buildings::add);

            if (Buildings.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(Buildings, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Building> getBuildingById(@PathVariable("id") long id) {
        Optional<Building> BuildingData = buildingRepository.findById(id);

        if (BuildingData.isPresent()) {
            return new ResponseEntity<>(BuildingData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Building> createBuilding(@RequestBody Building Building) {
        try {
            Building _Building = buildingRepository
                    .save(new Building(Building.getId(), Building.getCoordinates(), Building.getApartments()));
            return new ResponseEntity<>(_Building, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Building> updateBuilding(@PathVariable("id") long id, @RequestBody Building Building) {
        Optional<Building> BuildingData = buildingRepository.findById(id);

        if (BuildingData.isPresent()) {
            Building _Building = BuildingData.get();
            _Building.setCoordinates(Building.getCoordinates());
            return new ResponseEntity<>(buildingRepository.save(_Building), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBuilding(@PathVariable("id") long id) {
        try {
            buildingRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllBuildings() {
        try {
            buildingRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}