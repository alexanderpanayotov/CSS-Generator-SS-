package com.cssgenerator.dao;

//import java.util.List;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.event.spi.SaveOrUpdateEvent;



import com.cssgenerator.entities.CssStyle;

public class CssStyleDAO {
	
	public void delete(EntityManager entityManager, Long id){
		CssStyle object;
		try {
			object = findById(entityManager,id);
			if (object != null) {
				entityManager.remove(object);
			}
		} catch (PersistenceException e) {
			throw new PersistenceException("Грешка при изтриване от базата", e);
		}
	}
	public CssStyle findById(EntityManager entityManager, Long id) throws PersistenceException {
		CssStyle result;
		try {
			result = entityManager.find(CssStyle.class, id);
		} catch (PersistenceException e) {
			throw new PersistenceException("Грешка при четене от базата", e);
		}
		return result;
	}
	
	public void save(CssStyle css, EntityManager entitymanager)
			throws PersistenceException, IllegalArgumentException {

		Query query = entitymanager
				.createQuery("SELECT count(*) From CssStyle style where style.type = :type");
		query.setParameter("type", css.getType());
		long count = (Long) query.getSingleResult();
		System.out.println("Брой записи от дадения тип:" + count);

		if (count >= 5) {
			throw new IllegalArgumentException(
					"Не е позволено записването на повече от 5 записа!");
		} else {

			try {
				entitymanager.persist(css);
			} catch (PersistenceException e) {
				e.printStackTrace();
				throw new PersistenceException(
						"Грешка в базата при запис на продукт !!!", e);
			} 
		}
	}

	public List loadAllStyles(EntityManager entitymanager) {
		
		try {
			String sql = "Select e from CssStyle e";
			Query query = entitymanager.createQuery(sql);
			List<CssStyle> results = (List<CssStyle>) query.getResultList();
			return results;
		} catch (PersistenceException e) {
			throw new PersistenceException("Грешка при четене от базата", e);
		}
	}

	public List loadStyleByType(EntityManager entitymanager, String type) {
		
		try {
			String sql = "From CssStyle css Where css.type =:type";
			Query query = entitymanager.createQuery(sql);
			query.setParameter("type", type);
			List<CssStyle> results = (List<CssStyle>) query.getResultList();
			return results;
		} catch (PersistenceException e) {
			throw new PersistenceException("Грешка при четене от базата", e);
		}
	}
}
 