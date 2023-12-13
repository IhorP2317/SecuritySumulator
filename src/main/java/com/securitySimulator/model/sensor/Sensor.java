package com.securitySimulator.model.sensor;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.securitySimulator.model.entities.Room;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.Entity;
@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "sensor_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "Sensors")
public class Sensor{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Boolean isViolationDetected;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    @JsonBackReference
    Room room;

    public Sensor(){
    }
    @Override
    public String toString() {
        return "";
    }
}
