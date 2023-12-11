package com.securitySimulator.controller;

import com.securitySimulator.payload.response.MessageResponse;
import com.securitySimulator.repository.BuildingRepository;
import com.securitySimulator.services.SecuritySystemSimulation;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;



@RestController
@RequestMapping("/api/simulation")
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_USER')")
public class SimulationController {
    @Autowired
    BuildingRepository buildingRepository;

    @Getter
    private SecuritySystemSimulation securitySystemSimulation;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> configureSimulation(){

        try {
            if(getSecuritySystemSimulation().configureSimulation()){
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (IOException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("input/output socket error");
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
