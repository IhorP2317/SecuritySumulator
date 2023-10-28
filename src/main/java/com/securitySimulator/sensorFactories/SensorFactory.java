package com.securitySimulator.sensorFactories;

import com.securitySimulator.model.sensor.Sensor;

public interface SensorFactory {

        Sensor createSensor(String sensorType);

}