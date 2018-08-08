
package com.revature.assignforce.beans;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

//@Component
@Entity
@Table(name = "Location")
public class Location {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Location_ID")
	@SequenceGenerator(name = "Location_ID", sequenceName = "Location_ID_Seq", allocationSize = 1)
	@Column
	private int id;

	@Column(name = "LOCATION_NAME")
	private String name;

	@Column
	private String city;

	@Column
	private String state;

	@Column(name = "IS_ACTIVE")
	private Boolean isActive;
/*
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Building> buildings;*/

	public Location() {
		super();
	}

	public Location(int id, String name, String city, String state, Boolean isActive) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.state = state;
		this.isActive = isActive;
		
	}

	public Location(int i, String string, String string2, String string3, boolean b, HashSet<Building> buildingSet) {
		// TODO Auto-generated constructor stub
	}

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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}



}

