package com.revature.assignforce.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.assignforce.beans.Event;
import com.revature.assignforce.service.EventService;

@RestController
@RequestMapping("/event")
public class EventController {

	@Autowired
	EventService eventService;

	// findAll
	@GetMapping
	public List<Event> getAll() {

		return eventService.getAll();

	}

	// findOne
	@GetMapping(value = "{id}")
	public ResponseEntity<Event> getById(@PathVariable("id") int id) {
		Optional<Event> e = eventService.findById(id);
		if (e == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(e.get(), HttpStatus.OK);
	}

	// create
	@PostMapping
	public ResponseEntity<Event> add(@RequestBody Event e) {
		e = eventService.create(e);
		if (e == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(e, HttpStatus.CREATED);
	}

	// update
	@PutMapping("{id}")
	public ResponseEntity<Event> update(@RequestBody Event e) {
		e = eventService.update(e);
		if (e == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(e, HttpStatus.CREATED);
	}

	//delete
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Event> delete(@PathVariable("id") int id) {
		eventService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
