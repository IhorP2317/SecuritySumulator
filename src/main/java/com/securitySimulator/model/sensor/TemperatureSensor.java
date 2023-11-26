package com.securitySimulator.model.sensor;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * @author vasya pupkin
 */
//@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
@Entity
@DiscriminatorValue("TEMP")
public class TemperatureSensor extends Sensor {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Getter
//    Integer id;
    Double currentTemperature;
}
