package com.revature.assignforce.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


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
	@NotNull(message = "name must not be null")
	@Size(min = 1, max = 128, message = "RoomName size must be between 1 and 128")
	private String name;
	
	@Column(name = "Room_Capacity")
	@NotNull(message = "roomCapacity must not be null")
	@Max(value=25)
	@Min(value=1)
	@JsonProperty(value="capacity")
	private int roomCapacity;

//	@JsonIgnoreProperties
//	@ManyToOne(targetEntity=Building.class,fetch=FetchType.LAZY)
//	@JoinColumn(name = "BUILDING_ID")
//	private Building buildingObject;

	@Column(name="BUILDING_ID")
	private Integer building;

	@Column(name="ACTIVE")
	private boolean active;


	public Room() {
		super();
	}

	public Room(int id, String name, int building, int roomCapacity) {
		super();
		this.id = id;
		this.name = name;
		this.roomCapacity = roomCapacity;
		//this.buildingObject = notbuilding;
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


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public void setNotbuilding(Building notbuilding) {
//		this.buildingObject = notbuilding;
//	}

	public int getRoomCapacity() {
		return roomCapacity;
	}

	public void setRoomCapacity(int roomCapacity) {
		this.roomCapacity = roomCapacity;
	}

	public Integer getBuilding() {
		return building;
	}

	public void setBuilding(Integer building) {
		this.building = building;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
