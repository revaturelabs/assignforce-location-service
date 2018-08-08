
package com.revature.assignforce.beans;

import java.time.LocalDate;
import java.util.Optional;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Unavailability ")
public class Unavailability {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Unavailability_ID")
	@SequenceGenerator(name = "Unavailability_ID", sequenceName = "Unavailability_ID_seq", allocationSize = 1)
	@Column(name = "UNAVAILABLE_ID")
	private int id;
	
	@Column(name = "DESCRIPTION")
	@NotNull
	private String description;
	
	@Column(name = "END_DATE")
	private LocalDate endDate;
	
	@Column(name = "START_DATE")
	private LocalDate startDate;
	

	//The angular side of the application does not accept a Room object so I need to use the @JsonIgnoreProperties for the roomObject field
	// instead what gets sent to the front-end is the room field which holds the primary key of the room that pertains to 
	// the Unavailability
	@JsonIgnoreProperties
	@ManyToOne(targetEntity=Room.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "ROOMS_ID")
	private Room roomObject;

	
	@Column(name="ROOMS_ID", updatable=false, insertable=false)
	private Integer room;


	
	
	
	
	
	public Unavailability(int id, String description, LocalDate endDate, LocalDate startDate,  Integer room) {
		super();
		this.id = id;
		this.description = description;
		this.endDate = endDate;
		this.startDate = startDate;
		//this.notroom = notroom;
		this.room = room;
	}


	public Unavailability() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	


	public void setRoomObject(Room room) {
		this.roomObject = room;
	}


	public Integer getRoom() {
		return room;
	}


	public void setRoom(Integer room) {
		this.room = room;
	}
}
