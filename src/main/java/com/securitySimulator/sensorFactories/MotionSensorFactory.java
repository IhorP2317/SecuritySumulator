package com.securitySimulator.sensorFactories;

import com.securitySimulator.model.sensor.MotionSensor;
import com.securitySimulator.model.sensor.Sensor;


public class MotionSensorFactory extends SensorFactory{
    @Override
    public Sensor createSensor() {
            return new MotionSensor();
    }
}
