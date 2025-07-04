package com.example.filmsverts.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.example.filmsverts.entities.Movie;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class MovieDAO {
    
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private MovieRepository movieRepository; // Thêm repository
    @SuppressWarnings("unchecked")
	public List<Movie> findAll() {
        String sql = "Select e from " + Movie.class.getName() + " e";
        Query query = entityManager.createQuery(sql, Movie.class);
        return query.getResultList();
    }
    
    public Movie save(Movie movie) {
        if (movie.getMovieID() == null) {
            entityManager.persist(movie);
        } else {
            movie = entityManager.merge(movie);
        }
        return movie;
    }
    
    public void deleteById(Long id) {
        Movie movie = entityManager.find(Movie.class, id);
        if (movie != null) {
            entityManager.remove(movie);
        }
    }
    public Page<Movie> findAll(Pageable pageable) {
        return movieRepository.findAll(pageable); // Sử dụng repository cho phân trang
    }
    public Movie findById(Long id) {
        return entityManager.find(Movie.class, id);
    }
    
} 