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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


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
	@NotNull(message = "buildingName must not be null")
	@Size(min = 1, max =128, message = "buildingName length must be between 1 and 128")
	private String buildingName;
	
//	@JsonIgnoreProperties
//	@ManyToOne(targetEntity=Location.class,fetch=FetchType.LAZY)
//	@JoinColumn(name="LOCATION_ID")
//	private Location address;  //these lines currently exist
	
	@Column(name="LOCATION_ID")
	private Integer address;

	@JsonIgnoreProperties
	@Transient
	private int id;

	public Building() {
		super();
	}

	public Building(int buildingId, Boolean isActive, String buildingName, Integer address) {
		super();
		this.buildingId = buildingId;
		this.isActive = isActive;
		this.buildingName = buildingName;
		//this.location = location;
		this.address = address;
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

