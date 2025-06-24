package com.example.filmsverts.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.filmsverts.daos.AdminsDAO;
import com.example.filmsverts.entities.Admins;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AdminController {
    @Autowired
    private AdminsDAO adminsDAO;
    
    @Autowired
    @Qualifier("adminPasswordEncoder")
    private PasswordEncoder passwordEncoder;
    
    @GetMapping("/admin/login")
    public String showLoginPage(Model model, HttpServletRequest request) {
        if (SecurityContextHolder.getContext().getAuthentication() != null &&
            SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
            !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            return "redirect:/admin/profile";
        }
        model.addAttribute("admin", new Admins());
        return "admin-login";
    }

    @GetMapping("/admin/profile")
    public String viewProfile(Model model, Principal principal) {
        if (principal != null) {
            String adminID = principal.getName();
            System.out.println("AdminID từ Principal: " + adminID);
            Admins admin = adminsDAO.findByID(Integer.parseInt(adminID));
            model.addAttribute("admin", admin);
            return "admin-profile";
        } else {
            System.out.println("No Principal, redirecting to login");
            return "redirect:/admin/login";
        }
    }

    @GetMapping("/admin/logout")
    public String logout(HttpServletRequest request) {
        SecurityContextHolder.clearContext();
        request.getSession().invalidate();
        return "redirect:/admin/login?logout=true";
    }
}