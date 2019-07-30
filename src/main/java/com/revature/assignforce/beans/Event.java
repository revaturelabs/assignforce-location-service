package com.revature.assignforce.beans;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Event")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Event_ID")
	@SequenceGenerator(name = "Event_ID", sequenceName = "Event_ID_seq", allocationSize = 1)
	@Column(name = "Event_ID")
	private int id;

	@Column(name = "END_DATE")
	@NotNull(message = "endDate must not be null")
	private LocalDate endDate;

	@Column(name = "START_DATE")
	@NotNull(message = "startDate must not be null")
	private LocalDate startDate;

	@Column(name = "NAME")
	private String name;

	@Column(name = "CREATED_DATE")
	@NotNull(message = "createdDate must not be null")
	private LocalDate createdDate;

	@Column(name = "ROOM_ID")
	private Integer roomID;

	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Event(int id, @NotNull(message = "startDate must not be null") LocalDate startDate,
			@NotNull(message = "endDate must not be null") LocalDate endDate, String name,
			@NotNull(message = "createdDate must not be null") LocalDate createdDate, Integer roomID) {
		super();
		this.id = id;
		this.endDate = endDate;
		this.startDate = startDate;
		this.name = name;
		this.createdDate = createdDate;
		this.roomID = roomID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRoomID() {
		return roomID;
	}

	public void setRoomID(Integer roomID) {
		this.roomID = roomID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((roomID == null) ? 0 : roomID.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (roomID == null) {
			if (other.roomID != null)
				return false;
		} else if (!roomID.equals(other.roomID))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", endDate=" + endDate + ", startDate=" + startDate + ", name=" + name
				+ ", createdDate=" + createdDate + ", roomID=" + roomID + "]";
	}

}
