package com.securitySimulator.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.securitySimulator.model.sensor.Sensor;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "Buildings")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String coordinateX;
    String coordinateY;

    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    List<Apartment> apartments;

    public Building(String coordinateX, String coordinateY){
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }
}
