package com.securitySimulator.model.building;

import com.securitySimulator.model.normative.Normative;
import com.securitySimulator.model.sensor.Sensor;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;

/**
 * @author rom4ik
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "Rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column
    Integer amountOfDoors;
    @Column
    Integer amountOfWindows;
    @Column
    Double square;

    @ManyToOne
    @JoinColumn(name = "floor_id")
    Floor floor;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    ArrayList<Sensor> sensorsForRoom;
    public int getAmountOfHoles() {
        return amountOfDoors + amountOfWindows;
    }
}
