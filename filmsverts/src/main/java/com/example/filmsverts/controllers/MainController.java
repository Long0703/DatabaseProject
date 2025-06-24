package com.example.filmsverts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.filmsverts.daos.MovieDAO;
import com.example.filmsverts.entities.Movie;

@Controller
public class MainController {
	
	@Autowired
	private MovieDAO movieDAO;
	
	@GetMapping("/")
    public String showHomePage(Model model,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "15") int size) {
        // Tạo Pageable với số trang và kích thước (15 phim/trang)
        Pageable pageable = PageRequest.of(page, size);
        Page<Movie> moviePage = movieDAO.findAll(pageable);

        model.addAttribute("movies", moviePage.getContent()); // Danh sách phim hiện tại
        model.addAttribute("currentPage", page); // Trang hiện tại
        model.addAttribute("totalPages", moviePage.getTotalPages()); // Tổng số trang
        model.addAttribute("title", "Home - FilmsVert");
        return "home";
    }
	
}
