package com.example.filmsverts.models;

import java.math.BigDecimal;
import java.util.List;

public class MovieDTO {
    private Integer movieId;
    private String title;
    private Integer publishYear;
    private String description;
    private String trailer;
    private String poster;
    private BigDecimal avgRatio;
    private Integer genreId;
    private String genreName; // Tên thể loại
    private Integer directorId;
    private String directorName; // Tên đạo diễn
    private List<ActDTO> acts; // Danh sách vai diễn
    private List<CommentDTO> comments; // Danh sách bình luận
    private List<RateDTO> rates; // Danh sách đánh giá
	public MovieDTO(Integer movieId, String title, Integer publishYear, BigDecimal avgRatio) {
		super();
		this.movieId = movieId;
		this.title = title;
		this.publishYear = publishYear;
		this.avgRatio = avgRatio;
	}
	public Integer getMovieId() {
		return movieId;
	}
	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getPublishYear() {
		return publishYear;
	}
	public void setPublishYear(Integer publishYear) {
		this.publishYear = publishYear;
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
	public BigDecimal getAvgRatio() {
		return avgRatio;
	}
	public void setAvgRatio(BigDecimal avgRatio) {
		this.avgRatio = avgRatio;
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
	public Integer getDirectorId() {
		return directorId;
	}
	public void setDirectorId(Integer directorId) {
		this.directorId = directorId;
	}
	public String getDirectorName() {
		return directorName;
	}
	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}
	public List<ActDTO> getActs() {
		return acts;
	}
	public void setActs(List<ActDTO> acts) {
		this.acts = acts;
	}
	public List<CommentDTO> getComments() {
		return comments;
	}
	public void setComments(List<CommentDTO> comments) {
		this.comments = comments;
	}
	public List<RateDTO> getRates() {
		return rates;
	}
	public void setRates(List<RateDTO> rates) {
		this.rates = rates;
	}
    
}