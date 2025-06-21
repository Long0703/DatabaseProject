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

	public Users save(Users user) {
		if (user.getUsername() != null && entityManager.find(Users.class, user.getUsername()) == null) {
			entityManager.persist(user);
			return user;
		} else {
			return entityManager.merge(user);
		}
	}

}
