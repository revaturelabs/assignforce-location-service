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

import io.swagger.annotations.ApiOperation;

/**
 * 
 * A controller for retrieving, updating and deleting Unavailable Rooms and
 * assigning Unavailability to Rooms
 *
 */
@RestController
@RequestMapping("/unavailabilities")
public class UnavailabilityController {
	
	@Autowired
	UnavailabilityService unavailabilityService;
	
	@Autowired
	RoomService roomService;

	/**
	 * 
	 * @return		A List of All Unavailable Rooms
	 */
	@GetMapping
	@ApiOperation(value = "Get All Unavailable Rooms", 
	response = List.class, tags = "getAllUnavailableRooms")
	public List<Unavailability> getAll() {
		return unavailabilityService.getAll();
	}

	
	/**
	 * 
	 * @param 	id	An Unavailability Id of object to be retrieved
	 * @return      An Unavailability ResponseEntity
	 * @see		Unavailability
	 * @see		ResponseEntity
	 */
	@GetMapping(value = "{id}")
	@ApiOperation(value = "Get an Unavailable Room By Id", 
	response = ResponseEntity.class, tags = "getUnavailableRoom")
	public ResponseEntity<Unavailability> getById(@PathVariable("id") int id) {
		
		
		Optional<Unavailability> a = unavailabilityService.findById(id);
		if (!a.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(a.get(), HttpStatus.OK);
	}

	/* create, also need to send room id through the url because we cannot set the room field in 
	Unavailability (because the room field is updatable=false and insertable=false), the only possible way is to
	 set the roomObject to finding the room by id and then setting roomObject to the object returned (which will also change the room 
	field in Unavailability to the primary key of the room) 
	*/
	
	/**
	 * Find by room to add to Unavailability object
	 * 
	 * @param 	a	A New Unavailability object
	 * @return		An Unavailability ResponseEntity
	 * @see		Unavailability
	 * @see		ResponseEntity
	 */
	@PostMapping
	@ApiOperation(value = "Add Unavailability to Room", response = ResponseEntity.class, tags = "addUnavailabilityToRoom")
	public ResponseEntity<Unavailability> add(@RequestBody Unavailability a) {
		a = unavailabilityService.addUnavailability(a);
		if (a == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(a, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param uaId	Unavailability Id of object to be edited
	 * @param a		An Edited Unavailability object
	 * @return		An Unavailability ResponseEntity
	 * @see		Unavailability
	 * @see		ResponseEntity
	 */
	@PutMapping(value = "{uaId}")
	@ApiOperation(value = "Update Unavailability of Room", response = ResponseEntity.class, tags = "updateUnavailabilityOfRoom")
	public ResponseEntity<Unavailability> update(@PathVariable("uaId") int uaId, @RequestBody Unavailability a) {
		a = unavailabilityService.update(a);
		if (a == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(a, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param id	An Unavailability Id of object to be deleted
	 * @return		An Unavailability ResponseEntity
	 * @see		Unavailability
	 * @see		ResponseEntity
	 */
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Unavailability> delete(@PathVariable("id") int id) {
		unavailabilityService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}


}
