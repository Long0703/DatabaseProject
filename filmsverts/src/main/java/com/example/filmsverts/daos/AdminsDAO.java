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
	
	public Admins findByID(Integer adminID) {
		String sql = "Select e from " + Admins.class.getName()
				+ " e where e.adminID =: adminID";
		Query query = entityManager.createQuery(sql, Admins.class);
		query.setParameter("adminID", adminID);
		try {
			return (Admins) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public Admins save(Admins admin) {
		if (admin.getAdminID() == null) {
			entityManager.persist(admin);
			return admin;
		} else {
			return entityManager.merge(admin);
		}
	}

	public boolean existedAdmin(Integer adminID) {
		String queryStr = "SELECT COUNT(a) FROM Admins a WHERE AdminID = ?1";
		Query query = entityManager.createQuery(queryStr).setParameter(1, adminID);
		Long count = (Long) query.getSingleResult();
		return count > 0;
	}

}
