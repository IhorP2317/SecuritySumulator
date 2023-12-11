package com.securitySimulator.helpers;

import com.securitySimulator.model.entities.Room;
import com.securitySimulator.model.enums.NormativeType;
import com.securitySimulator.model.sensor.Sensor;
import com.securitySimulator.sensorFactories.FactoryProducer;
import com.securitySimulator.sensorFactories.SensorFactory;

import java.util.ArrayList;
import java.util.List;

public class RoomHelper {

    public static void populateSensorsForRoom(Room room) {
        int totalSensors = room.getAmountOfDoors() * room.getAmountOfWindows();
        List<Sensor> sensors = new ArrayList<>();

        SensorFactory sensorFactory1 = FactoryProducer.getFactory(true);
        SensorFactory sensorFactory2 = FactoryProducer.getFactory(false);

        if (room.getNormativeType() == NormativeType.Common) {
            for (int i = 0; i < totalSensors / 2; i++) {
                sensors.add(sensorFactory1.createSensor());
            }
        } else if (room.getNormativeType() == NormativeType.Premium) {
            for (int i = 0; i < totalSensors / 2; i++) {
                sensors.add(sensorFactory1.createSensor());
            }
            for (int i = 0; i < totalSensors / 2; i++) {
                sensors.add(sensorFactory2.createSensor());
            }
        } else if (room.getNormativeType() == NormativeType.Advanced) {
            for (int i = 0; i < totalSensors; i++) {
                sensors.add(sensorFactory1.createSensor());
            }
            for (int i = 0; i < totalSensors; i++) {
                sensors.add(sensorFactory2.createSensor());
            }
        }
        sensors.forEach(s -> s.setRoom(room));
        room.setSensorsForRoom(sensors);

    }
}
