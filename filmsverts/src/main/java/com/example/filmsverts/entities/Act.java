package com.example.filmsverts.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"Act\"")
public class Act {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"ActID\"")
    private Integer actId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"ActorID\"", nullable = false)
    private Actor actor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"MovieID\"", nullable = false)
    private Movie movie;

    @Column(name = "\"Role\"", nullable = false, length = 128)
    private String role;

    // Constructors
    public Act() {}

    public Act(Actor actor, Movie movie, String role) {
        this.actor = actor;
        this.movie = movie;
        this.role = role;
    }

    // Getters and Setters
    public Integer getActId() {
        return actId;
    }

    public void setActId(Integer actId) {
        this.actId = actId;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
