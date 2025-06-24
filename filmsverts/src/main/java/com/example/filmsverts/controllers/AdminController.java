package com.example.filmsverts.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.filmsverts.daos.AdminsDAO;
import com.example.filmsverts.entities.Admins;


@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminsDAO adminsDAO;
    
	@GetMapping("/login")
	public String showLoginForm(@RequestParam(value = "error", required = false) String error, Model model) {
	    if (error != null) {
	        model.addAttribute("error", "Sai tài khoản hoặc mật khẩu!");
	    }
	    return "adminLogin";
	}

	@GetMapping("/profile")
	public String showProfile(Principal principal, Model model) {
		Integer adminId = Integer.parseInt(principal.getName());
	    Admins admin = adminsDAO.findByID(adminId);
	    model.addAttribute("admin", admin);
	    return "adminProfile";
	}
}
