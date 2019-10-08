package com.revature.assignforce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.revature.assignforce.beans.Location;
import com.revature.assignforce.repos.LocationRepository;

@Service

/*
 * Only a user with an SVP role should be able to 
 * access these methods.
 */
//@PreAuthorize("hasRole('SVP')")
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepository locationRepository;

	@Override
	public List<Location> getAll() {
		return locationRepository.findAll();
	}

	@Override
	public Optional<Location> findById(int id) {
		return locationRepository.findById(id);
	}

	@Override
	public Location update(Location t) {
		return locationRepository.save(t);
	}

	@Override
	public Location create(Location t) {
		return locationRepository.save(t);
	}

	@Override
	public void delete(int id) {
		locationRepository.deleteById(id);
	}
}
