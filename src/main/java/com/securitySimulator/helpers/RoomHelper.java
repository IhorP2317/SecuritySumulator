package com.securitySimulator.helpers;

import com.securitySimulator.model.entities.Room;
import com.securitySimulator.model.enums.NormativeType;
import com.securitySimulator.model.sensor.MotionSensor;
import com.securitySimulator.model.sensor.Sensor;
import com.securitySimulator.model.sensor.TemperatureSensor;

import java.util.ArrayList;
import java.util.List;

public class RoomHelper {

    public static void populateSensorsForRoom(Room room) {
        int totalSensors = room.getAmountOfDoors() * room.getAmountOfWindows();
        List<Sensor> sensors = new ArrayList<>();

        // Populate sensors based on normativeType
        if (room.getNormativeType() == NormativeType.Common) {
            // Common: MotionSensors only
            for (int i = 0; i < totalSensors / 2; i++) {
                sensors.add(new MotionSensor());
            }
        } else if (room.getNormativeType() == NormativeType.Premium) {
            // Premium: Half MotionSensors, Half TemperatureSensors
            for (int i = 0; i < totalSensors / 2; i++) {
                sensors.add(new MotionSensor());
            }
            for (int i = 0; i < totalSensors / 2; i++) {
                sensors.add(new TemperatureSensor());
            }
        } else if (room.getNormativeType() == NormativeType.Advanced) {
            // Advanced: All MotionSensors and All TemperatureSensors
            for (int i = 0; i < totalSensors; i++) {
                sensors.add(new MotionSensor());
            }
            for (int i = 0; i < totalSensors; i++) {
                sensors.add(new TemperatureSensor());
            }
        }

        room.setSensorsForRoom(sensors);
    }
}
