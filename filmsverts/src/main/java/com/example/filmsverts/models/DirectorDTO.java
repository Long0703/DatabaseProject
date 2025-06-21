package com.example.filmsverts.models;

import java.util.List;

public class DirectorDTO {
    private Integer directorId;
    private String firstName;
    private String lastName;
    private String picture;
    private String gender;
    private String biography;
    private List<MovieDTO> movies; // Danh sách phim (nếu cần)
    
	public DirectorDTO(String firstName, String lastName, String picture, String gender) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.picture = picture;
		this.gender = gender;
	}
	public Integer getDirectorId() {
		return directorId;
	}
	public void setDirectorId(Integer directorId) {
		this.directorId = directorId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public List<MovieDTO> getMovies() {
		return movies;
	}
	public void setMovies(List<MovieDTO> movies) {
		this.movies = movies;
	}
}