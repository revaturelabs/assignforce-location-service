package com.revature.assignforce.beans;

import java.io.IOException;
import java.util.HashSet;
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

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

//@Component
@Entity
@Table(name = "Buildings")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Building {

	// Building_ID, Building_Name, Location_ID, Unavailability
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "buildings")
	@SequenceGenerator(name = "buildings", sequenceName = "buildings_seq", allocationSize = 1)
	@Column(name = "BUILDING_ID")
	private int buildingId;

	@Column(name = "IS_ACTIVE")
	private Boolean isActive;

	@Column(name = "BUILDING_NAME")
	private String buildingName;
	
	@ManyToOne
	@JoinColumn(name = "BUILDINGS_ID")
	private Location address;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	private Set<Room> rooms;

	public Building() {
		super();
	}
	
	

	public Building(int buildingId, Boolean isActive, String buildingName, Location address, Set<Room> rooms) {
		super();
		this.buildingId = buildingId;
		this.isActive = isActive;
		this.buildingName = buildingName;
		this.address = address;
		this.rooms = rooms;
	}



	public Building(int i, boolean b, String string, HashSet<Room> roomSet) {
		// TODO Auto-generated constructor stub
	}



	public int getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(int buildingId) {
		this.buildingId = buildingId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public Location getAddress() {
		return address;
	}

	public void setAddress(Location address) {
		this.address = address;
	}

	public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}

	
}


