package com.securitySimulator.model.sensor;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

/**
 * @author vasya pupkin
 */
//@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
@Entity
@DiscriminatorValue("MOTION")
public class MotionSensor extends Sensor {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Getter
//    Integer id;
    LocalDateTime curfewStart;
     LocalDateTime curfewEnd;
}
