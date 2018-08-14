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
		Location ll = new Location();
		assertNotNull(ll);
	}

	@Test
	public void getSetIdTest() {
		Location l1 = new Location();
		l1.setId(32);
		assertTrue(l1.getId() == 32);
	}
	
	@Test
	public void getSetNameTest() {
		Location l1 = new Location();
		l1.setName("HQ");
		assertTrue(l1.getName().equals("HQ"));
	}
	
	@Test
	public void getSetCityTest() {
		Location l1 = new Location();
		l1.setCity("Reston");
		assertTrue(l1.getCity().equals("Reston"));
	}
	
	@Test
	public void getSetStateTest() {
		Location l1 = new Location();
		l1.setState("VA");
		assertTrue(l1.getState().equals("VA"));
	}
	
	@Test
	public void getSetIsActiveTest() {
		Location l1 = new Location();
		l1.setIsActive(true);
		assertTrue(l1.getIsActive());
	}
}
