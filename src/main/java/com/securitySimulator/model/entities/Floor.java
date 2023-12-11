package com.securitySimulator.model.entities;

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
public class Floor extends BuildingComposite{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Integer number;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "apartment_id")
    Apartment apartment;

    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Room> rooms;


    public Floor(Integer id, Integer number, List<Room> rooms) {
        this.id = id;
        this.number = number;
        this.rooms = rooms;
    }

    public Floor(Integer number) {
        this.number = number;
    }

    @Override
    public List<Sensor> getAllSensors() {
        List<Sensor> sensors = new ArrayList<>();

        rooms.forEach(r -> sensors.addAll(r.getAllSensors()));

        return sensors;
    }

    @Override
    public String getFullAddress() {
        return apartment != null ? apartment.getFullAddress() + ' ' + number.toString() : "";
    }
}

