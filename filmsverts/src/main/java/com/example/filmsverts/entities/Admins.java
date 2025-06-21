package com.example.filmsverts.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
@Entity
@Table(name = "\"Admins\"",
uniqueConstraints = {
    @UniqueConstraint(name = "Admins_Email_key", columnNames = "\"Email\"")
}
)
public class Admins {
	@Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE
    )
	@Column(name = "\"Admins\"", nullable = false)
	private Long adminsID;
	
	@Column(name = "\"Password\"", length = 128, nullable = false)
	private String password;
	
	@Column(name = "\"Email\"", length = 256, nullable = false)
	private String email;
	
	@Column(name = "\"Firstname\"", length = 128, nullable = false)
	private String firstname;
	
	@Column(name = "\"Lastname\"", length = 128, nullable = false)
	private String lastname;
	
	@Column(name = "\"PhoneNumber\"", length = 32, nullable = false)
	private String phonenumber;
	
	@Column(name = "\"DateOfBirth\"", nullable = false)
	private LocalDate dateofbirth;

	public Long getAdminsID() {
		return adminsID;
	}

	public void setAdminsID(Long adminsID) {
		this.adminsID = adminsID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public LocalDate getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(LocalDate dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
}
