package com.securitySimulator.model.sensor;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * @author vasya pupkin
 */
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
@Entity
@DiscriminatorValue("TEMP")
public class TemperatureSensor extends Sensor {
    Double currentTemperature;
}
