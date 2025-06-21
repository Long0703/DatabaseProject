package com.example.filmsverts.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.filmsverts.daos.UsersDAO;
import com.example.filmsverts.entities.Users;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
	private final UsersDAO usersDAO;

    public CustomUserDetailsService(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersDAO.findByUserName(username);
        
        if (users == null) {
            throw new UsernameNotFoundException("Không tìm thấy người dùng: " + username);
        }
        
        // Tạo một UserDetails với ROLE_user
        return User.builder()
                .username(users.getUsername())
                .password(users.getPassword())
                .roles("USER") // Quan trọng: sử dụng ROLE_user
                .build();
    }
}
