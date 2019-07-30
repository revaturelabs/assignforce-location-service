package com.revature.tests.event;

import static org.junit.Assert.assertNotNull;

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

	
}
