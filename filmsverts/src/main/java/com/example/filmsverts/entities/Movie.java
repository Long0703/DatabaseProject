package com.example.filmsverts.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "\"Movie\"")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Sử dụng IDENTITY thay vì SEQUENCE
    @Column(name = "\"MovieID\"", nullable = false)
    private Integer movieID; // Thay đổi từ Long sang Integer để khớp với database

    @Column(name = "\"Title\"", nullable = false, length = 256)
    private String title;

    @Column(name = "\"PublishYear\"", nullable = true)
    private Integer publishYear; // Sửa tên từ publishyear thành publishYear

    @Column(name = "\"Description\"", nullable = true, columnDefinition = "TEXT")
    private String description;

    @Column(name = "\"Trailer\"", nullable = true, columnDefinition = "TEXT")
    private String trailer;

    @Column(name = "\"Poster\"", nullable = true, columnDefinition = "TEXT")
    private String poster;

    @Column(name = "\"AvgRatio\"", nullable = false, precision = 2, scale = 1)
    private BigDecimal avgRatio = BigDecimal.ZERO; // Đổi tên từ avg thành avgRatio

    // Quan hệ với Genre
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"GenreID\"", nullable = true)
    private Genre genre;

    // Quan hệ với Director
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"DirectorID\"", nullable = true)
    private Director director;

    // Quan hệ ngược với các entity khác (Optional)
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Act> acts = new ArrayList<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Rate> rates = new ArrayList<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EditInfo> editInfos = new ArrayList<>();

    // Constructors
    public Movie() {}

    public Movie(String title, Integer publishYear, String description, String trailer, String poster) {
        this.title = title;
        this.publishYear = publishYear;
        this.description = description;
        this.trailer = trailer;
        this.poster = poster;
    }

    // Getters and Setters
    public Integer getMovieID() {
        return movieID;
    }

    public void setMovieID(Integer movieID) {
        this.movieID = movieID;
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

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public List<Act> getActs() {
        return acts;
    }

    public void setActs(List<Act> acts) {
        this.acts = acts;
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

    public List<EditInfo> getEditInfos() {
        return editInfos;
    }

    public void setEditInfos(List<EditInfo> editInfos) {
        this.editInfos = editInfos;
    }

    // Utility methods
    public void addAct(Act act) {
        acts.add(act);
        act.setMovie(this);
    }

    public void removeAct(Act act) {
        acts.remove(act);
        act.setMovie(null);
    }

    public void addRate(Rate rate) {
        rates.add(rate);
        rate.setMovie(this);
    }

    public void removeRate(Rate rate) {
        rates.remove(rate);
        rate.setMovie(null);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setMovie(this);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
        comment.setMovie(null);
    }
}