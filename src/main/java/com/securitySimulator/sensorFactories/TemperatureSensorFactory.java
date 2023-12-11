package com.securitySimulator.sensorFactories;

import com.securitySimulator.model.sensor.Sensor;
import com.securitySimulator.model.sensor.TemperatureSensor;


public class TemperatureSensorFactory extends SensorFactory{
    @Override
    public Sensor createSensor() {
            return new TemperatureSensor();
    }
}
