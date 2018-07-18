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
import org.springframework.web.bind.annotation.RestController;

import com.revature.assignforce.beans.Location;
import com.revature.assignforce.service.LocationService;

@CrossOrigin
@RestController
public class LocationController {

	@Autowired
	LocationService locationService;

	// findAll
	@GetMapping
	public List<Location> getAll() {
		return locationService.getAll();
	}

	// findOne
	@GetMapping(value = "{id}")
	public ResponseEntity<Location> getById(@PathVariable("id") int id) {
		Optional<Location> a = locationService.findById(id);
		if (!a.isPresent())
			return new ResponseEntity<Location>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Location>(a.get(), HttpStatus.OK);
	}

	// create
	@PostMapping
	public ResponseEntity<Location> add(@RequestBody Location a) {
		a = locationService.create(a);
		if (a == null)
			return new ResponseEntity<Location>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Location>(a, HttpStatus.CREATED);
	}

	// update
	@PutMapping
	public ResponseEntity<Location> update(@RequestBody Location a) {
		a = locationService.update(a);
		if (a == null)
			return new ResponseEntity<Location>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Location>(a, HttpStatus.CREATED);
	}

	// delete
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Location> delete(@PathVariable("id") int id) {
		locationService.delete(id);
		return new ResponseEntity<Location>(HttpStatus.OK);
	}

}
