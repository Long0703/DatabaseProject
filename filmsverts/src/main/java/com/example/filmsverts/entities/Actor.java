package com.example.filmsverts.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "\"Actor\"")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"ActorID\"", nullable = false)
    private Integer actorID; // Sửa từ Long thành Integer

    @Column(name = "\"Firstname\"", length = 128, nullable = false)
    private String firstName;

    @Column(name = "\"Lastname\"", length = 128, nullable = false)
    private String lastName;

    @Column(name = "\"Picture\"", nullable = false, columnDefinition = "TEXT")
    private String picture;

    @Column(name = "\"Gender\"", length = 1, nullable = false)
    private String gender;

    @Column(name = "\"Biography\"", nullable = true, columnDefinition = "TEXT")
    private String biography;

    // Quan hệ ngược với Act
    @OneToMany(mappedBy = "actor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Act> acts = new ArrayList<>();

    // Constructors
    public Actor() {}

    public Actor(String firstName, String lastName, String picture, String gender, String biography) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.picture = picture;
        this.gender = gender;
        this.biography = biography;
    }

    // Getters and Setters
    public Integer getActorID() {
        return actorID;
    }

    public void setActorID(Integer actorID) {
        this.actorID = actorID;
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

    public List<Act> getActs() {
        return acts;
    }

    public void setActs(List<Act> acts) {
        this.acts = acts;
    }
}