package com.revature.assignforce.service;

import java.util.List;
import java.util.Optional;

import com.revature.assignforce.beans.Location;

public interface LocationService {
	List<Location> getAll();

	Optional<Location> findById(int id);

	Location update(Location t);

	Location create(Location t);

	void delete(int id);
}
