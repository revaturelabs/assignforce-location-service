package com.revature.tests.unavailability;

import com.revature.assignforce.beans.Room;
import com.revature.assignforce.beans.Unavailability;
import com.revature.assignforce.repos.RoomRepository;
import com.revature.assignforce.repos.UnavailabilityRepository;
import com.revature.assignforce.service.RoomService;
import com.revature.assignforce.service.RoomServiceImpl;
import com.revature.assignforce.service.UnavailabilityService;
import com.revature.assignforce.service.UnavailabilityServiceImpl;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UnavailabilityServiceImplTest {

    @Configuration
    static class UnavailabilityServiceTestContextConfiguration{
        @Bean
        public RoomService RoomService(){return new RoomServiceImpl();}
        @Bean
        public RoomRepository RoomRepository(){return Mockito.mock(RoomRepository.class);}
        @Bean
        public UnavailabilityService UnavailabilityService(){return new UnavailabilityServiceImpl();}
        @Bean
        public UnavailabilityRepository UnavailabilityRepository(){return Mockito.mock(UnavailabilityRepository.class);}
    }

    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private UnavailabilityService unavailabilityService;
    @Autowired
    private UnavailabilityRepository unavailabilityRepository;

    @Test
    public void getAllTest() {
        Room r1 = new Room();
        Room r2 = new Room();
        Unavailability u1 = new Unavailability(1, "Java", LocalDate.parse("2018-08-16"), LocalDate.parse("2018-06-11"), 5);
        Unavailability u2 = new Unavailability(3, ".net", LocalDate.parse("2018-08-11"), LocalDate.parse("2018-06-04"), 4);
        List<Unavailability> unavailabilityList = new ArrayList<>();
        unavailabilityList.add(u1);
        unavailabilityList.add(u2);
        Mockito.when(unavailabilityRepository.findAll()).thenReturn(unavailabilityList);
        List<Unavailability> testList = unavailabilityService.getAll();
        assertEquals(2, testList.size());
    }

    @Test
    public void findByIdTest() {
        Unavailability u1 = new Unavailability(1, "Java", LocalDate.parse("2018-08-16"), LocalDate.parse("2018-06-11"), 5);
        Optional<Unavailability> op1 = Optional.ofNullable(u1);
        Mockito.when(unavailabilityRepository.findById(4)).thenReturn(op1);
        Optional<Unavailability> lTest = unavailabilityService.findById(4);
        assertEquals("Java", lTest.get().getDescription());
    }

    @Test
    public void updateTest() {
        Unavailability u1 = new Unavailability(1, "Java", LocalDate.parse("2018-08-16"), LocalDate.parse("2018-06-11"), 5);
        u1.setDescription(".net");
        Mockito.when(unavailabilityRepository.save(u1)).thenReturn(u1);
        Unavailability lTest = unavailabilityService.update(u1);
        assertEquals(".net", lTest.getDescription());
    }

    @Test
    public void createTest() {
        Unavailability u1 = new Unavailability(1, "Java", LocalDate.parse("2018-08-16"), LocalDate.parse("2018-06-11"), 5);
        Mockito.when(unavailabilityRepository.save(u1)).thenReturn(u1);
        Unavailability lTest = unavailabilityService.create(u1);
        assertEquals(1, lTest.getId());
    }

    @Test
    public void deleteTest() {
        Mockito.doNothing().when(unavailabilityRepository).deleteById(8);
        unavailabilityService.delete(8);
        Optional<Unavailability> opTest = unavailabilityService.findById(8);
        assertFalse(opTest.isPresent());
    }

//    @Test
//    public void addUnavailabilityTest(){
//        Unavailability u1 = new Unavailability(1, "Java", LocalDate.parse("2018-08-16"), LocalDate.parse("2018-06-11"), 4);
//        Room r1 = new Room(4, "Staging", 2);
//        Optional<Room> op1 = Optional.ofNullable(r1);
//        Mockito.when(roomRepository.findById(4)).thenReturn(op1);
//        Unavailability test = u1;
//        test.setRoomObject(roomRepository.findById(4).orElse(null));
//        Mockito.when(unavailabilityService.addUnavailability(u1, 4)).thenReturn(test);
//        Optional<Unavailability> lTest = Optional.ofNullable(unavailabilityService.addUnavailability(u1, 4));
//        assertEquals(4, lTest.get().getRoomObject().getId());
//    }
//
//    @Test
//    public void updateUnavailabilityTest(){
//        Unavailability u = new Unavailability(1, "Test", LocalDate.parse("2018-07-16"), LocalDate.parse("2018-06-11"), 4);
//        Room r = new Room(4, "Staging", 2);
//        Optional<Room> rOpt = Optional.ofNullable(r);
//        Mockito.when(roomRepository.findById(4)).thenReturn(rOpt);
//        Unavailability test = u;
//        test.setRoomObject(roomRepository.findById(4).orElse(null));
//        Mockito.when(unavailabilityService.updateUnavailability(u, 4)).thenReturn(test);
//        Optional<Unavailability> testOpt = Optional.ofNullable(unavailabilityService.updateUnavailability(u, 4));
//        assertEquals(4, testOpt.get().getRoomObject().getId());
//    }
}
