package com.securitySimulator.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.securitySimulator.model.enums.NormativeType;
import com.securitySimulator.model.sensor.Sensor;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


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

    Double square;

    Integer amountOfDoors;

    Integer amountOfWindows;

    NormativeType normativeType;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "floor_id", referencedColumnName = "id")
    @JsonBackReference
    Floor floor;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    List<Sensor> sensorsForRoom;

    public Room(Integer id, Double square, Integer amountOfDoors, Integer amountOfWindows, NormativeType normativeType, List<Sensor> sensorsForRoom) {
        this.id = id;
        this.square = square;
        this.amountOfDoors = amountOfDoors;
        this.amountOfWindows = amountOfWindows;
        this.normativeType = normativeType;
        this.sensorsForRoom = sensorsForRoom;
    }

    public Room(Double square, Integer amountOfDoors, Integer amountOfWindows, NormativeType normativeType){
        this.square = square;
        this.amountOfDoors = amountOfDoors;
        this.amountOfWindows = amountOfWindows;
        this.normativeType = normativeType;
    }
}
