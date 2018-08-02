package com.revature.assignforce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.assignforce.beans.Unavailability;
import com.revature.assignforce.repos.UnavailabilityRepository;

/**
 * 
 * @author Shannon
 *
 */

@Service
public class UnavailabilityService {
	
	@Autowired
	private UnavailabilityRepository unavailRepo;
	
	public List<Unavailability> getAll() {
		return unavailRepo.findAll();
	}

	public Optional<Unavailability> findById(int id) {
		return unavailRepo.findById(id);
	}

	public Unavailability update(Unavailability u) {
		return unavailRepo.save(u);
	}

	public Unavailability create(Unavailability u) {
		return unavailRepo.save(u);
	}

	public void delete(int id) {
		unavailRepo.deleteById(id);
	}
}
