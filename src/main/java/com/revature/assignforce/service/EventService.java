package com.revature.assignforce.service;

import java.util.List;

import com.revature.assignforce.beans.Event;

public interface EventService {

	List<Event> getAll();
	
	Event findById(int id);
	
	Event update(Event e);
	
	Event create(Event e);
	
	void delete(int id);
}
