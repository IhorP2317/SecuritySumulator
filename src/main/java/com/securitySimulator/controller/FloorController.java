package com.securitySimulator.controller;

import com.securitySimulator.model.entities.Floor;
import com.securitySimulator.repository.FloorRepository;
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
@RequestMapping("/api/floors")
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_USER')")
@ComponentScan
public class FloorController {

    @Autowired
    FloorRepository FloorRepository;

    @GetMapping
    public ResponseEntity<List<Floor>> getAllFloors() {
        try {
            List<Floor> Floors = new ArrayList<>();

            FloorRepository.findAll().forEach(Floors::add);

            if (Floors.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(Floors, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Floor> getFloorById(@PathVariable("id") long id) {
        Optional<Floor> FloorData = FloorRepository.findById(id);

        if (FloorData.isPresent()) {
            return new ResponseEntity<>(FloorData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Floor> createFloor(@RequestBody Floor Floor) {
        try {
            Floor _Floor = FloorRepository
                    .save(new Floor(Floor.getId(), Floor.getNumber(), Floor.getRooms()));
            return new ResponseEntity<>(_Floor, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Floor> updateFloor(@PathVariable("id") long id, @RequestBody Floor Floor) {
        Optional<Floor> FloorData = FloorRepository.findById(id);

        if (FloorData.isPresent()) {
            Floor _Floor = FloorData.get();
            _Floor.setNumber(Floor.getNumber());
            return new ResponseEntity<>(FloorRepository.save(_Floor), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteFloor(@PathVariable("id") long id) {
        try {
            FloorRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllFloors() {
        try {
            FloorRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}