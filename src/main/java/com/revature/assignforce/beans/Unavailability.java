package com.revature.assignforce.beans;

import com.revature.assignforce.validators.IntervalHolder;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "UNAVAILABILITY")
@IntervalHolder(startInterval="startDate", endInterval="endDate", inclusive=true)
public class Unavailability {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unavailable")
	@SequenceGenerator(name = "unavailable", sequenceName = "unavailable_seq", allocationSize = 1)
	@Column(name = "UNAVAILABLE_ID")
	private int id;
        
        @NotNull
	@Column(name = "START_DATE")
	private LocalDate startDate;

        @NotNull
	@Column(name = "END_DATE")
	private LocalDate endDate;

        // it makes sense for a description to be empty but not for it to be null
        @NotNull
	@Column(name = "DESCRIPTION")
	private String description;

	public Unavailability() {
		super();
	}

	public Unavailability(int id, LocalDate startDate, LocalDate endDate, String description) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
