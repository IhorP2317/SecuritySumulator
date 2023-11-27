package com.securitySimulator.model.entities;

import com.securitySimulator.model.enums.NormativeType;
import com.securitySimulator.model.sensor.Sensor;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lil nigga
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "Rooms")
public class Room {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Getter
    @Setter
    @Column
    Double square;

    @Getter
    @Setter
    @Column
    Integer amountOfDoors;
    @Getter
    @Setter
    @Column
    Integer amountOfWindows;
    @Getter
    @Setter
    NormativeType normativeType;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "floor_id")
    Floor floor;

    @Getter
    @Setter
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Sensor> sensorsForRoom;

    public Room(Integer id, Double square, Integer amountOfDoors, Integer amountOfWindows, NormativeType normativeType, List<Sensor> sensorsForRoom) {
        this.id = id;
        this.square = square;
        this.amountOfDoors = amountOfDoors;
        this.amountOfWindows = amountOfWindows;
        this.normativeType = normativeType;
        this.sensorsForRoom = sensorsForRoom;
    }
}
