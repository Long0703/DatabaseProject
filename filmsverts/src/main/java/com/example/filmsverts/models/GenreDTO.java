package com.example.filmsverts.models;

import java.util.List;

public class GenreDTO {
    private Integer genreId;
    private String genreName;
    private Integer numberOfMovies;
    private List<MovieDTO> movies; // Danh sách phim (nếu cần)
	public GenreDTO(Integer genreId, String genreName) {
		super();
		this.genreId = genreId;
		this.genreName = genreName;
	}
	public Integer getGenreId() {
		return genreId;
	}
	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
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
	public List<MovieDTO> getMovies() {
		return movies;
	}
	public void setMovies(List<MovieDTO> movies) {
		this.movies = movies;
	}
    
}