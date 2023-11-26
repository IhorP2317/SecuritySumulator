package com.securitySimulator.model.entities;

import jakarta.persistence.*;

import com.securitySimulator.model.enums.NormativeType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * @author vasya pupkin
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "Floors")
public class Floor {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Getter
    @Setter
    @Column
    Integer number;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id")
    Apartment apartment;

    @Getter
    @Setter
    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL)
    List<Room> rooms;


    public Floor(Integer id, Integer number, List<Room> rooms) {
        this.id = id;
        this.number = number;
        this.rooms = rooms;
    }
}

