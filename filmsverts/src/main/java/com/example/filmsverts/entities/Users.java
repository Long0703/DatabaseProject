package com.example.filmsverts.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "\"Users\"",
    uniqueConstraints = {
        @UniqueConstraint(name = "Users_username_key", columnNames = "\"Username\""),
        @UniqueConstraint(name = "Users_Email_key", columnNames = "\"Email\"")
    }
)
public class Users {
    @Id
    @Column(name = "\"Username\"", length = 128, nullable = false)
    private String username; // Username là primary key
    
    @Column(name = "\"Password\"", length = 128, nullable = false)
    private String password;
    
    @Column(name = "\"Email\"", length = 256, nullable = false)
    private String email;
    
    @Column(name = "\"Firstname\"", length = 128, nullable = false)
    private String firstName; // Sửa tên theo convention
    
    @Column(name = "\"Lastname\"", length = 128, nullable = false)
    private String lastName;
    
    @Column(name = "\"PhoneNumber\"", length = 32, nullable = false)
    private String phoneNumber;
    
    @Column(name = "\"DateOfBirth\"", nullable = false)
    private LocalDate dateOfBirth;

    // Quan hệ ngược
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Rate> rates = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    // Constructors
    public Users() {}

    public Users(String username, String password, String email, String firstName, String lastName, String phoneNumber, LocalDate dateOfBirth) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}