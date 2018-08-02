package com.revature.assignforce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.assignforce.beans.Room;
import com.revature.assignforce.repos.RoomRepository;

public class RoomService {
	
	@Autowired
	private RoomRepository roomRepo;
	
	public List<Room> getAll() {
		return roomRepo.findAll();
	}
	
	public Optional<Room> findById(int id) {
		return roomRepo.findById(id);
	}
	
	public Room update(Room r) {
		return roomRepo.save(r);
	}
	
	public Room create(Room r) {
		return roomRepo.save(r);
	}
	
	public void delete(int id) {
		roomRepo.deleteById(id);
	}
	
}
