package com.revature.tests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

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

import com.revature.assignforce.beans.Building;
import com.revature.assignforce.beans.Room;
import com.revature.assignforce.beans.Unavailability;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BuildingTest {

	private static Validator validator;
	
	@Configuration
	static class BuildingTestContextConfiguration {
	@Bean
	public Building Building() {
		return new Building();
		}
	}
	
	@BeforeClass
	   public static void setUp() {
	      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	      validator = factory.getValidator();
	   }
	
	@Test
	public void buildingTest1() {
		Building b1 = new Building();
		assertNotNull(b1);
	}
	
	@Test
	public void buildingTest2() {
		Set<Unavailability> testSet = new TreeSet<Unavailability>();
		Room r1 = new Room(1, "Class A", testSet);
		Room r2 = new Room(2, "Class B", testSet);
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
		Set<Unavailability> testSet = new TreeSet<Unavailability>();
		Room r1 = new Room(1, "Class A", testSet);
		Room r2 = new Room(2, "Class B", testSet);
		Room r3 = new Room(3, "Class C", testSet);
		HashSet<Room> roomSet = new HashSet<Room>();
		roomSet.add(r1);
		roomSet.add(r2);
		roomSet.add(r3);
		Building b1 = new Building();
		b1.setRooms(roomSet);
		assertTrue(b1.getRooms().size() == 3);
	}
*/
	/**
	 * @author JavierCastano
	 * Asserts that there is a validation constraint on b1 because its buildingName is null.
	 */
	
	@Test
	public void buildingNameNotNull() {
		Set<Room> testSet = new TreeSet<Room>();
		Building b1 = new Building(1, false, null, testSet);
		
	    Set<ConstraintViolation<Building>> constraintViolations =
	  	      validator.validate(b1);
	  	 
	  	assertEquals(1, constraintViolations.size());
	  	assertEquals(
	  	 	     "buildingName must not be null",
	  	 	     constraintViolations.iterator().next().getMessage()
	  	 	  );
	}
	
	/**
	 * @author JavierCastano
	 * Asserts that there is a validation constraint on b1 because its buildingName is an empty string
	 * which is less then the Size annotation minimum of 1 character.
	 */
	
	@Test
	public void BuildingNameSizeGreaterThanEmptyString() {
		Set<Room> testSet = new TreeSet<Room>();
		Building b1 = new Building(1, false, "", testSet);
		
		Set<ConstraintViolation<Building>> constraintViolations =
		  	      validator.validate(b1);
		  	 
		assertEquals(1, constraintViolations.size());
		assertEquals(
					"buildingName must be between 1 and 128",
		  	 	    constraintViolations.iterator().next().getMessage()
		  	 	  );
	}
	
	/**
	 * 	@author JavierCastano
	 * 	Asserts that there is a validation constraint that occurs for b1 because it is a string with a size
	 *  greater than 128. The size 129 is greater then the maximum size declared for roomName in the Size annotation.
	 */
	
	@Test
	public void BuildingSizeLessThan129() {
		Set<Room> testSet = new TreeSet<Room>();
		Building b1 = new Building(1, false, "1234567890123456789012345678901234567890"
				+ "1234567890123456789012345678901234567890"
				+ "1234567890123456789012345678901234567890"
				+ "123456789", testSet);

		Set<ConstraintViolation<Building>> constraintViolations =
		  	      validator.validate(b1);
		  	 
		assertEquals(1, constraintViolations.size());
		assertEquals(
					"buildingName must be between 1 and 128",
		  	 	    constraintViolations.iterator().next().getMessage()
		  	 	  );
	}
}