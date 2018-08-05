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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;


@Component
@Entity
@Table(name = "Room")
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
	
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.REMOVE })
	@JoinColumn
	private Set<Unavailability> Unavailabilities;

	
	public Room() {
		super();
	}

	public Room(int id,
			@NotNull(message = "roomName must not be null") @Size(min = 1, max = 128, message = "RoomName size must be between 1 and 128") String roomName,
			Set<Unavailability> unavailabilities) {
		super();
		this.id = id;
		this.roomName = roomName;
		Unavailabilities = unavailabilities;
	}

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

	public Set<Unavailability> getUnavailabilities() {
		return Unavailabilities;
	}

	public void setUnavailabilities(Set<Unavailability> unavailabilities) {
		Unavailabilities = unavailabilities;
	}
	
	

}