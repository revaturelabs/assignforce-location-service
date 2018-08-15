package com.revature.tests;

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
        Unavailability u1 = new Unavailability();
        assertNotNull(u1);
    }

    @Test
    public void getSetIdTest(){
        Unavailability u1 = new Unavailability();
        u1.setId(1);
        assertTrue(u1.getId() == 1);
    }

    @Test
    public void getSetDescriptionTest(){
        Unavailability u1 = new Unavailability();
        u1.setDescription("Already booked");
        assertTrue(u1.getDescription().equals("Already booked"));
    }

    @Test
    public void getSetStartDateTest(){
        Unavailability u1 = new Unavailability();
        LocalDate date = LocalDate.parse("2018-06-11");
        u1.setStartDate(date);
        assertTrue(u1.getStartDate().isEqual(LocalDate.parse("2018-06-11")));
    }

    @Test
    public void getSetEndDateTest(){
        Unavailability u1 = new Unavailability();
        LocalDate date = LocalDate.parse("2018-08-17");
        u1.setEndDate(date);
        assertTrue(u1.getEndDate().isEqual(LocalDate.parse("2018-08-17")));
    }

    @Test
    public void getSetRoomTest() {
        Unavailability u1 = new Unavailability();
        u1.setRoom(1402);
        assertTrue(u1.getRoom() == 1402);
    }
}
