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

@RestController
@RequestMapping("/room")
public class RoomController {
	
	@Autowired
	RoomService roomService;

	// findAll
	@GetMapping
	public List<Room> getAll() {
		
		
		return roomService.getAll();
	}

	// findOne
	@GetMapping(value = "{id}")
	public ResponseEntity<Room> getById(@PathVariable("id") int id) {
		Optional<Room> a = roomService.findById(id);
		if (!a.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(a.get(), HttpStatus.OK);
	}

	// create
	@PostMapping
	public ResponseEntity<Room> add(@RequestBody Room a) {
		a = roomService.create(a);
		if (a == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(a, HttpStatus.CREATED);
	}

	// update
	@PutMapping
	public ResponseEntity<Room> update(@RequestBody Room a) {
		a = roomService.update(a);
		if (a == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(a, HttpStatus.CREATED);
	}

	// delete
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Room> delete(@PathVariable("id") int id) {
		roomService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
