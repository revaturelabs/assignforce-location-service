package com.revature.assignforce.service;

import java.util.List;
import java.util.Optional;

import com.revature.assignforce.beans.Building;
import com.revature.assignforce.beans.Location;

public interface BuildingService {
	
	List<Building> getAll();

	Optional<Building> findById(int id);

	Building update(Building t);

	Building create(Building t);

	void delete(int id);

}
