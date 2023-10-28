package com.securitySimulator.model.building;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * @author rom4ik
 */


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
    @Column
    Integer number;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    Apartment apartment;

    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL)
    @JoinColumn(name = "floor_id")
    List<Room> rooms;

}

