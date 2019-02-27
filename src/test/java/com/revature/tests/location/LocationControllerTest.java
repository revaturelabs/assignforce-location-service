package com.revature.tests.location;

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
		Location l1 = new Location(1, "Reston", "Reston", "VA", true);
		Location l2 = new Location(6, "Tampa", "Tampa", "FL", false);
		Location l3 = new Location(8, "Detroit", "Detroit", "MI", false);
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
		int id = 5;
		Location l = new Location(id, "Reston", "Reston", "VA", true);
		Optional<Location> op = Optional.ofNullable(l);
		Mockito.when(locationRepository.findById(id)).thenReturn(op);
		ResponseEntity<Location> reTest = locationController.getById(id);
		assertTrue(reTest.getBody().getId() == id && reTest.getStatusCode() == HttpStatus.OK);
	}
	
	@Test
	public void getByIdTestNotFound() {
		ResponseEntity<Location> reTest = locationController.getById(7);
		assertTrue(reTest.getStatusCode() == HttpStatus.NOT_FOUND);
	}
	
	@Test
	public void addTestCreated() {
		int id = 8;
		Location l = new Location(id, "San Diego", "San Diego", "CA", true);
		Mockito.when(locationRepository.save(l)).thenReturn(l);
		ResponseEntity<Location> reTest = locationController.add(l);
		assertTrue(reTest.getBody().getId() == id && reTest.getStatusCode() == HttpStatus.CREATED);
	}
	
	@Test
	public void addTestBadRequest() {
		Location l = new Location(13, "Scranton", "Scranton", "PA", false);
		ResponseEntity<Location> reTest = locationController.add(l);
		assertTrue(reTest.getStatusCode() == HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void updateTestOK() {
		String state = "AZ";
		Location l = new Location(8, "San Diego", "San Diego", "CA", true);
		l.setCity("Phoenix");
		l.setName("Phoenix");
		l.setState(state);
		Mockito.when(locationRepository.save(l)).thenReturn(l);
		ResponseEntity<Location> reTest = locationController.update(l);
		assertTrue(reTest.getBody().getState().equals(state) && reTest.getStatusCode() == HttpStatus.CREATED);
	}
	
	@Test
	public void updateTestBadRequest() {
		Location l = new Location(19, "San Diego", "San Diego", "CA", true);
		l.setCity("Phoenix");
		l.setName("Phoenix");
		l.setState("AZ");
		ResponseEntity<Location> reTest = locationController.update(l);
		assertTrue(reTest.getStatusCode() == HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void deleteTest() {
		Mockito.doNothing().when(locationRepository).deleteById(25);
		ResponseEntity<Location> reTest = locationController.delete(25);
		assertTrue(reTest.getStatusCode() == HttpStatus.OK);
	}

}
