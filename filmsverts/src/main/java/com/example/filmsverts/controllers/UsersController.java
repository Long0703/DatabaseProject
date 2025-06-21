package com.example.filmsverts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.filmsverts.daos.UsersDAO;
import com.example.filmsverts.daos.AdminsDAO;
import com.example.filmsverts.entities.Users;
import com.example.filmsverts.entities.Admins;
import com.example.filmsverts.entities.Movie;
import com.example.filmsverts.daos.MovieDAO;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UsersController {

    @Autowired
    private UsersDAO usersDAO;
    
    @Autowired
    private AdminsDAO adminsDAO;
    
    @Autowired
    private MovieDAO movieDAO;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, 
                                   @RequestParam String password,
                                   @RequestParam boolean isAdmin) {
        try {
            if (isAdmin) {
                // Admin login
                Long adminId = Long.parseLong(username);
                Admins admin = adminsDAO.findByID(adminId);
                if (admin != null && admin.getPassword().equals(password)) {
                    return ResponseEntity.ok().body(Map.of("success", true, "role", "ADMIN"));
                }
            } else {
                // User login
                Users user = usersDAO.findByUserName(username);
                if (user != null && user.getPassword().equals(password)) {
                    return ResponseEntity.ok().body(Map.of("success", true, "role", "USER"));
                }
            }
            return ResponseEntity.ok().body(Map.of("success", false));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies() {
        try {
            List<Movie> movies = movieDAO.findAll();
            return ResponseEntity.ok(movies);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/movies")
    public ResponseEntity<?> addMovie(@RequestBody Movie movie) {
        try {
            Movie savedMovie = movieDAO.save(movie);
            return ResponseEntity.ok(savedMovie);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        try {
            movieDAO.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, Object> payload) {
        try {
            boolean isAdmin = Boolean.parseBoolean(payload.getOrDefault("isAdmin", false).toString());
            if (isAdmin) {
                // Đăng ký Admin
                Admins admin = new Admins();
                admin.setPassword(payload.get("password").toString());
                admin.setEmail(payload.get("email").toString());
                admin.setFirstname(payload.get("firstname").toString());
                admin.setLastname(payload.get("lastname").toString());
                admin.setPhonenumber(payload.get("phonenumber").toString());
                admin.setDateofbirth(java.time.LocalDate.parse(payload.get("dateofbirth").toString()));
                Admins saved = adminsDAO.save(admin);
                return ResponseEntity.ok(Map.of("success", true, "adminID", saved.getAdminsID()));
            } else {
                // Đăng ký User
                Users user = new Users();
                user.setUsername(payload.get("username").toString());
                user.setPassword(payload.get("password").toString());
                user.setEmail(payload.get("email").toString());
                user.setFirstname(payload.get("firstname").toString());
                user.setLastname(payload.get("lastname").toString());
                user.setPhonenumber(payload.get("phonenumber").toString());
                user.setDateofbirth(java.time.LocalDate.parse(payload.get("dateofbirth").toString()));
                Users saved = usersDAO.save(user);
                return ResponseEntity.ok(Map.of("success", true, "username", saved.getUsername()));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "error", e.getMessage()));
        }
    }
}
