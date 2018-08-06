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
import com.revature.assignforce.beans.Room;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BuildingTest {

	@Configuration
	static class BuildingTestContextConfiguration {
	@Bean
	public Building Building() {
		return new Building();
		}
	}
	
	@Test
	public void buildingTest1() {
		Building b1 = new Building();
		assertNotNull(b1);
	}
	
	@Test
	public void buildingTest2() {
		Room r1 = new Room(1, "Class A");
		Room r2 = new Room(2, "Class B");
		HashSet<Room> roomSet = new HashSet<Room>();
		roomSet.add(r1);
		roomSet.add(r2);
		Building b1 = new Building(7, true, "BuildingOne", roomSet);
		assertTrue(b1.getBuildingId() == 7);
	}
	
	@Test
	public void getSetIdTest() {
		Building b1 = new Building();
		b1.setBuildingId(96);
		assertTrue(b1.getBuildingId() == 96);
	}
	
	@Test
	public void getSetIsActiveTest() {
		Building b1 = new Building();
		b1.setIsActive(true);
		assertTrue(b1.getIsActive());
	}
	
	@Test
	public void getSetNameTest() {
		Building b1 = new Building();
		b1.setBuildingName("HQ");
		assertTrue(b1.getBuildingName().equals("HQ"));
	}
	
/*	@Test
	public void getSetRoomsTest() {
		Room r1 = new Room(1, "Class A");
		Room r2 = new Room(2, "Class B");
		Room r3 = new Room(3, "Class C");
		HashSet<Room> roomSet = new HashSet<Room>();
		roomSet.add(r1);
		roomSet.add(r2);
		roomSet.add(r3);
		Building b1 = new Building();
		b1.setRooms(roomSet);
		assertTrue(b1.getRooms().size() == 3);
	}*/

}
