package com.revature.assignforce.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * A controller for retrieving, creating, updating and deleting Location information
 *
 */
@RestController
@Api(value = "LocationController", 
description = "REST APIs for retrieving, creating, updating and deleting Location information")
public class LocationController {

	@Autowired
	LocationService locationService;

	/**
	 * 
	 * @return		A List of All Locations
	 */
	@GetMapping
	@ApiOperation(value = "Get All Locations", response = List.class, tags = "getAllLocations")
	@PreAuthorize("isAuthenticated() and hasAnyRole('SVP of Technology','Trainer','Manager of Technology','Center Head')")
	public List<Location> getAll() {
		return locationService.getAll();
	}

	/**
	 * 
	 * @param 	id	A Location Id of object to be retrieved
	 * @return		A Location ResponseEntity
	 * @see		Location
	 * @see		ResponseEntity
	 */
	@GetMapping(value = "{id}")
	@ApiOperation(value = "Get Specific Location by Id", 
	response = ResponseEntity.class, tags = "getLocation")
	@PreAuthorize("isAuthenticated() and hasAnyRole('SVP of Technology','Trainer','Manager of Technology','Center Head')")
	public ResponseEntity<Location> getById(@PathVariable("id") int id) {
		Optional<Location> a = locationService.findById(id);
		if (!a.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(a.get(), HttpStatus.OK);
	}

	/**
	 * 
	 * @param 	a	A New Location object
	 * @return		A Location ResponseEntity
	 * @see		Location
	 * @see		ResponseEntity
	 */
	@PostMapping
	@ApiOperation(value = "Add a Location", 
	response = ResponseEntity.class, tags = "addLocation")
	@PreAuthorize("isAuthenticated() and hasAnyRole('SVP of Technology','Manager of Technology','Center Head')")
	public ResponseEntity<Location> add(@RequestBody Location a) {
		a = locationService.create(a);
		if (a == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(a, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param 	a	An Edited Location object
	 * @return		A Location ResponseEntity
	 * @see		Location
	 * @see		ResponseEntity
	 */
	@PutMapping
	@ApiOperation(value = "Update a Location", 
	response = ResponseEntity.class, tags = "updateLocation")
	@PreAuthorize("isAuthenticated() and hasAnyRole('SVP of Technology','Manager of Technology','Center Head')")
	public ResponseEntity<Location> update(@RequestBody Location a) {
		a = locationService.update(a);
		if (a == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(a, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param 	id	A Location Id of object to be deleted
	 * @return		A Location ResponseEntity
	 * @see		Location
	 * @see		ResponseEntity
	 */
	@DeleteMapping(value = "{id}")
	@ApiOperation(value = "Delete a Location", 
	response = ResponseEntity.class, tags = "deleteLocation")
	@PreAuthorize("isAuthenticated() and hasAnyRole('SVP of Technology','Manager of Technology','Center Head')")
	public ResponseEntity<Location> delete(@PathVariable("id") int id) {
		locationService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
