package com.example.filmsverts.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.filmsverts.daos.AdminsDAO;
import com.example.filmsverts.entities.Admins;

public class CustomAdminDetailsService implements UserDetailsService {
	@Autowired
    private final AdminsDAO adminsDAO;

    public CustomAdminDetailsService(AdminsDAO adminsDAO) {
        this.adminsDAO = adminsDAO;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Chuyển username (thực ra là ID) thành Long
        Long adminId;
        try {
            adminId = Long.parseLong(username);
        } catch (NumberFormatException e) {
            throw new UsernameNotFoundException("ID quản trị viên không hợp lệ: " + username);
        }
        
        Admins admin = adminsDAO.findByID(adminId);
        if (admin == null) {
            throw new UsernameNotFoundException("Không tìm thấy quản trị viên với ID: " + username);
        }

        return User.builder()
                .username(admin.getAdminID().toString()) // Dùng ID làm username
                .password(admin.getPassword()) // Password đã mã hóa
                .roles("ADMIN")
                .build();
    }
}
