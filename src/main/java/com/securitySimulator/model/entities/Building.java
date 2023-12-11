package com.securitySimulator.model.entities;

import com.securitySimulator.model.sensor.Sensor;
import com.securitySimulator.model.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "Buildings")
public class Building extends BuildingComposite{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String address;
    String coordinateX;
    String coordinateY;

    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    List<Apartment> apartments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;


    public Building(String address, String coordinateX, String coordinateY){
        this.address = address;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }


    @Override
    public List<Sensor> getAllSensors() {
        List<Sensor> sensors = new ArrayList<>();

        apartments.forEach(a -> sensors.addAll(a.getAllSensors()));

        return sensors;
    }

    @Override
    public String getFullAddress() {
        return address;
    }
}
