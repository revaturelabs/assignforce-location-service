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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.assignforce.beans.Room;
import com.revature.assignforce.beans.Unavailability;
import com.revature.assignforce.service.RoomService;
import com.revature.assignforce.service.UnavailabilityService;

@CrossOrigin
@RestController
@RequestMapping("/unavailable")
public class UnavailabilityController {
	
	@Autowired
	UnavailabilityService unavailabilityService;
	@Autowired
	RoomService roomService;

	// findAll
	@GetMapping
	public List<Unavailability> getAll() {
		
		
		
		return unavailabilityService.getAll();
	}

	// findOne
	@GetMapping(value = "{id}")
	public ResponseEntity<Unavailability> getById(@PathVariable("id") int id) {
		
		
		Optional<Unavailability> a = unavailabilityService.findById(id);
		if (!a.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(a.get(), HttpStatus.OK);
	}

	// create
	@PostMapping
	public ResponseEntity<Unavailability> add(@RequestHeader(value = "RoomId" ) String roomId, @RequestBody Unavailability a) {
	
		int myRoomId = Integer.parseInt(roomId);
		Room theRoom = (roomService.findById(myRoomId).orElse(null));
		a.setNotroom(theRoom);
		
		a = unavailabilityService.create(a);
		if (a == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(a, HttpStatus.CREATED);
	}

	// update
	@PutMapping
	public ResponseEntity<Unavailability> update( @RequestHeader(value = "RoomId" )  String roomId, @RequestBody Unavailability a) {
		
		int myRoomId = Integer.parseInt(roomId);
		Room theRoom = (roomService.findById(myRoomId).orElse(null));
		a.setNotroom(theRoom);
		
		a = unavailabilityService.update(a);
		if (a == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(a, HttpStatus.CREATED);
	}

	// delete
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Unavailability> delete(@PathVariable("id") int id) {
		unavailabilityService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}


}
