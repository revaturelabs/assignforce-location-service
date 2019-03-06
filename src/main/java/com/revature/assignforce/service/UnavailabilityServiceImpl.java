package com.revature.assignforce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.assignforce.beans.Room;
import com.revature.assignforce.beans.Unavailability;

import com.revature.assignforce.repos.UnavailabilityRepository;

@Service
public class UnavailabilityServiceImpl  implements UnavailabilityService {

	
	@Autowired
	private UnavailabilityRepository unavailabilityRepository;
	
	@Autowired
	private RoomService roomService;


	@Override
	public List<Unavailability> getAll() {
		return unavailabilityRepository.findAll();
	}
	
	
	@Override
	public Optional<Unavailability> findById(int id) {
		return unavailabilityRepository.findById(id);
	}
	@Override
	public Unavailability update(Unavailability t) {
		return unavailabilityRepository.save(t);
	}

	@Override
	public Unavailability create(Unavailability t) {
		return unavailabilityRepository.save(t);
	}

	@Override
	public void delete(int id) {
		unavailabilityRepository.deleteById(id);
	}
	
	public Unavailability addUnavailability(Unavailability t) {
//		Room theRoom = (roomService.findById(roomId).orElse(null));
		return create(t);

	}
	
	public Unavailability updateUnavailability(Unavailability t) {
//		Room theRoom = (roomService.findById(roomId).orElse(null));
		return update(t);
		
	}
	
	
}
