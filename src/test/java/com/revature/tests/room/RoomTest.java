package com.revature.tests.room;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.TreeSet;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.assignforce.beans.Room;
import com.revature.assignforce.beans.Unavailability;
import com.revature.assignforce.repos.LocationRepository;
import com.revature.assignforce.service.LocationService;
import com.revature.assignforce.service.LocationServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RoomTest {

	private static Validator validator;
	
	@Configuration
	static class RoomTestContextConfiguration {
		@Bean
		public Room Room() {
			return new Room();
			}
	}
	
	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	public void roomTest1() {
		Room r = new Room();
		assertNotNull(r);
	}
	
	@Test
	public void getSetIdTest() {
		int id = 59;
		Room r = new Room();
		r.setId(id);
		assertTrue(r.getId() == id);
	}
	
	@Test
	public void getSetNameTest() {
		String name = "BlueRoom";
		Room r = new Room();
		r.setRoomName(name);
		assertTrue(r.getRoomName().equals(name));
	}

	@Test
	public void getSetBuildingTest() {
		int bld = 5;
		Room b = new Room();
		b.setBuilding(bld);
		assertTrue(b.getBuilding() == bld);
	}

	/**
	 *  @author JavierCastano
	 *  Asserts that there is a validation constraint that occurs for r1 because its roomName is null.
	 */
	
	@Test
	public void roomNameNotNull() {
		Room r = new Room();
		r.setRoomName(null);
		
	    Set<ConstraintViolation<Room>> constraintViolations =
	      validator.validate(r);
	 
	    assertEquals(1, constraintViolations.size());
	    assertEquals(
	 	         "roomName must not be null",
	 	         constraintViolations.iterator().next().getMessage()
	 	      );
	}
	
	/**
	 * 
	 * @author JavierCastano
	 * Asserts that there is a validation constraint that occurs for r1 because its roomName is an empty string
	 * which is not greater than the minimum declared in the Size annotation which is 1 character.
	 */
	
	@Test
	public void roomNameGreaterThanEmptyString() {
		
		Room r = new Room();
		r.setRoomName("");
		
		Set<ConstraintViolation<Room>> constraintViolations =
			      validator.validate(r);
 
		assertEquals(1, constraintViolations.size());
		assertEquals(
	 	         "RoomName size must be between 1 and 128",
	 	         constraintViolations.iterator().next().getMessage()
	 	      );
	}
	
	/**
	 * 	@author JavierCastano
	 * 	Asserts that there is a validation constraint that occurs for r1 because it is a string with a size
	 *  greater than 128. The size 129 is greater then the maximum size declared for roomName in the Size annotation.
	 */
	
	@Test
	public void roomNameSizeLessThan129() {
		Room r = new Room();
		r.setRoomName("1234567890123456789012345678901234567890"
				+ "1234567890123456789012345678901234567890" 
				+ "1234567890123456789012345678901234567890" 
				+ "123456789");
		
		Set<ConstraintViolation<Room>> constraintViolations =
			      validator.validate(r);

		assertEquals(1, constraintViolations.size());
		assertEquals(
	 	         "RoomName size must be between 1 and 128",
	 	         constraintViolations.iterator().next().getMessage()
	 	      );
	}
}
