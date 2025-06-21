package com.example.filmsverts.models;

import java.time.LocalDate;
import java.util.List;

public class AdminDTO {
    private Integer adminId;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private List<EditInfoDTO> editInfos; // Danh sách chỉnh sửa (nếu cần)
	public AdminDTO(String email, String firstName, String lastName, String phoneNumber, LocalDate dateOfBirth) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
	}
	public Integer getAdminId() {
		return adminId;
	}
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public List<EditInfoDTO> getEditInfos() {
		return editInfos;
	}
	public void setEditInfos(List<EditInfoDTO> editInfos) {
		this.editInfos = editInfos;
	}
    
}
