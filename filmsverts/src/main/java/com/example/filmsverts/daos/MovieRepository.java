package com.example.filmsverts.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.filmsverts.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    // Không cần thêm phương thức, JpaRepository đã hỗ trợ findAll với Pageable
}