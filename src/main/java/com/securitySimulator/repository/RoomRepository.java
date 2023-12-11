package com.securitySimulator.repository;

import com.securitySimulator.model.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
