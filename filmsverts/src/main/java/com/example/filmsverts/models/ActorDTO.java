package com.example.filmsverts.models;

import java.util.List;

public class ActorDTO {
    private Integer actorId;
    private String firstName;
    private String lastName;
    private String picture;
    private String gender;
    private String biography;
    private List<ActDTO> acts; // Danh sách vai diễn (nếu cần)
	public ActorDTO(String firstName, String lastName, String picture, String gender) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.picture = picture;
		this.gender = gender;
	}
	public Integer getActorId() {
		return actorId;
	}
	public void setActorId(Integer actorId) {
		this.actorId = actorId;
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
	public List<ActDTO> getActs() {
		return acts;
	}
	public void setActs(List<ActDTO> acts) {
		this.acts = acts;
	}
    
}