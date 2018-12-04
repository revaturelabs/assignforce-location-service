package com.revature.assignforce.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "Buildings")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Building {

	// Building_ID, Building_Name, Location_ID, Unavailability
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "buildings")
	@SequenceGenerator(name = "buildings", sequenceName = "buildings_seq", allocationSize = 1)
	@Column(name = "BUILDING_ID")
	private int id;

	@Column(name = "IS_ACTIVE")
	private Boolean isActive;

	@Column(name = "BUILDING_NAME")
	@NotNull(message = "buildingName must not be null")
	@Size(min = 1, max =128, message = "buildingName length must be between 1 and 128")
	private String buildingName;
	
	@JsonIgnoreProperties
	@ManyToOne(targetEntity=Location.class,fetch=FetchType.LAZY)
	@JoinColumn(name="LOCATION_ID")
	private Location location;  //these lines currently exist
	
	@Column(name="LOCATION_ID", updatable=false, insertable=false)
	private Integer address;


	public Building() {
		super();
	}

	public Building(int id, Boolean isActive, String buildingName, Integer address) {
		super();
		this.id = id;
		this.isActive = isActive;
		this.buildingName = buildingName;
		//this.location = location;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

//	public void setLocation(Location location) {
//		this.location = location;
//	}
//
//	public Location getLocation(){return location;}

	public Integer getAddress() {
		return address;
	}

	public void setAddress(Integer address) {
		this.address = address;
	}
}

