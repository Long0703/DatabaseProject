package com.example.filmsverts.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "\"Admins\"",
    uniqueConstraints = {
        @UniqueConstraint(name = "Admins_Email_key", columnNames = "\"Email\"")
    }
)
public class Admins {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"AdminID\"", nullable = false) // Sửa từ "Admins" thành "AdminID"
    private Integer adminID; // Sửa từ Long thành Integer
    
    @Column(name = "\"Password\"", length = 128, nullable = false)
    private String password;
    
    @Column(name = "\"Email\"", length = 256, nullable = false)
    private String email;
    
    @Column(name = "\"Firstname\"", length = 128, nullable = false)
    private String firstName;
    
    @Column(name = "\"Lastname\"", length = 128, nullable = false)
    private String lastName;
    
    @Column(name = "\"PhoneNumber\"", length = 32, nullable = false)
    private String phoneNumber;
    
    @Column(name = "\"DateOfBirth\"", nullable = false)
    private LocalDate dateOfBirth;

    // Quan hệ ngược với EditInfo
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EditInfo> editInfos = new ArrayList<>();

    // Constructors
    public Admins() {}

    public Admins(String password, String email, String firstName, String lastName, String phoneNumber, LocalDate dateOfBirth) {
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    // Getters and Setters
    public Integer getAdminID() {
        return adminID;
    }

    public void setAdminID(Integer adminID) {
        this.adminID = adminID;
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

    public List<EditInfo> getEditInfos() {
        return editInfos;
    }

    public void setEditInfos(List<EditInfo> editInfos) {
        this.editInfos = editInfos;
    }
}
