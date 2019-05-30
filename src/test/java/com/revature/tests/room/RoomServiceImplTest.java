package com.revature.tests.room;


import com.revature.assignforce.beans.Room;
import com.revature.assignforce.repos.RoomRepository;
import com.revature.assignforce.service.RoomService;
import com.revature.assignforce.service.RoomServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RoomServiceImplTest {

    @Configuration
    static class RoomServiceTestContextConfiguration{
        @Bean
        public RoomService RoomService(){return new RoomServiceImpl();}
        @Bean
        public RoomRepository RoomRepository(){return Mockito.mock(RoomRepository.class);}
    }

    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomRepository roomRepository;

    @Test
    public void getAllTest() {
        Room r1 = new Room(1, "Auditorium", 2);
        Room r2 = new Room(5, "Basement", 3);
        List<Room> roomList = new ArrayList<>();
        roomList.add(r1);
        roomList.add(r2);
        Mockito.when(roomRepository.findAll()).thenReturn(roomList);
        List<Room> testList = roomService.getAll();
        assertEquals(2, testList.size());
    }

    @Test
    public void findByIdTest() {
        String name = "Staging";
        int id = 4;
        Room r = new Room(id, name, 2);
        Optional<Room> op1 = Optional.ofNullable(r);
        Mockito.when(roomRepository.findById(id)).thenReturn(op1);
        Optional<Room> lTest = roomService.findById(id);
        assertEquals(name, lTest.get().getName());
    }

    @Test
    public void updateTest() {
        String change = "HR Office";
        Room r = new Room(4, "HR", 1);
        r.setName(change);
        Mockito.when(roomRepository.save(r)).thenReturn(r);
        Room lTest = roomService.update(r);
        assertEquals(change, lTest.getName());
    }

    @Test
    public void createTest() {
        int id = 5;
        Room r = new Room(id,"Planning", 6);
        Mockito.when(roomRepository.save(r)).thenReturn(r);
        Room lTest = roomService.create(r);
        assertEquals(id, lTest.getId());
    }

    @Test
    public void deleteTest() {
        int id = 8;
        Mockito.doNothing().when(roomRepository).deleteById(id);
        roomService.delete(id);
        Optional<Room> opTest = roomService.findById(id);
        assertFalse(opTest.isPresent());
    }
}
