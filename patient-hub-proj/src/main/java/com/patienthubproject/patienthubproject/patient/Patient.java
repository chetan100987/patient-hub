package com.patienthubproject.patienthubproject.patient;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity(name = "patient_details")
public class Patient {
	
	protected Patient()
	{
		
	}
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min=2, message = "Name should have atleast 2 characters")
	//@JsonProperty("patient_name")
	private String name;
	
	@Past(message = "Birth Date should be in past ") 
	//@JsonProperty("birth_date")
	private LocalDate birthDate;
	
	public Patient(Integer id, String name, LocalDate birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
	
	 
}
