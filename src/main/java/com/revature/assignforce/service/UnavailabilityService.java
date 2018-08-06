package com.revature.assignforce.service;

import java.util.List;
import java.util.Optional;

import com.revature.assignforce.beans.Unavailability;

public interface UnavailabilityService {
	

	List<Unavailability> getAll();

	Optional<Unavailability> findById(int id);

	Unavailability update(Unavailability t);

	Unavailability create(Unavailability t);

	void delete(int id);


}
