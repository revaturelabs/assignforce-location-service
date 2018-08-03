package com.revature.assignforce.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.assignforce.beans.Building;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Integer> {

}
