package com.securitySimulator.model.sensor;

import com.securitySimulator.model.entities.Floor;
import com.securitySimulator.model.entities.Room;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "sensor_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "Sensors")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;

    LocalDateTime activationStart;

    LocalDateTime activationEnd;

    Double workingArea;

    Boolean isViolationDetected;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    Room room;

    public Sensor(){
    }
}
