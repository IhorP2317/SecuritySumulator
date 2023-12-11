package com.securitySimulator.controller;

import com.securitySimulator.helpers.RoomHelper;
import com.securitySimulator.model.entities.Room;
import com.securitySimulator.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rooms")
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_USER')")
@ComponentScan
public class RoomController {

    @Autowired
    RoomRepository roomRepository;

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        try {
            List<Room> Rooms = new ArrayList<>(roomRepository.findAll());

            if (Rooms.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(Rooms, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable("id") long id) {
        Optional<Room> RoomData = roomRepository.findById(id);

        if (RoomData.isPresent()) {
            return new ResponseEntity<>(RoomData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> createRoom(@RequestBody Room room) {
        try {
            Room newRoom = new Room(
                    room.getId(),
                    room.getSquare(),
                    room.getAmountOfDoors(),
                    room.getAmountOfWindows(),
                    room.getNormativeType(),
                    new ArrayList<>()
            );

            RoomHelper.populateSensorsForRoom(newRoom);

            Room _room = roomRepository.save(newRoom);
            return new ResponseEntity<>(_room.toString(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable("id") long id, @RequestBody Room Room) {
        Optional<Room> RoomData = roomRepository.findById(id);

        if (RoomData.isPresent()) {
            Room _Room = RoomData.get();
            _Room.setSquare(Room.getSquare());
            _Room.setAmountOfDoors(Room.getAmountOfDoors());
            _Room.setAmountOfWindows(Room.getAmountOfWindows());
            _Room.setNormativeType(Room.getNormativeType());
            return new ResponseEntity<>(roomRepository.save(_Room), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRoom(@PathVariable("id") long id) {
        try {
            roomRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllRooms() {
        try {
            roomRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}