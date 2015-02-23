package ru.nick.bo.impl;

import java.util.ArrayList;
import java.util.List;

import ru.nick.bo.SimpleCrudBusinessObject;
import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Identifiable;

/**
 * <p>
 * Этот класс реализует уровень бизнес логики. Если логика отсутствует и
 * сущность примитивно взаимодействует с базой данных, то всем методы
 * пробрасывают запрос дальше, на уровень ДАО.
 * 
 * @author NovikovNick
 *
 * @param <T>
 */
public abstract class AbstaractBusinessObject<T extends Identifiable>
		implements SimpleCrudBusinessObject<T> {

	protected abstract SimpleCrudDao<T> getDao();

	@Override
	public T getById(long id) {
		return getDao().getById(id);
	}

	@Override
	public List<T> findAll() {
		//return getDao().findAll();
		return new ArrayList<T>(getDao().findAll());
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

	protected java.sql.Date getCurrentDate() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
	}

}
