package com.example.filmsverts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class FilmsvertsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmsvertsApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(PasswordEncoder passwordEncoder) {
		return args -> {
			String originalPassword = "admin123";
			String encodedPassword = passwordEncoder.encode(originalPassword);
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println("!!! HASH MẬT KHẨU CHO ADMIN !!!");
			System.out.println("Mật khẩu gốc: " + originalPassword);
			System.out.println("Mật khẩu đã mã hóa: " + encodedPassword);
			System.out.println("!!! HÃY COPY CHUỖI MÃ HÓA TRÊN VÀ CẬP NHẬT VÀO DATABASE !!!");
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		};
	}
}
