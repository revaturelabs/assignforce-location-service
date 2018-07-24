package com.revature.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.assignforce.beans.Room;
import com.revature.assignforce.repos.LocationRepository;
import com.revature.assignforce.service.LocationService;
import com.revature.assignforce.service.LocationServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RoomTest {

	@Configuration
	static class RoomTestContextConfiguration {
	@Bean
	public Room Room() {
		return new Room();
		}
	}
	
	@Test
	public void roomTest1() {
		Room r1 = new Room();
		assertNotNull(r1);
	}
	
	@Test
	public void roomTest2() {
		Room r1 = new Room(4, "Room4");
		assertTrue(r1.getId() == 4);
	}
	
	@Test
	public void getSetIdTest() {
		Room r1 = new Room();
		r1.setId(59);
		assertTrue(r1.getId() == 59);
	}
	
	@Test
	public void getSetNameTest() {
		Room r1 = new Room();
		r1.setRoomName("BlueRoom");
		assertTrue(r1.getRoomName().equals("BlueRoom"));
	}

}
