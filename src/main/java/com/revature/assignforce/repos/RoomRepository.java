package com.revature.assignforce.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.assignforce.beans.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

}
