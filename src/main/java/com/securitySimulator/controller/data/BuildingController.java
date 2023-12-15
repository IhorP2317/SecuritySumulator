package com.securitySimulator.controller.data;

import com.securitySimulator.model.entities.Building;
import com.securitySimulator.model.user.User;
import com.securitySimulator.repository.BuildingRepository;
import com.securitySimulator.repository.UserRepository;
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
@RequestMapping("/api/data/buildings")
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_USER')")
@ComponentScan
public class BuildingController {

    @Autowired
    BuildingRepository buildingRepository;
    @Autowired
    UserRepository userRepository;

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

    @GetMapping("/getBuildingsByUserId/{id}")
    public ResponseEntity<List<Building>> getBuildingsByUserId(@PathVariable("id") long id) {
        Optional<User> UserData = userRepository.findById(id);

        if (UserData.isPresent()) {
            var user = UserData.get();
            var userApartments = user.getApartments();
            Optional<List<Building>> buildingsWithUserApartments =
                    buildingRepository.findByApartmentsIn(userApartments);

            if (buildingsWithUserApartments.isPresent()) {
                return new ResponseEntity<>(buildingsWithUserApartments.get(), HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getBuildingById/{id}")
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
                    .save(new Building(Building.getId(), Building.getCoordinateX(), Building.getCoordinateY(), Building.getApartments()));
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
            _Building.setCoordinateX(Building.getCoordinateX());
            _Building.setCoordinateY(Building.getCoordinateY());
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