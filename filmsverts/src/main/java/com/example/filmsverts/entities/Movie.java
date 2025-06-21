package com.example.filmsverts.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"Movie\"")
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "\"MovieID\"", nullable = false)
	private Long movieID;
	
	@Column(name = "\"Title\"", nullable = false, length = 256)
	private String title;
	
	@Column(name = "\"PublishYear\"", nullable = true)
	private Integer publishyear;
	
	@Column(name = "\"Description\"", nullable = true)
	private String description;
	
	@Column(name = "\"Trailer\"", nullable = true)
	private String trailer;
	
	@Column(name = "\"Poster\"", nullable = true)
	private String poster;
	
	@Column(name = "\"GenreID\"", nullable = true)
	private Long genreID;
	
	@Column(name = "\"DirectorID\"", nullable = true)
	private Long DirectorID;
	
	@Column(name = "\"AvgRatio\"", nullable = false)
	private BigDecimal avg = BigDecimal.ZERO;

	public Long getMovieID() {
		return movieID;
	}

	public void setMovieID(Long movieID) {
		this.movieID = movieID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getPublishyear() {
		return publishyear;
	}

	public void setPublishyear(Integer publishyear) {
		this.publishyear = publishyear;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public Long getGenreID() {
		return genreID;
	}

	public void setGenreID(Long genreID) {
		this.genreID = genreID;
	}

	public Long getDirectorID() {
		return DirectorID;
	}

	public void setDirectorID(Long directorID) {
		DirectorID = directorID;
	}

	public BigDecimal getAvg() {
		return avg;
	}

	public void setAvg(BigDecimal avg) {
		this.avg = avg;
	}
}
