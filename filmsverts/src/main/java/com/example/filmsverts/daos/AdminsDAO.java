package com.example.filmsverts.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.filmsverts.entities.Admins;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class AdminsDAO {
	@Autowired
	private EntityManager entityManager;
	
	public Admins findByID(Long adminID) {
		String sql = "Select e from " + Admins.class.getName()
				+ " e where e.AdminID =: adminID";
		Query query = entityManager.createQuery(sql, Admins.class);
		query.setParameter("AdminID", adminID);
		return (Admins) query.getSingleResult();
	}

}
