package com.example.filmsverts.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"Director\"")
public class Director {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "\"DirectorID\"", nullable = false)
	private Long DirectorID;
	
	@Column(name = "\"Firstname\"", length = 128, nullable = false)
	private String firstname;
	
	@Column(name = "\"Lastname\"", length = 128, nullable = false)
	private String lastname;
	
	@Column(name = "\"Picture\"", nullable = false)
	private String picture;
	
	@Column(name = "\"Gender\"", length = 1, nullable = false)
	private String gender;
	
	@Column(name = "\"Biography\"", nullable = true)
	private String biography;

	public Long getDirectorID() {
		return DirectorID;
	}

	public void setDirectorID(Long DirectorID) {
		this.DirectorID = DirectorID;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}
}
