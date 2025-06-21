package com.example.filmsverts.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "\"Genre\"")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Sửa từ SEQUENCE
    @Column(name = "\"GenreID\"", nullable = false)
    private Integer genreID; // Sửa từ Long thành Integer

    @Column(name = "\"GenreName\"", length = 64, nullable = false)
    private String genreName;

    @Column(name = "\"NumberOfMovies\"", nullable = true)
    private Integer numberOfMovies;

    // Quan hệ ngược với Movie
    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Movie> movies = new ArrayList<>();

    // Constructors
    public Genre() {}

    public Genre(String genreName, Integer numberOfMovies) {
        this.genreName = genreName;
        this.numberOfMovies = numberOfMovies;
    }

    // Getters and Setters
    public Integer getGenreID() {
        return genreID;
    }

    public void setGenreID(Integer genreID) {
        this.genreID = genreID;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public Integer getNumberOfMovies() {
        return numberOfMovies;
    }

    public void setNumberOfMovies(Integer numberOfMovies) {
        this.numberOfMovies = numberOfMovies;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}