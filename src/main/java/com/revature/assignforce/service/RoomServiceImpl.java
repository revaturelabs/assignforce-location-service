package com.revature.assignforce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.assignforce.beans.Building;
import com.revature.assignforce.beans.Room;
import com.revature.assignforce.repos.RoomRepository;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomRepository roomRepository;

	@Override
	public List<Room> getAll() {
		return roomRepository.findAll();
	}

	@Override
	public Optional<Room> findById(int id) {
		return roomRepository.findById(id);
	}

	@Override
	public Room update(Room t) {
		return roomRepository.save(t);
	}

	@Override
	public Room create(Room t) {
		return roomRepository.save(t);
	}

	@Override
	public void delete(int id) {
		roomRepository.deleteById(id);
	}

}
