package com.revature.tests.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Date;

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

import com.revature.assignforce.beans.Event;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EventTest {

	private static Validator validator;

	@Configuration
	static class EventTestContextConfiguration {
		@Bean
		public Event Event() {
			return new Event();
		}
	}
	
	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	public void eventNotNull() {
		Event e = new Event();
		assertNotNull(e);
	}
	
	@Test
	public void getsetStartDate() {
		LocalDate date = LocalDate.parse("2018-09-16");
		Event e = new Event();
		e.setStartDate(date);
		assertEquals(date, e.getStartDate());
	}
	
	
	@Test
	public void getsetCreatedDate() {
		LocalDate date = LocalDate.parse("2018-09-16");
		Event e = new Event();
		e.setCreatedDate(date);
		assertEquals(date, e.getCreatedDate());
	}
	
	@Test
	public void getsetEndDate() {
		LocalDate date = LocalDate.parse("2018-09-16");
		Event e = new Event();
		e.setEndDate(date);
		assertEquals(date, e.getEndDate());
	}
	
	@Test
	public void getsetId() {
		int id = 34;
		Event e = new Event();
		e.setId(id);
		assertEquals(id, e.getId());
	}
	
	@Test
	public void getsetName() {
		String name = "ND-09";
		Event e = new Event();
		e.setName(name);
		assertTrue(e.getName().equals(name));
	}
	
	@Test
	public void getsetRoomID() {
		int rId = 107;
		Event e = new Event();
		e.setRoomID(rId);
		assertEquals((Integer) rId,e.getRoomID());
	}
	
}
