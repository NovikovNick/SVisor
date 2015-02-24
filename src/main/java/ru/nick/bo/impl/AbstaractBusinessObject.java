package ru.nick.bo.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

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
	
//	SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy hh:mm");
//	System.out.println(format1.format(calendar.getTime()));
	protected java.sql.Date getCurrentDate() {
		Calendar calendar = new GregorianCalendar();
		return new java.sql.Date(calendar.getTime().getTime());
	}
	protected java.sql.Date getCurrentDate(int day) {
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DAY_OF_YEAR, day);
		return new java.sql.Date(calendar.getTime().getTime());
	}
	
	protected <E extends Identifiable> List<E> asOrderList(Set<E> set){
		if (set == null) {
			return new ArrayList<E>();
		}
		List<E> res = new ArrayList<E>(set);
		Collections.sort(res, new Comparator<E>() {

			@Override
			public int compare(E o1, E o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});
		return res;
	}
}
