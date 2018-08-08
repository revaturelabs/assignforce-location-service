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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.assignforce.beans.Building;
import com.revature.assignforce.beans.Location;
import com.revature.assignforce.beans.Room;
import com.revature.assignforce.beans.Unavailability;
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
		Location l1 = new Location(1, "Reston", "Reston", "VA", true);
		Location l2 = new Location(6, "Tampa", "Tampa", "FL", false);
		List<Location> locationList = new ArrayList<Location>();
		locationList.add(l1);
		locationList.add(l2);
		Mockito.when(locationRepository.findAll()).thenReturn(locationList);
		List<Location> testList = locationService.getAll();
		assertTrue(testList.size() == 2);
	}
	
	@Test
	public void findByIdTest() {
		Location l1 = new Location(4, "Chicago", "Chicago", "IL", false);
		Optional<Location> op1 = Optional.ofNullable(l1);
		Mockito.when(locationRepository.findById(4)).thenReturn(op1);
		Optional<Location> lTest = locationService.findById(4);
		assertTrue(lTest.get().getName().equals("Chicago"));
	}
	
	@Test
	public void updateTest() {
		Location l1 = new Location(4, "Chicago", "Chicago", "IL", false);
		l1.setIsActive(true);
		Mockito.when(locationRepository.save(l1)).thenReturn(l1);
		Location lTest = locationService.update(l1);
		assertTrue(lTest.getIsActive());
	}
	
	@Test
	public void createTest() {
		Location l1 = new Location(5, "New York", "New York", "NY", true);
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
