package com.securitySimulator.model.entities;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.Entity;

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

    String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    Building building;

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Floor> floors;

    public Apartment(Integer id, String address, List<Floor> floors) {
        this.id = id;
        this.address = address;
        this.floors = floors;
    }
}
