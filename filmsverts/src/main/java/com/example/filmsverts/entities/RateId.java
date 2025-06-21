package com.example.filmsverts.entities;

import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("serial")
public class RateId implements Serializable {
    private String user; // Tương ứng với Username
    private Integer movie; // Tương ứng với MovieID

    public RateId() {}

    public RateId(String user, Integer movie) {
        this.user = user;
        this.movie = movie;
    }

    // Getters and Setters
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getMovie() {
        return movie;
    }

    public void setMovie(Integer movie) {
        this.movie = movie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RateId rateId = (RateId) o;
        return Objects.equals(user, rateId.user) && Objects.equals(movie, rateId.movie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, movie);
    }
}