package com.securitySimulator.model.normative;

import com.securitySimulator.model.sensor.Sensor;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "Normatives")
public class Normative {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column
    Integer sensorsPerDoor ;

    @Column
    Integer sensorsPerWindow;
  /*  @OneToMany(cascade = CascadeType.ALL, mappedBy = "normativeForDoor")
    ArrayList<Sensor> sensorsForDoor;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "normativeForWindow")
    ArrayList<Sensor> sensorsForWindow;*/




   /* @OneToMany(cascade = CascadeType.ALL, mappedBy = "normativeForApartment")
    ArrayList<Sensor> sensorsForApartment;*/

}
