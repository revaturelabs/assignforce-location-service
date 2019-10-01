package com.revature.assignforce.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.assignforce.beans.Building;
import com.revature.assignforce.beans.Room;
import com.revature.assignforce.service.BuildingService;
import com.revature.assignforce.service.RoomService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * A controller for retrieving, creating, updating and deleting Room information
 *
 */
@RestController
@RequestMapping("/room")
@Api(value = "RoomController", 
description = "REST APIs for retrieving, creating, updating and deleting Room information")
public class RoomController {
	
	@Autowired
	RoomService roomService;

	/**
	 * 
	 * @return		A List of All Rooms
	 */
	@GetMapping
	@ApiOperation(value = "Get All Rooms", response = List.class, tags = "getAllRooms")
	public List<Room> getAll() {
		return roomService.getAll();
	}

	/**
	 * 
	 * @param 	id	A Room Id of object to be retrieved
	 * @return		A Room ResponseEntity
	 * @see		Room
	 * @see		ResponseEntity
	 */
	@GetMapping(value = "{id}")
	@ApiOperation(value = "Get Specific Room By Id", response = ResponseEntity.class, tags = "getRoom")
	public ResponseEntity<Room> getById(@PathVariable("id") int id) {
		Optional<Room> a = roomService.findById(id);
		if (!a.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(a.get(), HttpStatus.OK);
	}

	/**
	 * 
	 * @param 	a	A New Room object
	 * @return		A Room ResponseEntity
	 * @see		Room
	 * @see		ResponseEntity
	 */
	@PostMapping
	@ApiOperation(value = "Add a Room", response = ResponseEntity.class, tags = "addRoom")
	public ResponseEntity<Room> add(@RequestBody Room a) {
		a = roomService.create(a);
		if (a == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(a, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param 	a	An Edited Room object
	 * @return		A Room ResponseEntity
	 * @see		Room
	 * @see		ResponseEntity
	 */
	@PutMapping("{id}")
	@ApiOperation(value = "Update a Room", response = ResponseEntity.class, tags = "updateRoom")
	public ResponseEntity<Room> update(@RequestBody Room a) {
		a = roomService.update(a);
		if (a == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(a, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param 	id	A Room Id of object to be deleted
	 * @return		A Room ResponseEntity
	 * @see		Room
	 * @see		ResponseEntity
	 */
	@DeleteMapping(value = "{id}")
	@ApiOperation(value = "Delete a Room", response = ResponseEntity.class, tags = "deleteRoom")
	public ResponseEntity<Room> delete(@PathVariable("id") int id) {
		roomService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
