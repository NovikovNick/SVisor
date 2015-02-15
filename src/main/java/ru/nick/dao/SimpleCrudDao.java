package ru.nick.dao;

import java.util.List;

public interface SimpleCrudDao<T> {

	public T getById(long id);
	
	public List<T> findAll();

	public void delete(T entity);

	public void add(T entity);

	public T update(T entity);
}
