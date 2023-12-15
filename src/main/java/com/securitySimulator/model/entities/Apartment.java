package com.securitySimulator.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.securitySimulator.model.sensor.Sensor;
import com.securitySimulator.model.user.User;
import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "Apartments")
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "building_id")
    @JsonBackReference
    Building building;

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    List<Floor> floors;

    public Apartment(Integer id, List<Floor> floors) {
        this.id = id;
        this.floors = floors;
    }

    public Apartment(List<Floor> floors) {
        this.floors = floors;
    }
}
