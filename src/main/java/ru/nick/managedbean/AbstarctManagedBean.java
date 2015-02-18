package ru.nick.managedbean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.AcademicDegree;

public abstract class AbstarctManagedBean<T> {

	private final String EMPTY_FORM_FIELD = "empty";
	
	
	/** Create */
	public void add() {
		T instatnce = null;

		try {
			instatnce = getGenericClass().newInstance();
			for (Field field : getFormFields()) {
				Field instField = instatnce.getClass().getDeclaredField(field.getName());
				instField.setAccessible(true);
				field.setAccessible(true);
				instField.set(instatnce, field.get(this));
			}
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		getDao().add(instatnce);
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
	// ************* END ********************//
	
	protected abstract SimpleCrudDao<T> getDao();
	
	protected abstract void refresh();
	
	protected abstract Class<T> getGenericClass();
	
	protected void clearForm() {
		
		for ( Field field : getFormFields()) {
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
			};
		};
		return res;
	}
	

}
