package com.revature.tests.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.assignforce.beans.Event;
import com.revature.assignforce.repos.EventRepository;
import com.revature.assignforce.service.EventService;
import com.revature.assignforce.service.EventServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EventServiceImplTest {

	@Configuration
	static class EventServiceTestContextConfiguration {
		@Bean
		public EventService EventService() {
			return new EventServiceImpl();
		}

		@Bean
		public EventRepository EventRepository() {
			return Mockito.mock(EventRepository.class);
		}

	}

	@Autowired
	private EventService eventService;
	@Autowired
	private EventRepository eventRepository;

	@Test
	public void getAllTest() {
		LocalDate s1 = LocalDate.of(1994, 4, 27);
		LocalDate e1 = LocalDate.of(1994, 4, 27);
		LocalDate t1 = LocalDate.now();
		Event event = new Event(1, s1, e1, "Name", t1, 2);
		Event event1 = new Event(2, s1, e1, "EventName2", t1, 3);
		List<Event> eventList = new ArrayList<Event>();
		eventList.add(event);
		eventList.add(event1);
		Mockito.when(eventRepository.findAll()).thenReturn(eventList);
		List<Event> testList = eventService.getAll();
		assertEquals(2, testList.size());
	}
	
	@Test
	public void findByIdTest() {
		LocalDate s1 = LocalDate.of(1994, 4, 27);
		LocalDate e1 = LocalDate.of(1994, 4, 27);
		LocalDate t1 = LocalDate.now();
		int id = 1;
		Event e = new Event(id, s1, e1, "Name", t1, 2);
		Optional<Event> op1 = Optional.ofNullable(e);
		Mockito.when(eventRepository.findById(id)).thenReturn(op1);
		Optional<Event> test = eventService.findById(id);
		assertEquals("Name", test.get().getName());
	}
	
	@Test
	public void updateTest() {
		LocalDate s1 = LocalDate.of(1994, 4, 27);
		LocalDate s2 = LocalDate.of(1994, 5, 27);
		LocalDate s3 = LocalDate.now();
		
		Event e = new Event(1, s1, s2, "Name", s3, 2);
		e.setEndDate(LocalDate.of(1994, 5, 20));
		Mockito.when(eventRepository.save(e)).thenReturn(e);
		Event test = eventService.update(e);
		assertEquals(LocalDate.of(1994, 5, 20), test.getEndDate());	
	}
	
	@Test
	public void createTest() {
		LocalDate s1 = LocalDate.of(1994, 4, 27);
		LocalDate s2 = LocalDate.of(1994, 5, 27);
		LocalDate s3 = LocalDate.now();
		
		int id = 10;
		Event e = new Event(id, s1, s2, "Name", s3, 2);
		Mockito.when(eventRepository.save(e)).thenReturn(e);
		Event test = eventService.create(e);
		assertEquals(id, test.getId());
	}
	
	@Test
	public void deleteTest() {
		int id = 22;
		Mockito.doNothing().when(eventRepository).deleteById(id);
		eventService.delete(id);
		Optional<Event> test = eventService.findById(id);
		assertFalse(test.isPresent());
	}

}
