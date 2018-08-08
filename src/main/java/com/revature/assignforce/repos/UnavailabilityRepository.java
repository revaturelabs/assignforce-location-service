package com.revature.assignforce.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.assignforce.beans.Unavailability;

@Repository
public interface UnavailabilityRepository extends JpaRepository<Unavailability, Integer> {

}