package com.revature.assignforce.beans;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Room")
public class Unavailibility {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "buildings")
	@SequenceGenerator(name = "buildings", sequenceName = "buildings_seq", allocationSize = 1)
	@Column(name = "UNAVAILABLE_ID")
	private int buildingId;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "END_DATE")
	private LocalDate endDate;
	
	@Column(name = "START_DATE")
	private LocalDate startDate;
	
	@ManyToOne
	@JoinColumn(name = "ROOMS_ID")
	private Building building;
	

	public Unavailibility() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Unavailibility(int buildingId, String description, LocalDate endDate, LocalDate startDate,
			Building building) {
		super();
		this.buildingId = buildingId;
		this.description = description;
		this.endDate = endDate;
		this.startDate = startDate;
		this.building = building;
	}



	public int getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(int buildingId) {
		this.buildingId = buildingId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	
	
	
	

}
