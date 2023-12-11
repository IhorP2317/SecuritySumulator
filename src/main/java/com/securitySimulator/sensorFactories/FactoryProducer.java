package com.securitySimulator.sensorFactories;

public class FactoryProducer {
    public static SensorFactory getFactory(boolean isMotionSensor){
        if(isMotionSensor){
            return new MotionSensorFactory();
        }else{
            return new TemperatureSensorFactory();
        }
    }
}
