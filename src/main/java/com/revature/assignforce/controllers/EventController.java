package com.revature.assignforce.controllers;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * A controller for retrieving, creating, updating and deleting Event information
 *
 */
@RestController
@RequestMapping("/event")
@Api(value = "BuildingController", 
description = "REST APIs for retrieving, creating, updating and deleting Event information")
public class EventController {

	@Autowired
	EventService eventService;

	/**
	 * 
	 * @return		A List of All Events
	 */
	@GetMapping
	@ApiOperation(value = "Get All Events", response = List.class, tags = "getAllEvents")
	@PreAuthorize("isAuthenticated() and hasAnyRole('SVP of Technology','Trainer','Manager of Technology','Center Head')")
	public List<Event> getAll() {
		return eventService.getAll();
	}

	/**
	 * 
	 * @param 	id	An Event Id of object to be retrieved
	 * @return		An Event ResponseEntity
	 * @see		Event
	 * @see		ResponseEntity
	 */
	@GetMapping(value = "{id}")
	@ApiOperation(value = "Get Specific Event by Id", response = ResponseEntity.class, tags = "getEvent")
	@PreAuthorize("isAuthenticated() and hasAnyRole('SVP of Technology','Trainer','Manager of Technology','Center Head')")
	public ResponseEntity<Event> getById(@PathVariable("id") int id) {
		Optional<Event> e = eventService.findById(id);
		if (e == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(e.get(), HttpStatus.OK);
	}

	/**
	 * 
	 * @param	 e	A New Event object
	 * @return		An Event ResponseEntity
	 * @see		Event
	 * @see		ResponseEntity
	 */
	@PostMapping
	@ApiOperation(value = "Create an Event", response = ResponseEntity.class, tags = "addEvent")
    @PreAuthorize("isAuthenticated() and hasAnyRole('SVP of Technology', 'Manager of Technology')")
	public ResponseEntity<Event> add(@RequestBody Event e) {
		e = eventService.create(e);
		if (e == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(e, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param 	e	An Edited Event object
	 * @return		An Event ResponseEntity
	 * @see		Event
	 * @see		ResponseEntity
	 */
	@PutMapping("{id}")
	@ApiOperation(value = "Update an Event", response = ResponseEntity.class, tags = "updateEvent")
    @PreAuthorize("isAuthenticated() and hasAnyRole('SVP of Technology', 'Manager of Technology')")
	public ResponseEntity<Event> update(@RequestBody Event e) {
		e = eventService.update(e);
		if (e == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(e, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param 	id	An Event Id of object to be deleted
	 * @return		An Event ResponseEntity
	 * @see		Event
	 * @see		ResponseEntity
	 */
	@DeleteMapping(value = "{id}")
	@ApiOperation(value = "Delete an Event information", 
	response = ResponseEntity.class, tags = "deleteEvent")
    @PreAuthorize("isAuthenticated() and hasAnyRole('SVP of Technology', 'Manager of Technology')")
	public ResponseEntity<Event> delete(@PathVariable("id") int id) {
		eventService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
