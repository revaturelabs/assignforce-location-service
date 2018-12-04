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
import com.revature.assignforce.beans.Location;
import com.revature.assignforce.service.BuildingService;
import com.revature.assignforce.service.LocationService;

@RestController
@RequestMapping("/building")
public class BuildingController {
	
	@Autowired
	BuildingService buildingService;

	// findAll
	@GetMapping
	public List<Building> getAll() {
		
		
		return buildingService.getAll();
	}

	// findOne
	@GetMapping(value = "{id}")
	public ResponseEntity<Building> getById(@PathVariable("id") int id) {
		Optional<Building> a = buildingService.findById(id);
		if (!a.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(a.get(), HttpStatus.OK);
	}

	// create
	@PostMapping
	public ResponseEntity<Building> add(@RequestBody Building a) {
		a = buildingService.create(a);
		if (a == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(a, HttpStatus.CREATED);
	}

	// update
	@PutMapping(value = "{id}")
	public ResponseEntity<Building> update(@PathVariable("id") int id, @RequestBody Building a) {
		a = buildingService.update(a);
		if (a == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(a, HttpStatus.CREATED);
	}

	// delete
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Building> delete(@PathVariable("id") int id) {
		buildingService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
