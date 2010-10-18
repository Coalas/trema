package org.fluttercode.demo.crudapp.bean;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import pl.com.studioit.tremacrm.qualifier.DataRepository;

/**
 * Entity Manager Dao that implements sloppy but convenient transaction handling.
 * 
 * @author Andy Gibson
 *
 */
public class EntityManagerDao implements Serializable {

	@Inject
	@DataRepository
	private EntityManager entityManager;

	public Object updateObject(Object object) {
		entityManager.getTransaction().begin();
		object = entityManager.merge(object);
		entityManager.getTransaction().commit();
		return object;
	}

	public void createObject(Object object) {
		entityManager.getTransaction().begin();
		entityManager.persist(object);
		entityManager.getTransaction().commit();
	}

	public void refresh(Object object) {		
		entityManager.refresh(object);		
	}

	public <T> T find(Class<T> clazz, Long id) {
		return entityManager.find(clazz, id);
	}

	public void deleteObject(Object object) {
		entityManager.getTransaction().begin();
		entityManager.remove(object);
		entityManager.getTransaction().commit();		
	}

}
