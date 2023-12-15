package com.securitySimulator.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "Floors")
public class Floor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Integer number;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "apartment_id")
    @JsonBackReference
    Apartment apartment;

    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    List<Room> rooms;

    public Floor(Integer id, Integer number, List<Room> rooms) {
        this.id = id;
        this.number = number;
        this.rooms = rooms;
    }

    public Floor(Integer number) {
        this.number = number;
    }
}

