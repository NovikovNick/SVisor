package ru.nick.dao;

import java.util.List;

import ru.nick.model.Identifiable;
/**
 * ЕЩВЩ!!!
 * @author NovikovNick
 *
 * @param <T>
 */
public interface SimpleCrudDao<T extends Identifiable> {

	public T getById(long id);
	
	public List<T> findAll();

	public void delete(T entity);

	public void add(T entity);

	public T update(T entity);
}
