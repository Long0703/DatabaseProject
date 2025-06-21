package com.example.filmsverts.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.filmsverts.entities.Users;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class UsersDAO {
	@Autowired
	private EntityManager entityManager;
	
	public Users findByUserName(String username) {
		String sql = "Select e from " + Users.class.getName()
				+ " e where e.username =: username";
		Query query = entityManager.createQuery(sql, Users.class);
		query.setParameter("username", username);
		@SuppressWarnings("unchecked")
		List<Users> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
	}

	public void addUser(Users user) {
		// Tạo câu lệnh SQL gốc
        String sql = "INSERT INTO \"Users\" (\"Username\", \"Email\", \"Firstname\", "
        		+ "\"Lastname\", \"Password\", \"PhoneNumber\", \"DateOfBirth\") VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        // Sử dụng createNativeQuery và truyền tham số
        entityManager.createNativeQuery(sql)
                     .setParameter(1, user.getUsername()) // Tham số đầu tiên
                     .setParameter(2, user.getEmail()) // Tham số thứ hai
                     .setParameter(3, user.getFirstName())    // Tham số thứ ba
                     .setParameter(4, user.getLastName())
                     .setParameter(5, user.getPassword())
                     .setParameter(6, user.getPhoneNumber())
                     .setParameter(7, user.getDateOfBirth())
                     .executeUpdate(); // Thực thi câu lệnh
	}

	
	public boolean existedUser(String username) {
		String queryStr = "SELECT COUNT(u) FROM Users u WHERE username = ?1";
		Query query = entityManager.createQuery(queryStr).setParameter(1, username);
		Long count = (Long) query.getSingleResult();
		return count > 0;
	}
}
