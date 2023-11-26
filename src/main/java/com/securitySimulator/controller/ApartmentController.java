package com.securitySimulator.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.securitySimulator.model.entities.Apartment;
import com.securitySimulator.repository.ApartmentRepository;


//http://localhost:8080/swagger-ui/index.html

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/apartments")
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_USER')")
@ComponentScan
public class ApartmentController {

    @Autowired
    ApartmentRepository apartmentRepository;

    @GetMapping
    public ResponseEntity<List<Apartment>> getAllApartments() {
        try {
            List<Apartment> apartments = new ArrayList<>();

            apartmentRepository.findAll().forEach(apartments::add);

            if (apartments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(apartments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Apartment> getApartmentById(@PathVariable("id") long id) {
        Optional<Apartment> ApartmentData = apartmentRepository.findById(id);

        if (ApartmentData.isPresent()) {
            return new ResponseEntity<>(ApartmentData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Apartment> createApartment(@RequestBody Apartment Apartment) {
        try {
            Apartment _apartment = apartmentRepository
                    .save(new Apartment(Apartment.getId(), Apartment.getAddress(), Apartment.getFloors()));
            return new ResponseEntity<>(_apartment, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Apartment> updateApartment(@PathVariable("id") long id, @RequestBody Apartment Apartment) {
        Optional<Apartment> ApartmentData = apartmentRepository.findById(id);

        if (ApartmentData.isPresent()) {
            Apartment _Apartment = ApartmentData.get();
            _Apartment.setAddress(Apartment.getAddress());
            return new ResponseEntity<>(apartmentRepository.save(_Apartment), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteApartment(@PathVariable("id") long id) {
        try {
            apartmentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllApartments() {
        try {
            apartmentRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}



