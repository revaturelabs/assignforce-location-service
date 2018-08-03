package com.revature.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.assignforce.beans.Building;
import com.revature.assignforce.beans.Location;
import com.revature.assignforce.beans.Room;
import com.revature.assignforce.beans.Unavailability;
import com.revature.assignforce.controllers.LocationController;
import com.revature.assignforce.repos.LocationRepository;
import com.revature.assignforce.service.LocationService;
import com.revature.assignforce.service.LocationServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LocationControllerTest {

	@Configuration
	static class LocationServiceTestContextConfiguration {
	@Bean
	public LocationService LocationService() {
		return new LocationServiceImpl();
		}
	@Bean
	public LocationRepository LocationRepository() {
		return Mockito.mock(LocationRepository.class);
		}
	@Bean
	public LocationController LocationController() {
		return new LocationController();
		}
	}
	
	@Autowired
	private LocationRepository locationRepository;
	@Autowired
	private LocationController locationController;
	
	@Test
	public void getAllTest() {
		Set<Unavailability> testSet = new TreeSet<Unavailability>();
		Room r1 = new Room(1, "Class A", testSet);
		Room r2 = new Room(2, "Class B", testSet);
		HashSet<Room> roomSet = new HashSet<Room>();
		roomSet.add(r1);
		roomSet.add(r2);
		Building b1 = new Building(1, true, "BuildingOne", roomSet);
		Building b2 = new Building(2, false, "BuildingTwo", roomSet);
		Building b3 = new Building(4, true, "BuildingFour", roomSet);
		HashSet<Building> buildingSet = new HashSet<Building>();
		buildingSet.add(b1);
		buildingSet.add(b2);
		buildingSet.add(b3);
		Location l1 = new Location(1, "Reston", "Reston", "VA", true, buildingSet);
		Location l2 = new Location(6, "Tampa", "Tampa", "FL", false, buildingSet);
		Location l3 = new Location(8, "Detroit", "Detroit", "MI", false, buildingSet);
		List<Location> locationList = new ArrayList<Location>();
		locationList.add(l1);
		locationList.add(l2);
		locationList.add(l3);
		Mockito.when(locationRepository.findAll()).thenReturn(locationList);
		List<Location> testList = locationController.getAll();
		assertTrue(testList.size() == 3);
	}
	
	@Test
	public void getByIdTestOk() {
		Set<Unavailability> testSet = new TreeSet<Unavailability>();
		Room r1 = new Room(1, "Class A", testSet);
		Room r2 = new Room(2, "Class B", testSet);
		HashSet<Room> roomSet = new HashSet<Room>();
		roomSet.add(r1);
		roomSet.add(r2);
		Building b1 = new Building(1, true, "BuildingOne", roomSet);
		Building b2 = new Building(2, false, "BuildingTwo", roomSet);
		Building b3 = new Building(4, true, "BuildingFour", roomSet);
		HashSet<Building> buildingSet = new HashSet<Building>();
		buildingSet.add(b1);
		buildingSet.add(b2);
		buildingSet.add(b3);
		Location l1 = new Location(5, "Reston", "Reston", "VA", true, buildingSet);
		Optional<Location> op1 = Optional.ofNullable(l1);
		Mockito.when(locationRepository.findById(5)).thenReturn(op1);
		ResponseEntity<Location> reTest = locationController.getById(5);
		assertTrue(reTest.getBody().getId() == 5 && reTest.getStatusCode() == HttpStatus.OK);
	}
	
	@Test
	public void getByIdTestNotFound() {
		ResponseEntity<Location> reTest = locationController.getById(7);
		assertTrue(reTest.getStatusCode() == HttpStatus.NOT_FOUND);
	}
	
	@Test
	public void addTestCreated() {
		Set<Unavailability> testSet = new TreeSet<Unavailability>();
		Room r1 = new Room(1, "Class A", testSet);
		Room r2 = new Room(2, "Class B", testSet);
		HashSet<Room> roomSet = new HashSet<Room>();
		roomSet.add(r1);
		roomSet.add(r2);
		Building b1 = new Building(1, true, "BuildingOne", roomSet);
		Building b2 = new Building(2, false, "BuildingTwo", roomSet);
		Building b3 = new Building(4, true, "BuildingFour", roomSet);
		HashSet<Building> buildingSet = new HashSet<Building>();
		buildingSet.add(b1);
		buildingSet.add(b2);
		buildingSet.add(b3);
		Location l1 = new Location(8, "San Diego", "San Diego", "CA", true, buildingSet);
		Mockito.when(locationRepository.save(l1)).thenReturn(l1);
		ResponseEntity<Location> reTest = locationController.add(l1);
		assertTrue(reTest.getBody().getId() == 8 && reTest.getStatusCode() == HttpStatus.CREATED);
	}
	
	@Test
	public void addTestBadRequest() {
		Set<Unavailability> testSet = new TreeSet<Unavailability>();
		Room r1 = new Room(1, "Class A", testSet);
		Room r2 = new Room(2, "Class B", testSet);
		HashSet<Room> roomSet = new HashSet<Room>();
		roomSet.add(r1);
		roomSet.add(r2);
		Building b1 = new Building(1, true, "BuildingOne", roomSet);
		Building b2 = new Building(2, false, "BuildingTwo", roomSet);
		Building b3 = new Building(4, true, "BuildingFour", roomSet);
		HashSet<Building> buildingSet = new HashSet<Building>();
		buildingSet.add(b1);
		buildingSet.add(b2);
		buildingSet.add(b3);
		Location l1 = new Location(13, "Scranton", "Scranton", "PA", false, buildingSet);
		ResponseEntity<Location> reTest = locationController.add(l1);
		assertTrue(reTest.getStatusCode() == HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void updateTestOK() {
		Set<Unavailability> testSet = new TreeSet<Unavailability>();
		Room r1 = new Room(1, "Class A", testSet);
		Room r2 = new Room(2, "Class B", testSet);
		HashSet<Room> roomSet = new HashSet<Room>();
		roomSet.add(r1);
		roomSet.add(r2);
		Building b1 = new Building(1, true, "BuildingOne", roomSet);
		Building b2 = new Building(2, false, "BuildingTwo", roomSet);
		Building b3 = new Building(4, true, "BuildingFour", roomSet);
		HashSet<Building> buildingSet = new HashSet<Building>();
		buildingSet.add(b1);
		buildingSet.add(b2);
		buildingSet.add(b3);
		Location l1 = new Location(8, "San Diego", "San Diego", "CA", true, buildingSet);
		l1.setCity("Phoenix");
		l1.setName("Phoenix");
		l1.setState("AZ");
		Mockito.when(locationRepository.save(l1)).thenReturn(l1);
		ResponseEntity<Location> reTest = locationController.update(l1);
		assertTrue(reTest.getBody().getState().equals("AZ") && reTest.getStatusCode() == HttpStatus.CREATED);
	}
	
	@Test
	public void updateTestBadRequest() {
		Set<Unavailability> testSet = new TreeSet<Unavailability>();
		Room r1 = new Room(1, "Class A", testSet);
		Room r2 = new Room(2, "Class B", testSet);
		HashSet<Room> roomSet = new HashSet<Room>();
		roomSet.add(r1);
		roomSet.add(r2);
		Building b1 = new Building(1, true, "BuildingOne", roomSet);
		Building b2 = new Building(2, false, "BuildingTwo", roomSet);
		Building b3 = new Building(4, true, "BuildingFour", roomSet);
		HashSet<Building> buildingSet = new HashSet<Building>();
		buildingSet.add(b1);
		buildingSet.add(b2);
		buildingSet.add(b3);
		Location l1 = new Location(19, "San Diego", "San Diego", "CA", true, buildingSet);
		l1.setCity("Phoenix");
		l1.setName("Phoenix");
		l1.setState("AZ");
		ResponseEntity<Location> reTest = locationController.update(l1);
		assertTrue(reTest.getStatusCode() == HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void deleteTest() {
		Mockito.doNothing().when(locationRepository).deleteById(25);
		ResponseEntity<Location> reTest = locationController.delete(25);
		assertTrue(reTest.getStatusCode() == HttpStatus.OK);
	}

}
