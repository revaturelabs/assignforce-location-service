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

@RestController
@RequestMapping("/unavailabilities")
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

	/** create, also need to send room id through the url because we cannot set the room field in 
	Unavailability (because the room field is updatable=false and insertable=false), the only possible way is to
	 set the roomObject to finding the room by id and then setting roomObject to the object returned (which will also change the room 
	field in Unavailability to the primary key of the room) 
	*/
	@PostMapping(value = "{roomId}")
	public ResponseEntity<Unavailability> add(@PathVariable("roomId") int roomId, @RequestBody Unavailability a) {
	
		//find by room to add to Unavailability object
		
		a = unavailabilityService.addUnavailability(a, roomId);
		if (a == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(a, HttpStatus.CREATED);
	}

	@PutMapping(value = "{roomId}")
	public ResponseEntity<Unavailability> update(@PathVariable("roomId") int roomId, @RequestBody Unavailability a) {
		
		//find the room to add to Unavailability object
		Room theRoom = (roomService.findById(roomId).orElse(null));
		a.setRoomObject(theRoom);
		
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
