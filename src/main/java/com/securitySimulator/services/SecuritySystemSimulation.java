package com.securitySimulator.services;

import com.securitySimulator.model.enums.ViolationType;
import com.securitySimulator.model.sensor.MotionSensor;
import com.securitySimulator.model.sensor.Sensor;
import com.securitySimulator.model.DTO.ViolationDTO;
import com.securitySimulator.repository.SensorRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;


@Component
@Slf4j
public class SecuritySystemSimulation {

    @Autowired
    SensorRepository sensorRepository;
    private ExecutorService simulationThreadPool;
    private List<Sensor> allSensors;
    private ClientMapSocketService clientMapSocketService;
    @Getter
    private SimulationMemento simulationMemento;

    public boolean configureSimulation() throws IOException {
        allSensors = sensorRepository.findAll();
        log.info("configuring simulation");
        clientMapSocketService = new ClientMapSocketService();
        this.simulationThreadPool = Executors.newFixedThreadPool(3);
        runSimulation();
        return !allSensors.isEmpty();
    }

    public void runSimulation() {
        log.info("starting threads");
        simulationThreadPool.submit(() -> processViolationEvents());
        simulationThreadPool.submit(() -> processViolationEvents());
        simulationThreadPool.submit(() -> CheckDbSensors());
    }

    private void processViolationEvents() {
        while (!Thread.currentThread().isInterrupted()) {
            try {

                CreateViolation();

                TimeUnit.MILLISECONDS.sleep(5000);
            } catch (InterruptedException e) {
                log.error("Error in event processing thread: {}", e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }

    public synchronized void CreateViolation() {
        try {
            log.info("creating violation");
            int randomIndex = ThreadLocalRandom.current().nextInt(allSensors.size());
            var randomSender = allSensors.get(randomIndex);
            randomSender.setIsViolationDetected(true);

            Optional<Sensor> SensorData = sensorRepository.findById(randomSender.getId());

            if (SensorData.isPresent()) {
                Sensor _Sensor = SensorData.get();
                _Sensor.setIsViolationDetected(true);
                sensorRepository.save(_Sensor);
            }

        } catch (Exception ex) {
            log.error("Error in creating violation thread: {}", ex.getMessage());
        }
    }

    //checking db in cycle to send message through socket to client when sensor is changed
    public void CheckDbSensors(){
        while (!Thread.currentThread().isInterrupted()) {
            try {

                var sensors = sensorRepository.findAll();

                var violatedSensor = sensors.stream().filter(Sensor::getIsViolationDetected).findFirst();
                if(violatedSensor.isPresent()){
                    var sensor = violatedSensor.get();
                    clientMapSocketService.SendInfo(new ViolationDTO(sensor.getId(),
                            sensor instanceof MotionSensor ? ViolationType.Movement : ViolationType.Temperature));
                }
            }
             catch (IOException e) {
                log.error("Error in checking db update thread: {}", e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }

    public void shutdown(){
        try {
            log.info("stopping all threads in thread pool");
            this.simulationThreadPool.shutdownNow();
            clientMapSocketService.StopConnection();
        }
        catch (Exception e) {
            log.error("Error in stopping threads: {}", e.getMessage());
        }
    }

    public void pause(){
        try {
            log.info("pausing simulation");
            getSimulationMemento().SaveInToFile();
            this.simulationThreadPool.shutdownNow();
            clientMapSocketService.StopConnection();
        }
        catch (Exception e) {
            log.error("Error in pausing simulation thread: {}", e.getMessage());
        }
    }

    public void resume(){
        try {
            getSimulationMemento().RefreshDbFromFile();
            if(configureSimulation()){
                log.info("simulation resumed successfully");
            }else{
                throw new RuntimeException("error while trying to resume simulation");
            }
        }
        catch (Exception e) {
            log.error("Error in resuming simulation thread: {}", e.getMessage());
        }
    }

    @Autowired()
    public void setSimulationMemento(SimulationMemento simulationMemento) {
        this.simulationMemento = simulationMemento;
    }

}
