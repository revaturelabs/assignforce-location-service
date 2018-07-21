package com.revature.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.assignforce.beans.Building;
import com.revature.assignforce.beans.Location;
import com.revature.assignforce.beans.Room;
import com.revature.assignforce.repos.LocationRepository;
import com.revature.assignforce.service.LocationService;
import com.revature.assignforce.service.LocationServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LocationServiceImplTest {

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
	}
	
	@Autowired
	private LocationService locationService;
	@Autowired
	private LocationRepository locationRepository;
	
	@Test
	public void getAllTest() {
		Room r1 = new Room(1, "Class A");
		Room r2 = new Room(2, "Class B");
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
		List<Location> locationList = new ArrayList<Location>();
		locationList.add(l1);
		locationList.add(l2);
		Mockito.when(locationRepository.findAll()).thenReturn(locationList);
		List<Location> testList = locationService.getAll();
		assertTrue(testList.size() == 2);
	}
	
	@Test
	public void findByIdTest() {
		Room r1 = new Room(1, "Class A");
		Room r2 = new Room(2, "Class B");
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
		Location l1 = new Location(4, "Chicago", "Chicago", "IL", false, buildingSet);
		Optional<Location> op1 = Optional.ofNullable(l1);
		Mockito.when(locationRepository.findById(4)).thenReturn(op1);
		Optional<Location> lTest = locationService.findById(4);
		assertTrue(lTest.get().getName().equals("Chicago"));
	}
	
	@Test
	public void updateTest() {
		Room r1 = new Room(1, "Class A");
		Room r2 = new Room(2, "Class B");
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
		Location l1 = new Location(4, "Chicago", "Chicago", "IL", false, buildingSet);
		l1.setIsActive(true);
		Mockito.when(locationRepository.save(l1)).thenReturn(l1);
		Location lTest = locationService.update(l1);
		assertTrue(lTest.getIsActive());
	}
	
	@Test
	public void createTest() {
		Room r1 = new Room(1, "Class A");
		Room r2 = new Room(2, "Class B");
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
		Location l1 = new Location(5, "New York", "New York", "NY", true, buildingSet);
		Mockito.when(locationRepository.save(l1)).thenReturn(l1);
		Location lTest = locationService.create(l1);
		assertTrue(lTest.getId() == 5);
	}
	
	@Test
	public void deleteTest() {
		Mockito.doNothing().when(locationRepository).deleteById(8);
		locationService.delete(8);
		Optional<Location> opTest = locationService.findById(8);
		assertFalse(opTest.isPresent());
	}

}
