package com.hive.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hive.models.Room;

@Repository
public interface RoomRepo extends JpaRepository<Room, Integer>{

	List<Room> findByPropertyId(int id);

}
