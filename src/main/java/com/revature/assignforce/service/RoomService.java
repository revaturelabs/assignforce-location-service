package com.revature.assignforce.service;

import java.util.List;
import java.util.Optional;


import com.revature.assignforce.beans.Room;

public interface RoomService {
	
	List<Room> getAll();

	Optional<Room> findById(int id);

	Room update(Room t);

	Room create(Room t);

	void delete(int id);

}