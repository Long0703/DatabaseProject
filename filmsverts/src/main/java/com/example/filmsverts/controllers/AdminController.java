package com.example.filmsverts.controllers;

import java.security.Principal;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.filmsverts.daos.AdminsDAO;
import com.example.filmsverts.entities.Admins;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AdminController {
	@Autowired
	private AdminsDAO adminsDAO;
    
	@GetMapping("/admin/login")
	public String showLoginPage(Model model) {
	    model.addAttribute("admin", new Admins());
	    return "admin-login";
	}

	@SuppressWarnings("deprecation")
	@PostMapping("/admin/login")
	public String processLogin(@RequestParam Integer adminID,
	                        @RequestParam String password,
	                        Model model,
	                        HttpServletRequest request) {
	    System.out.println("Bắt đầu processLogin với adminID: " + adminID);
	    try {
	        Admins admin = adminsDAO.findByID(adminID);
	        if (admin != null && password.equals(admin.getPassword())) {
	            UsernamePasswordAuthenticationToken authToken = 
	                new UsernamePasswordAuthenticationToken(
	                    adminID.toString(),
	                    password,
	                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
	                );
	            SecurityContextHolder.getContext().setAuthentication(authToken);
	            new SecurityContextPersistenceFilter().doFilter(request, null, (req, res) -> {});
	            request.getSession().setAttribute("adminID", adminID);
	            System.out.println("Đăng nhập thành công! AuthToken: " + authToken);
	            System.out.println("Redirecting to /admin/profile...");
	            return "redirect:/admin/profile";
	        } else {
	            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu sai!");
	            System.out.println("Đăng nhập thất bại: Sai thông tin");
	            return "admin-login";
	        }
	    } catch (Exception e) {
	        model.addAttribute("error", "Lỗi: " + e.getMessage());
	        System.out.println("Lỗi trong processLogin: " + e.getMessage());
	        return "admin-login";
	    }
	}

	@GetMapping("/admin/profile")
	public String viewProfile(Model model, Principal principal, HttpServletRequest request) {
	    System.out.println("Bắt đầu viewProfile");
	    System.out.println("Principal: " + (principal != null ? principal.getName() : "null"));
	    System.out.println("Session adminID: " + request.getSession().getAttribute("adminID"));
	    
	    Integer adminID = null;
	    if (principal != null) {
	        try {
	            adminID = Integer.parseInt(principal.getName());
	            System.out.println("Parsed adminID from Principal: " + adminID);
	        } catch (NumberFormatException e) {
	            System.out.println("Không thể parse adminID từ Principal: " + e.getMessage());
	        }
	    }
	    
	    if (adminID == null) {
	        adminID = (Integer) request.getSession().getAttribute("adminID");
	        System.out.println("Fallback adminID from session: " + adminID);
	    }
	    
	    if (adminID != null) {
	        System.out.println("AdminID sử dụng: " + adminID);
	        Admins admin = adminsDAO.findByID(adminID);
	        if (admin != null) {
	            model.addAttribute("admin", admin);
	            System.out.println("Rendering admin-profile with admin: " + admin.getAdminID());
	            return "admin-profile";
	        } else {
	            System.out.println("Admin not found for adminID: " + adminID);
	            return "redirect:/admin/login";
	        }
	    } else {
	        System.out.println("No adminID found, redirecting to login");
	        return "redirect:/admin/login";
	    }
	}
}
