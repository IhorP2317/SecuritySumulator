package com.securitySimulator.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.securitySimulator.model.sensor.Sensor;
import com.securitySimulator.repository.SensorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class SimulationMemento {

    @Autowired
    SensorRepository sensorRepository;
    private List<Sensor> allSensors;

    private static final String filePath = "sensorscache.txt";

    public void SaveInToFile() {
        allSensors = sensorRepository.findAll();

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            ObjectMapper objectMapper = new ObjectMapper();

            for (Sensor sensor : allSensors) {
                String sensorJson = objectMapper.writeValueAsString(sensor);
                writer.println(sensorJson);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void RefreshDbFromFile(){
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            ObjectMapper objectMapper = new ObjectMapper();

            String line;
            while ((line = reader.readLine()) != null) {
                Sensor sensor = objectMapper.readValue(line, Sensor.class);

                Optional<Sensor> existingSensor = sensorRepository.findById(sensor.getId());
                if (existingSensor.isPresent()) {
                    Sensor updatedSensor = existingSensor.get();
                    updatedSensor.setIsViolationDetected(sensor.getIsViolationDetected());
                    sensorRepository.save(updatedSensor);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
