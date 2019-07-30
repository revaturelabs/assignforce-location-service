package com.revature.assignforce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.assignforce.beans.Event;
import com.revature.assignforce.repos.EventRepository;

public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository eventRepository;
	
	@Override
	public List<Event> getAll() {
		return eventRepository.findAll();
	}

	@Override
	public Optional<Event> findById(int id) {
		return eventRepository.findById(id);
	}

	@Override
	public Event update(Event e) {
		return eventRepository.save(e);
	}

	@Override
	public Event create(Event e) {
		
		List<Event> allEvents = getAll();
		
		for (Event event : allEvents) {
			if (event.getRoomID() == e.getRoomID()) {
				if ((event.getStartDate().isBefore(e.getStartDate()) && event.getEndDate().isBefore(e.getEndDate()) ||
						event.getStartDate().isAfter(e.getStartDate()) && event.getStartDate().isBefore(e.getEndDate()))) {
					return null;
				}
			}
		}
		
		return eventRepository.save(e);
	}

	@Override
	public void delete(int id) {
		eventRepository.deleteById(id);
	}

}
