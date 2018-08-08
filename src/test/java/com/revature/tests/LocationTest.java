package com.revature.tests;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.assignforce.beans.Building;
import com.revature.assignforce.beans.Location;
import com.revature.assignforce.beans.Room;

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
		Location l1 = new Location();
		assertNotNull(l1);
	}
	
	@Test
	public void locationTest2() {
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
		Location l1 = new Location(5, "Reston", "Reston", "VA", true, buildingSet);
		assertTrue(l1.getId() == 5);
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
/*	
	@Test
	public void getSetBuildingsTest() {
		Location l1 = new Location();
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
		l1.setBuildings(buildingSet);
		assertTrue(l1.getBuildings().size() == 3);
	}*/

}