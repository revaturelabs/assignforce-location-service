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
        LocalDate date = LocalDate.parse("2018-11-06");
        u1.setStartDate(date);

        //System.out.println(u1.getStartDate());
        assertTrue(u1.getStartDate().isEqual(LocalDate.parse("2018-11-06")));
        //assertThat(u1.getStartDate().isEqual("2018-11-06"));
    }
}
