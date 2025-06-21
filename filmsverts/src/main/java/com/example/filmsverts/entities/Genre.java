package com.example.filmsverts.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"Genre\"")
public class Genre {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "\"GenreID\"", nullable = false)
	private Long genreID;
	
	@Column(name = "\"GenreName\"", length = 64, nullable = false)
	private String genreName;
	
	@Column(name = "\"NumberOfMovies\"", nullable = true)
	private Integer numberOfMovies;

	public Long getGenreID() {
		return genreID;
	}

	public void setGenreID(Long genreID) {
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
}
