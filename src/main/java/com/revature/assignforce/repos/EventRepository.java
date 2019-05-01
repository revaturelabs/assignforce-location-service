package com.revature.assignforce.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.assignforce.beans.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer>{

}
