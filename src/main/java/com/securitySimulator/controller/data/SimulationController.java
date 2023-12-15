package com.securitySimulator.controller.data;

import com.securitySimulator.payload.response.MessageResponse;
import com.securitySimulator.repository.BuildingRepository;
import com.securitySimulator.repository.SensorRepository;
import com.securitySimulator.services.SecuritySystemSimulation;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;



@RestController
@RequestMapping("/api/data/simulation")
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_USER')")
@ComponentScan
public class SimulationController {
    @Autowired
    BuildingRepository buildingRepository;
    @Autowired
    SensorRepository sensorRepository;

    @Getter
    private SecuritySystemSimulation securitySystemSimulation;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> configureSimulation(){

            var allSensors = sensorRepository.findAll();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        getSecuritySystemSimulation().configureSimulation();
                    }
                    catch (IOException e) {

                    }
                }
            }).start();

            if(!allSensors.isEmpty()){
                return new ResponseEntity<>(HttpStatus.OK);
            }
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("configuration couldn't start due to internal error");
    }

    @PatchMapping("/stop")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> stopSimulation() {
        try {
            getSecuritySystemSimulation().shutdown();
            return ResponseEntity.ok(new MessageResponse("stopped successfully"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("simulation couldn't be stopped");
        }
    }

    @PatchMapping("/pause")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> pauseSimulation() {
        try {
            getSecuritySystemSimulation().pause();
            return ResponseEntity.ok(new MessageResponse("paused successfully"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("simulation couldn't be paused");
        }
    }

    @PatchMapping("/resume")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> resumeSimulation() {
        try {
            getSecuritySystemSimulation().resume();
            return ResponseEntity.ok(new MessageResponse("resumed successfully"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("simulation couldn't be resumed");
        }
    }


    @Autowired()
    public void setSecuritySystemSimulation(SecuritySystemSimulation securitySystemSimulation) {
        this.securitySystemSimulation = securitySystemSimulation;
    }
}
