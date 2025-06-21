package com.example.filmsverts.models;

import java.time.LocalDate;

public class CommentDTO {
    private Integer commentId;
    private String username; // Chỉ truyền username thay vì toàn bộ Users
    private Integer movieId; // Chỉ truyền ID thay vì toàn bộ Movie
    private String movieTitle; // Tiêu đề phim
    private String comment;
    private LocalDate commentDate;
    private Boolean status;
	public CommentDTO(Integer commentId, String username, Integer movieId, String comment, LocalDate commentDate,
			Boolean status) {
		super();
		this.commentId = commentId;
		this.username = username;
		this.movieId = movieId;
		this.comment = comment;
		this.commentDate = commentDate;
		this.status = status;
	}
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public LocalDate getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(LocalDate commentDate) {
		this.commentDate = commentDate;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
}
