package com.revature.tests.location;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.revature.assignforce.beans.Location;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LocationTest {

	@Configuration
	static class LocationTestContextConfiguration {
	@Bean
	public Location Location() {
		return new Location();
		}
	}

	@Test
	public void locationTest1() {
		Location l = new Location();
		assertNotNull(l);
	}

	@Test
	public void getSetIdTest() {
		int id = 32;
		Location l = new Location();
		l.setId(id);
		//assertTrue(l.getId() == id);
		assertEquals(id, l.getId());
	}
	
	@Test
	public void getSetNameTest() {
		String name = "HQ";
		Location l = new Location();
		l.setName(name);
		assertTrue(l.getName().equals(name));
	}
	
	@Test
	public void getSetCityTest() {
		String city = "Reston";
		Location l = new Location();
		l.setCity(city);
		assertTrue(l.getCity().equals(city));
	}
	
	@Test
	public void getSetStateTest() {
		String state = "VA";
		Location l = new Location();
		l.setState(state);
		assertTrue(l.getState().equals(state));
	}
	
	@Test
	public void getSetIsActiveTest() {
		Location l = new Location();
		l.setIsActive(true);
		assertTrue(l.getIsActive());
	}
}
