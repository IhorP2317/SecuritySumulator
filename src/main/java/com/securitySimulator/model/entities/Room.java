package com.securitySimulator.model.entities;

import com.securitySimulator.model.enums.NormativeType;
import com.securitySimulator.model.sensor.Sensor;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "Rooms")
public class Room extends BuildingComposite{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Double square;

    Integer amountOfDoors;

    Integer amountOfWindows;

    NormativeType normativeType;

    @Nullable
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "floor_id")
    Floor floor;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

    @Transient
    @Override
    public List<Sensor> getAllSensors() {
        return sensorsForRoom;
    }
    @Transient
    @Override
    public String getFullAddress() {
        return floor != null ? floor.getFullAddress() + ' ' + getId().toString() : "";
    }
}
