package com.securitySimulator.model.sensor;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;



@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
@Entity
@DiscriminatorValue("MOTION")
public class MotionSensor extends Sensor {
    Boolean isMovementDetected;
}
