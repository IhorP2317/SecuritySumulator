package com.securitySimulator.repository;

import com.securitySimulator.model.sensor.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, Integer> {
}
