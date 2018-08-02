package com.revature.assignforce.beans;

import java.sql.Date;

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
public class Unavailability {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unavailable")
	@SequenceGenerator(name = "unavailable", sequenceName = "unavailable_seq", allocationSize = 1)
	@Column(name = "UNAVAILABLE_ID")
	private int id;
        
        
	@Column(name = "START_DATE")
	private Date startDate;

        
	@Column(name = "END_DATE")
	private Date endDate;

	@Column(name = "DESCRIPTION")
	private String description;

	public Unavailability() {
		super();
	}

	public Unavailability(int id, Date startDate, Date endDate, String description) {
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
