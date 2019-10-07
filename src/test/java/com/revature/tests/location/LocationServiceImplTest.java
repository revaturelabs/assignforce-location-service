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
		//assertTrue(testList.size() == 2);
		assertEquals(2, testList.size());
	}
	
	@Test
	public void findByIdTest() {
		String name = "Chicago";
		Location l = new Location(4, name, "Chicago", "IL", false);
		Optional<Location> op = Optional.ofNullable(l);
		Mockito.when(locationRepository.findById(4)).thenReturn(op);
		Optional<Location> lTest = locationService.findById(4);
		assertTrue(lTest.get().getName().equals(name));
	}
	
	@Test
	public void updateTest() {
		Location l = new Location(4, "Chicago", "Chicago", "IL", false);
		l.setIsActive(true);
		Mockito.when(locationRepository.save(l)).thenReturn(l);
		Location lTest = locationService.update(l);
		assertTrue(lTest.getIsActive());
	}
	
	@Test
	public void createTest() {
		int id = 5;
		Location l = new Location(id, "New York", "New York", "NY", true);
		Mockito.when(locationRepository.save(l)).thenReturn(l);
		Location lTest = locationService.create(l);
//		assertTrue(lTest.getId() == id);
		assertEquals(id, lTest.getId());
	}
	
	@Test
	public void deleteTest() {
		int id = 8;
		Mockito.doNothing().when(locationRepository).deleteById(id);
		locationService.delete(id);
		Optional<Location> opTest = locationService.findById(id);
		assertFalse(opTest.isPresent());
	}

}
