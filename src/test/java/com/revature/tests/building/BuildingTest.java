package com.revature.tests.building;

import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.revature.assignforce.beans.Location;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.assignforce.beans.Building;

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

		@Bean
		public Location Location() {
			return new Location();
		}
	}

	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void buildingTest1() {
		Building b = new Building();
		assertNotNull(b);
	}

	@Test
	public void getSetIdTest() {
		int id = 96;
		Building b = new Building();
		b.setId(id);
		assertTrue(b.getId() == id);
	}
	
	@Test
	public void getSetIsActiveTest() {
		Building b = new Building();
		b.setIsActive(true);
		assertTrue(b.getIsActive());
	}
	
	@Test
	public void getSetNameTest() {
		String name = "HQ";
		Building b = new Building();
		b.setName(name);
		assertTrue(b.getName().equals(name));
	}

	@Test
	public void getSetAddressTest() {
		int add = 5;
		Building b = new Building();
		b.setAddress(add);
		assertTrue(b.getAddress() == add);
	}

//	@Test
//	public void getSetLocationTest(){
//		Building b1 = new Building();
//		Location l1 = new Location(1, "Reston", "Reston", "VA", true);
//		b1.setLocation(l1);
//		assertTrue(b1.getLocation().getId() == 1 && b1.getLocation().getName().equals("Reston")
//			&& b1.getLocation().getCity().equals("Reston") && b1.getLocation().getState().equals("VA") && b1.getLocation().getIsActive());
//	}
	
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
	 * Asserts that there is a validation constraint on b1 because its nrame is null.
	 */
	
	@Test
	public void buildingNameNotNull() {
		Building b = new Building();
		b.setName(null);
		
	    Set<ConstraintViolation<Building>> constraintViolations =
	  	      validator.validate(b);

	  	assertEquals(1, constraintViolations.size());
	  	assertEquals(
	  	 	     "name must not be null",
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
		Building b = new Building();
		b.setName("");
		
		Set<ConstraintViolation<Building>> constraintViolations =
		  	      validator.validate(b);

		assertEquals(1, constraintViolations.size());
		assertEquals(
					"name length must be between 1 and 128",
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
		Building b = new Building();
		b.setName("1234567890123456789012345678901234567890"
				+ "1234567890123456789012345678901234567890"
				+ "1234567890123456789012345678901234567890"
				+ "123456789");
		
		Set<ConstraintViolation<Building>> constraintViolations =
		  	      validator.validate(b);

		assertEquals(1, constraintViolations.size());
		assertEquals(
					"name length must be between 1 and 128",
		  	 	    constraintViolations.iterator().next().getMessage()
		  	 	  );
	}
}

