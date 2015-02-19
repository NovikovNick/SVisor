package ru.nick.bo.impl;

import java.util.List;

import ru.nick.bo.SimpleCrudBusinessObject;
import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Identifiable;


public abstract class AbstaractBusinessObject<T extends Identifiable> implements SimpleCrudBusinessObject<T>{
		
	protected abstract SimpleCrudDao<T> getDao();

	@Override
	public T getById(long id) {
		return getDao().getById(id);
	}

	@Override
	public List<T> findAll() {
		return getDao().findAll();
	}

	@Override
	public void delete(T entity) {
		getDao().delete(entity);
	}

	@Override
	public void add(T entity) {
		getDao().add(entity);
	}

	@Override
	public T update(T entity) {
		return getDao().update(entity);
	}
	
	
	
}
