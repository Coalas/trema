package org.fluttercode.demo.crudapp.bean;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.enterprise.context.Conversation;
import javax.inject.Inject;

import org.fluttercode.demo.crudapp.model.BaseEntity;

public class HomeBean<T extends BaseEntity> implements Serializable {

	@Inject
	private EntityManagerDao entityManagerDao;

	private Long id;
	private T instance;

	@Inject
	private Conversation conversation;

	public T getInstance() {
		if (instance == null) {
			if (id != null) {
				instance = loadInstance();
			} else {
				instance = createInstance();
			}
		}
		return instance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public T loadInstance() {
		return entityManagerDao.find(getClassType(), getId());
	}

	public T createInstance() {
		try {
			return getClassType().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private Class<T> getClassType() {
		ParameterizedType parameterizedType = (ParameterizedType) getClass()
				.getGenericSuperclass();
		return (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}

	public boolean isManaged() {
		return getInstance().getId() != null;
	} 

	public String save() {
		if (isManaged()) {
			entityManagerDao.updateObject(getInstance());
		} else {
			entityManagerDao.createObject(getInstance());
		}
		conversation.end();
		return "saved";
	}

	public String cancel() {
		conversation.end();
		return "cancelled";
	}

	public void initConversation() {
		if (conversation.isTransient()) {
			conversation.begin();
		}
	}

	public String delete() {		
		System.out.println("Deleting "+getInstance());
		entityManagerDao.deleteObject(getInstance());
		conversation.end();
		return "deleted";		
	}
}
