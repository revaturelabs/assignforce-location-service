package com.revature.tests.building;

import com.revature.assignforce.beans.Building;
import com.revature.assignforce.controllers.BuildingController;
import com.revature.assignforce.repos.BuildingRepository;
import com.revature.assignforce.service.BuildingService;
import com.revature.assignforce.service.BuildingServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BuildingController.class})
@WebMvcTest(BuildingController.class)
public class BuildingServiceImplTest {

    @Configuration
    static class BuildingServiceTestContextConfiguration {
        @Bean
        public BuildingService BuildingService() {
            return new BuildingServiceImpl();
        }
        @Bean
        public BuildingRepository BuildingRepository() {
            return Mockito.mock(BuildingRepository.class);
        }
    }

    @Autowired
    private BuildingService buildingService;
    @Autowired
    private BuildingRepository buildingRepository;

    @Test
    public void getAllTest() {
        Building b1 = new Building(1, true, "HQ", 2);
        Building b2 = new Building(5, false, "NEC", 3);
        List<Building> buildingList = new ArrayList<>();
        buildingList.add(b1);
        buildingList.add(b2);
        Mockito.when(buildingRepository.findAll()).thenReturn(buildingList);
        List<Building> testList = buildingService.getAll();
        assertTrue(testList.size() == 2);
    }

    @Test
    public void findByIdTest() {
        String name = "NEC";
        Building b = new Building(4, true, name, 2);
        Optional<Building> op = Optional.ofNullable(b);
        Mockito.when(buildingRepository.findById(4)).thenReturn(op);
        Optional<Building> lTest = buildingService.findById(4);
        assertTrue(lTest.get().getName().equals(name));
    }

    @Test
    public void updateTest() {
        Building b = new Building(4, false, "NEC", 1);
        b.setIsActive(true);
        Mockito.when(buildingRepository.save(b)).thenReturn(b);
        Building lTest = buildingService.update(b);
        assertTrue(lTest.getIsActive());
    }

    @Test
    public void createTest() {
        int id = 5;
        Building b = new Building(id, true,"Business", 6);
        Mockito.when(buildingRepository.save(b)).thenReturn(b);
        Building lTest = buildingService.create(b);
        assertTrue(lTest.getId() == id);
    }

    @Test
    public void deleteTest() {
        int id = 8;
        Mockito.doNothing().when(buildingRepository).deleteById(id);
        buildingService.delete(id);
        Optional<Building> opTest = buildingService.findById(id);
        assertFalse(opTest.isPresent());
    }
}
