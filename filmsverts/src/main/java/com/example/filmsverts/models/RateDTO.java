package com.example.filmsverts.models;

import java.time.LocalDate;

public class RateDTO {
    private String username;
    private Integer movieId;
    private String movieTitle;
    private Integer rate;
    private LocalDate rateDate;
	public RateDTO(String username, Integer movieId, Integer rate, LocalDate rateDate) {
		super();
		this.username = username;
		this.movieId = movieId;
		this.rate = rate;
		this.rateDate = rateDate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getMovieId() {
		return movieId;
	}
	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	public Integer getRate() {
		return rate;
	}
	public void setRate(Integer rate) {
		this.rate = rate;
	}
	public LocalDate getRateDate() {
		return rateDate;
	}
	public void setRateDate(LocalDate rateDate) {
		this.rateDate = rateDate;
	}
    
}