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
}
