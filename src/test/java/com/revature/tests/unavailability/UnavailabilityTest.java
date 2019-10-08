package com.revature.tests.unavailability;

import com.revature.assignforce.beans.Unavailability;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UnavailabilityTest {

    @Configuration
    static class UnavailabilityTestContextConfiguration{
        @Bean
        public Unavailability Unavailability(){ return new Unavailability();}
    }

    @Test
    public void UnavailabilityTest1() {
        Unavailability u = new Unavailability();
        assertNotNull(u);
    }

    @Test
    public void getSetIdTest(){
        int id = 1;
        Unavailability u = new Unavailability();
        u.setId(id);
        //assertTrue(u.getId() == id);
        assertEquals(id, u.getId());
    }

    @Test
    public void getSetDescriptionTest(){
        String desc = "Already booked";
        Unavailability u = new Unavailability();
        u.setDescription(desc);
        assertTrue(u.getDescription().equals(desc));
    }

    @Test
    public void getSetStartDateTest(){
        Unavailability u = new Unavailability();
        LocalDate date = LocalDate.parse("2018-06-11");
        u.setStartDate(date);
        assertTrue(u.getStartDate().isEqual(LocalDate.parse("2018-06-11")));
    }

    @Test
    public void getSetEndDateTest(){
        Unavailability u = new Unavailability();
        LocalDate date = LocalDate.parse("2018-08-17");
        u.setEndDate(date);
        assertTrue(u.getEndDate().isEqual(LocalDate.parse("2018-08-17")));
    }

    @Test
    public void getSetRoomTest() {
        int room = 1402;
        Unavailability u = new Unavailability();
        u.setRoom(room);
        //assertTrue(u.getRoom() == room);
        assertEquals((Integer)room, u.getRoom());
    }
}
