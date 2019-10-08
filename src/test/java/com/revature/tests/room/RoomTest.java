package com.revature.tests.room;

import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.assignforce.beans.Room;

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
		//assertTrue(r.getId() == id);
		assertEquals(id, r.getId());
	}
	
	@Test
	public void getSetNameTest() {
		String name = "BlueRoom";
		Room r = new Room();
		r.setName(name);
		assertTrue(r.getName().equals(name));
	}
	
	@Test
	public void getSetRoomCapacity() {
		int rc = 30;
		Room r = new Room();
		r.setRoomCapacity(rc);
		assertEquals(30, r.getRoomCapacity());
	}
	
	@Test
	public void getisActive() {
		boolean b = true;
		Room r = new Room();
		r.setActive(b);
		assertEquals(true,r.isActive());
	}

	@Test
	public void getSetBuildingTest() {
		int bld = 5;
		Integer i = 5;
		Room b = new Room();
		b.setBuilding(bld);
		//assertTrue(b.getBuilding() == bld);
		assertEquals(i, b.getBuilding());
	}

	/**
	 *  @author JavierCastano
	 *  Asserts that there is a validation constraint that occurs for r1 because its roomName is null.
	 */
	
	@Test
	public void roomNameNotNull() {
		Room r = new Room(5, "Room 2", 2, 1);
		r.setName(null);
		
	    Set<ConstraintViolation<Room>> constraintViolations =
	      validator.validate(r);
	 
	    assertEquals(1, constraintViolations.size());
	    assertEquals(
	 	         "name must not be null",
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
		
		Room r = new Room(5, "Room 2", 2, 1);
		r.setName("");
		
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
		Room r = new Room(5, "Room 2", 2, 1);
		r.setName("1234567890123456789012345678901234567890"
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
