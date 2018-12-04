package com.revature.tests.room;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.assignforce.beans.Building;
import com.revature.assignforce.beans.Location;
import com.revature.assignforce.beans.Room;
import com.revature.assignforce.beans.Unavailability;
import com.revature.assignforce.controllers.RoomController;
import com.revature.assignforce.repos.RoomRepository;
import com.revature.assignforce.service.RoomService;
import com.revature.assignforce.service.RoomServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RoomControllerTest {

    @Configuration
    static class LocationServiceTestContextConfiguration {
        @Bean
        public RoomService RoomService() {
            return new RoomServiceImpl();
        }
        @Bean
        public RoomRepository RoomRepository() {
            return Mockito.mock(RoomRepository.class);
        }
        @Bean
        public RoomController RoomController() {
            return new RoomController();
        }
    }

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomController roomController;

    @Test
    public void getAllTest() {
        Room r1 = new Room(1, "201 A", 2);
        Room r2 = new Room(2, "201 C", 2);
        List<Room> roomList = new ArrayList<Room>();
        roomList.add(r1);
        roomList.add(r2);
        Mockito.when(roomRepository.findAll()).thenReturn(roomList);
        List<Room> testList = roomController.getAll();
        assertTrue(testList.size() == 2);
    }

    @Test
    public void getByIdTestOk() {
        Room r1 = new Room(3, "2001", 3);
        Optional<Room> op1 = Optional.ofNullable(r1);
        Mockito.when(roomRepository.findById(3)).thenReturn(op1);
        ResponseEntity<Room> reTest = roomController.getById(3);
        assertTrue(reTest.getBody().getId() == 3 && reTest.getStatusCode() == HttpStatus.OK);
    }

    @Test
    public void getByIdTestNotFound() {
        ResponseEntity<Room> reTest = roomController.getById(7);
        assertTrue(reTest.getStatusCode() == HttpStatus.NOT_FOUND);
    }

    @Test
    public void addTestCreated() {
        Room r1 = new Room(10, "201 A", 2);
        Mockito.when(roomRepository.save(r1)).thenReturn(r1);
        ResponseEntity<Room> reTest = roomController.add(r1);
        assertTrue(reTest.getBody().getId() == 10 && reTest.getStatusCode() == HttpStatus.CREATED);
    }

//    @Test
//    public void addTestBadRequest() {
//        Room r1 = new Room(0, "201 A", 5);
//        ResponseEntity<Room> reTest = roomController.add(r1);
//        assertTrue(reTest.getStatusCode() == HttpStatus.BAD_REQUEST);
//    }

    @Test
    public void updateTestOK() {
        Room r1 = new Room(10, "201 A", 3);
        r1.setRoomName("4003");
        r1.setBuilding(5);
        Mockito.when(roomRepository.save(r1)).thenReturn(r1);
        ResponseEntity<Room> reTest = roomController.update(r1.getId(), r1);
        assertTrue(reTest.getBody().getRoomName().equals("4003") && reTest.getStatusCode() == HttpStatus.CREATED);
    }

//    @Test
//    public void updateTestBadRequest() {
//        Room r1 = new Room(19, "San Diego", "San Diego", "CA", true);
//        r1.setCity("Phoenix");
//        r1.setName("Phoenix");
//        r1.setState("AZ");
//        ResponseEntity<Room> reTest = roomController.update(19, r1);
//        assertTrue(reTest.getStatusCode() == HttpStatus.BAD_REQUEST);
//    }

    @Test
    public void deleteTest() {
        Mockito.doNothing().when(roomRepository).deleteById(25);
        ResponseEntity<Room> reTest = roomController.delete(25);
        assertTrue(reTest.getStatusCode() == HttpStatus.OK);
    }

}