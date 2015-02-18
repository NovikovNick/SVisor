package ru.nick.managedbean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import ru.nick.dao.SimpleCrudDao;

/**
 * <p>
 * Скелетная реализация JSF-bean'а.
 * <p>
 * {@link #refresh()} - инициализация или обновление данных.
 * 
 * <p>
 * В методах {@link #getDao()}, {@link #getGenericClass()} -
 * реализован шаблон Template Method.
 * 
 * <p>
 * CRUD-операции сущности T представлены методами: {@link #add()},
 * {@link #findAll()}, {@link #update(T)}, {@link #delete(T)}.
 *
 * <p>
 * {@link #clearForm()} обнуляет состояние бина.
 * 
 * <hr>
 * 
 * <p>
 * This class provides a skeletal implementation of the JSF-bean.
 * 
 * <p>
 * Template Method: {@link #getDao()}, {@link #refresh()},
 * {@link #getGenericClass()}
 * 
 * <p>
 * CRUD-operation: {@link #add()}, {@link #findAll()}, {@link #update(T)},
 * {@link #delete(T)}.
 * 
 * <p>
 * {@link #clearForm()} - reset state of the bean.
 * 
 * @author NovikovNick
 */
public abstract class AbstarctManagedBean<T> {

	private final String EMPTY_FORM_FIELD = "";
	
	/**
	 * Cacheble data of all existing current Entity
	 */
	protected List<T> all;

	/**
	 * <p>
	 * is Template method.
	 * 
	 * @return DAO typed {@link SimpleCrudDao} generic by current Entity
	 */
	protected abstract SimpleCrudDao<T> getDao();

	/**
	 * <p>
	 * Use to initial data or refresh data after change Database state operations
	 */
	@PostConstruct
	protected void refresh(){
		all = findAll();
	}

	/**
	 * <p>
	 * is Template method.
	 * @return current generic entity class
	 */
	protected abstract Class<T> getGenericClass();

	
	
	/** Create */
	public void add() {
		T instance = null;

		try {
			instance = getGenericClass().newInstance();
			for (Field field : getFormFields()) {
				Field instField = instance.getClass().getDeclaredField(
						field.getName());
				instField.setAccessible(true);
				field.setAccessible(true);
				instField.set(instance, field.get(this));
			}
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}

		getDao().add(instance);
		clearForm();
		refresh();
	}

	/** Read */
	public List<T> findAll() {
		return getDao().findAll();
	}

	/** Update */
	public String update(T entity) {
		getDao().update(entity);
		refresh();
		return null;
	}

	/** Delete */
	public String delete(T entity) {
		getDao().delete(entity);
		refresh();
		return null;
	}

	/**
	 * 
	 * @return cache of entity {@link #all}
	 */
	public List<T> getAll() {
		return all;
	}
	
	/**
	 * Reset every field, who was marked by {@link FormField}
	 * 
	 * @return
	 */
	protected void clearForm() {

		for (Field field : getFormFields()) {
			field.setAccessible(true);
			try {
				field.set(this, EMPTY_FORM_FIELD);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}

	}

	private List<Field> getFormFields() {
		List<Field> res = new ArrayList<>();
		Field[] fields = this.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (field.isAnnotationPresent(FormField.class)) {
				res.add(field);
			}
		}
		return res;
	}

	
}
