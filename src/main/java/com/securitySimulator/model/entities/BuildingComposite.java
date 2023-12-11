package com.securitySimulator.model.entities;

import com.securitySimulator.model.sensor.Sensor;

import java.util.ArrayList;
import java.util.List;

public abstract class BuildingComposite {

    public abstract List<Sensor> getAllSensors();
    public abstract String getFullAddress();
}
