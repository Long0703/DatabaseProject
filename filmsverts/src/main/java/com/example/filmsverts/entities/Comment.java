package com.example.filmsverts.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"Comment\"")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"CommentID\"")
    private Integer commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"Username\"", nullable = false)
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"MovieID\"", nullable = false)
    private Movie movie;

    @Column(name = "\"Comment\"", nullable = false, columnDefinition = "TEXT")
    private String comment;

    @Column(name = "\"CommentDate\"", nullable = false)
    private LocalDate commentDate;

    @Column(name = "\"Status\"", nullable = false)
    private Boolean status;

    // Constructors
    public Comment() {}

    public Comment(Users user, Movie movie, String comment, LocalDate commentDate, Boolean status) {
        this.user = user;
        this.movie = movie;
        this.comment = comment;
        this.commentDate = commentDate;
        this.status = status;
    }

    // Getters and Setters
    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
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