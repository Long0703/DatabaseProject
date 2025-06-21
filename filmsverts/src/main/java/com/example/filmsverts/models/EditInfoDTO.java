package com.example.filmsverts.models;

import java.time.LocalDate;

public class EditInfoDTO {
    private Integer editId;
    private Integer movieId;
    private String movieTitle;
    private Integer adminId;
    private String adminName; // Kết hợp firstName + lastName của admin
    private LocalDate editDate;
	public EditInfoDTO(Integer editId, Integer movieId, Integer adminId, LocalDate editDate) {
		super();
		this.editId = editId;
		this.movieId = movieId;
		this.adminId = adminId;
		this.editDate = editDate;
	}
	public Integer getEditId() {
		return editId;
	}
	public void setEditId(Integer editId) {
		this.editId = editId;
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
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public LocalDate getEditDate() {
		return editDate;
	}
	public void setEditDate(LocalDate editDate) {
		this.editDate = editDate;
	}
    
}