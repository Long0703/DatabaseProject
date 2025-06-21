package com.example.filmsverts.models;

public class ActDTO {
    private Integer actId;
    private Integer actorId; // Chỉ truyền ID thay vì toàn bộ Actor
    private String actorName; // Tên diễn viên (kết hợp firstName và lastName)
    private Integer movieId; // Chỉ truyền ID thay vì toàn bộ Movie
    private String movieTitle; // Tiêu đề phim
    private String role;
    
	public ActDTO(Integer actorId, Integer movieId, String role) {
		super();
		this.actorId = actorId;
		this.movieId = movieId;
		this.role = role;
	}
	public Integer getActId() {
		return actId;
	}
	public void setActId(Integer actId) {
		this.actId = actId;
	}
	public Integer getActorId() {
		return actorId;
	}
	public void setActorId(Integer actorId) {
		this.actorId = actorId;
	}
	public String getActorName() {
		return actorName;
	}
	public void setActorName(String actorName) {
		this.actorName = actorName;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}