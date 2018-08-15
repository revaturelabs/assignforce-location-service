package com.revature.tests.building;

import com.revature.assignforce.beans.Building;
import com.revature.assignforce.repos.BuildingRepository;
import com.revature.assignforce.service.BuildingService;
import com.revature.assignforce.service.BuildingServiceImpl;
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
        Building b1 = new Building(4, true, "NEC", 2);
        Optional<Building> op1 = Optional.ofNullable(b1);
        Mockito.when(buildingRepository.findById(4)).thenReturn(op1);
        Optional<Building> lTest = buildingService.findById(4);
        assertTrue(lTest.get().getBuildingName().equals("NEC"));
    }

    @Test
    public void updateTest() {
        Building b1 = new Building(4, false, "NEC", 1);
        b1.setIsActive(true);
        Mockito.when(buildingRepository.save(b1)).thenReturn(b1);
        Building lTest = buildingService.update(b1);
        assertTrue(lTest.getIsActive());
    }

    @Test
    public void createTest() {
        Building b1 = new Building(5, true,"Business", 6);
        Mockito.when(buildingRepository.save(b1)).thenReturn(b1);
        Building lTest = buildingService.create(b1);
        assertTrue(lTest.getBuildingId() == 5);
    }

    @Test
    public void deleteTest() {
        Mockito.doNothing().when(buildingRepository).deleteById(8);
        buildingService.delete(8);
        Optional<Building> opTest = buildingService.findById(8);
        assertFalse(opTest.isPresent());
    }
}
