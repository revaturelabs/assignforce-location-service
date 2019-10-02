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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



/**
 * 
 * A controller for retrieving, creating, updating and deleting Building information
 *
 */
@RestController
@RequestMapping("/building")
@Api(value = "BuildingController", 
description = "REST APIs for retrieving, creating, updating and deleting Building information")
public class BuildingController {
	
	@Autowired
	BuildingService buildingService;

	/**
	 * 
	 * @return		A List of All Buildings
	 */
	@GetMapping
	@ApiOperation(value = "Get All Buildings", response = List.class, tags = "getAllBuildings")
	public List<Building> getAll() {
		return buildingService.getAll();
	}

	/**
	 * 
	 * @param 	id	A Building Id of object to be retrieved
	 * @return		A Building ResponseEntity
	 * @see		Building
	 * @see		ResponseEntity
	 */
	@GetMapping(value = "{id}")
	@ApiOperation(value = "Get Specific Building by Id", response = ResponseEntity.class, tags = "getById")
	public ResponseEntity<Building> getById(@PathVariable("id") int id) {
		Optional<Building> a = buildingService.findById(id);
		if (!a.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(a.get(), HttpStatus.OK);
	}

	/**
	 * 
	 * @param 	a	A New Building object
	 * @return		A Building ResponseEntity
	 * @see		Building
	 * @see		ResponseEntity
	 */
	@PostMapping
	@ApiOperation(value = "Add a Building", response = ResponseEntity.class, tags = "add")
	public ResponseEntity<Building> add(@RequestBody Building a) {
		a = buildingService.create(a);
		if (a == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(a, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param 	a	An Edited Building object
	 * @return		A Building ResponseEntity
	 * @see		Building
	 * @see		ResponseEntity
	 */
	@PutMapping
	@ApiOperation(value = "Update Building information", response = ResponseEntity.class, tags = "update")
	public ResponseEntity<Building> update(@RequestBody Building a) {
		a = buildingService.update(a);
		if (a == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(a, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param 	id	A Building Id of object to be deleted
	 * @return		A Building ResponseEntity
	 * @see		Building
	 * @see		ResponseEntity
	 */
	@DeleteMapping(value = "{id}")
	@ApiOperation(value = "Delete Building information", response = ResponseEntity.class, tags = "delete")
	public ResponseEntity<Building> delete(@PathVariable("id") int id) {
		buildingService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
