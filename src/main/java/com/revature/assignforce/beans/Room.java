package com.revature.assignforce.beans;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "Room")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Room_ID")
	@SequenceGenerator(name = "Room_ID", sequenceName = "Room_ID_seq", allocationSize = 1)
	@Column(name = "Room_ID")
	private int id;

	@Column(name = "Room_Name")
	@NotNull(message = "roomName must not be null")
	@Size(min = 1, max = 128, message = "RoomName size must be between 1 and 128")
	private String roomName;

	@JsonIgnoreProperties
	@ManyToOne(targetEntity=Building.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "BUILDING_ID")
	private Building buildingObject;

	@Column(name="BUILDING_ID", updatable=false, insertable=false)
	private Integer building;


	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Room(int id, String roomName, Building notbuilding, Integer building) {
		super();
		this.id = id;
		this.roomName = roomName;
		this.buildingObject = notbuilding;
		this.building = building;
	}


//	public Room(int i, String string) {
//		// TODO Auto-generated constructor stub
//	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}



//	public void setNotbuilding(Building notbuilding) {
//		this.buildingObject = notbuilding;
//	}


	public Integer getBuilding() {
		return building;
	}

	public void setBuilding(Integer building) {
		this.building = building;
	}
}