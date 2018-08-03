package com.revature.tests;

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
		Room r1 = new Room();
		assertNotNull(r1);
	}
	
	@Test
	public void roomTest2() {
		Set<Unavailability> testSet = new TreeSet<Unavailability>();
		Room r1 = new Room(4, "Room4", testSet);
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

	/**
	 *  @author JavierCastano
	 *  Asserts that there is a validation constraint that occurs for r1 because its roomName is null.
	 */
	
	@Test
	public void roomNameNotNull() {
		Set<Unavailability> testSet = new TreeSet<Unavailability>();
		Room r1 = new Room(1, null, testSet);
		
	    Set<ConstraintViolation<Room>> constraintViolations =
	      validator.validate(r1);
	 
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
		
		Set<Unavailability> testSet = new TreeSet<Unavailability>();
		Room r1 = new Room(1, "", testSet);
		
		Set<ConstraintViolation<Room>> constraintViolations =
			      validator.validate(r1);
 
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
		Set<Unavailability> testSet = new TreeSet<Unavailability>();
		Room r1 = new Room(1, "1234567890123456789012345678901234567890"
				+ "1234567890123456789012345678901234567890" 
				+ "1234567890123456789012345678901234567890" 
				+ "123456789", testSet);
		
		Set<ConstraintViolation<Room>> constraintViolations =
			      validator.validate(r1);

		assertEquals(1, constraintViolations.size());
		assertEquals(
	 	         "RoomName size must be between 1 and 128",
	 	         constraintViolations.iterator().next().getMessage()
	 	      );
	}
}
