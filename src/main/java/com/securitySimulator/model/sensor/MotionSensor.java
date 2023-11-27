package com.securitySimulator.model.sensor;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

/**
 * @author vasya pupkin
 */
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
@Entity
@DiscriminatorValue("MOTION")
public class MotionSensor extends Sensor {
    LocalDateTime curfewStart;
    LocalDateTime curfewEnd;
}
