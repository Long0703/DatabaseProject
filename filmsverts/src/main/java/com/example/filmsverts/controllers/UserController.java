package com.example.filmsverts.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.filmsverts.daos.AdminsDAO;
import com.example.filmsverts.daos.MovieDAO;
import com.example.filmsverts.daos.UsersDAO;
import com.example.filmsverts.entities.Users;

import jakarta.transaction.Transactional;

@Controller
public class UserController {

    @Autowired
    private UsersDAO usersDAO;
    
    @Autowired
    private AdminsDAO adminsDAO;
    
    @Autowired
    private MovieDAO movieDAO;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/user/register")
    public String showRegisterPage(Model model) {
    	model.addAttribute("user", new Users());
    	return "user-register";
    }
    
    @PostMapping("/user/register")
    @Transactional
    public String processRegisterManager(@ModelAttribute("user") Users users, Model model) {
        try {
            // 1. Kiểm tra xem username đã tồn tại trong database chưa

            if (usersDAO.existedUser(users.getUsername())) {
                // Nếu username đã tồn tại, thêm thông báo lỗi vào model và trả về trang đăng ký
                model.addAttribute("error", "Tên đăng nhập đã tồn tại. Vui lòng chọn tên khác.");
                return "user-register";
            }

            // 2. Nếu username chưa tồn tại, tiến hành mã hóa mật khẩu và thêm người dùng
            users.setPassword(passwordEncoder.encode(users.getPassword()));
            usersDAO.addUser(users);
            return "redirect:/user/login?registered=true";

        } catch (Exception e) {
            // Xử lý các lỗi khác (ví dụ: lỗi database)
            model.addAttribute("error", "Đã xảy ra lỗi trong quá trình đăng ký: " + e.getMessage());
            // Quan trọng: Đánh dấu transaction là rollback-only để tránh dữ liệu không nhất quán
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return "user-register";
        }
    }
    
    @GetMapping("/user/login")
    public String showLoginPage() {
    	return "user-login";
    }
    
    @PostMapping("/user/login")
    public String processLogin(@RequestParam String username, 
                           @RequestParam String password, 
                           Model model) {
        try {
            Users user = usersDAO.findByUserName(username);
            if (user != null && passwordEncoder.matches(password, user.getPassword())) {
                System.out.println("Đăng nhập thành công!");
                return "redirect:/user/profile"; // Chuyển sang controller hiển thị dashboard
            } else {
                model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu sai!");
                return "redirect:/user/login";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi: " + e.getMessage());
            return "user-login";
        }
    }
    
    @GetMapping("/user/profile")
    public String viewProfile(Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            System.out.println("Username từ Principal: " + username);

            Users user = usersDAO.findByUserName(username);
            model.addAttribute("user", user);
            return "user-profile";
        } else {
            return "redirect:/user/login";
        }
    }
    	
}

