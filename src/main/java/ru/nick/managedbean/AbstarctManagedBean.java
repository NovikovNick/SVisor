package ru.nick.managedbean;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import ru.nick.bo.SimpleCrudBusinessObject;
import ru.nick.dao.SimpleCrudDao;
import ru.nick.util.Messages;

/**
 * <p>
 * Скелетная реализация JSF-bean'а в цепочке:
 * 
 * <p>
 * <b>AbstarctManagedBean</b> ==> {@link SimpleCrudBusinessObject} ==> {@link SimpleCrudDao}
 * 
 * <p><ul>
 * CRUD-операции сущности T: 
 * <li><b>C</b>reate: {@link #add()}
 * <li><b>R</b>ead: {@link #findAll()}
 * <li><b>U</b>pdate: {@link #update(T)}
 * <li><b>D</b>elete: {@link #delete(T)}
 * </ul></p>
 * 
 * <p><ul>
 * Методы для переопределения:
 * <li>{@link #getBo()}
 * </ul></p>
 * 
 * <p><ul>
 * Функционал для работы с полями ввода:
 * <li>{@link #getGenericClass()}
 * <li>{@link #clearForm()} 
 * <li>{@link #refresh()} 
 * </ul></p>
 * 
 * @param <T>
 * 
 * @author NovikovNick
 * @see     AcademicDegreeBean
 * @see     AcademicTitleBean
 * @see     DisciplineBean
 * @see     GroupBean
 * @see     ModuleQuestionAnswerBean
 * @see     ResultBean
 * @see     SpecialityBean
 * @see     StudentBean
 * @see     TeacherBean
 * @see     TestAssignBean
 * @see     TestBean
 */
public abstract class AbstarctManagedBean<T> {

	private final String EMPTY_FORM_FIELD = "тест";
	
	/**
	 * Cacheble data of all existing current Entity
	 */
	protected List<T> all;

	/**
	 * <p>
	 * It is part of pattern "Template method" represented CRUD-operations
	 * 
	 * @return BO typed {@link SimpleCrudBusinessObject} generic by current Entity
	 */
	protected abstract SimpleCrudBusinessObject<T> getBo();

	/**
	 * <p>
	 * Use to initial data or refresh data after change Database state operations
	 */
	@PostConstruct
	protected void refresh(){//TODO:It is not correct to use exception in app logic...
		try {
			all = findAll();
		} catch (UnsupportedOperationException e) {
			
		}
		
	}

	/**
	 * <p>
	 * This is... 
	 * @return current generic entity class
	 */
	@SuppressWarnings("unchecked")
	protected Class<T> getGenericClass(){
		return (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * При переопределение этого метода необходимо вызвать методы:
	 * <pre>@Override
public String add() {
	...
	getBo().add(teacher);
	clearForm();
	refresh();
	return null;
}
	 * @return 
	 */
	public String add() {
		T entity = null;

		try {
			entity = getGenericClass().newInstance();			
		} catch (InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException  e) {
			e.printStackTrace();
		}
		fillFields(entity);
		getBo().add(entity);
		clearForm();
		refresh();
		return null;
	}

	/**
	 * @param entity
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 */
	protected void fillFields(T entity){
		try {
		for (Field managedBeanField : getFormFields()) {
			Field entityField;			
			entityField = entity.getClass().getDeclaredField(managedBeanField.getName());			
			entityField.setAccessible(true);
			managedBeanField.setAccessible(true);
			entityField.set(entity, managedBeanField.get(this));
		}
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/** Read */
	public List<T> findAll() {
		return getBo().findAll();
	}
	
	/** Read */
	public T findById(long id) {
		return getBo().getById(id);
	}

	/** Update */
	public String update(T entity) {
		getBo().update(entity);
		refresh();
		return null;
	}

	/** Delete */
	public String delete(T entity) {
		getBo().delete(entity);
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
	 * @return
	 */
	protected void clearForm() {

		for (Field field : getFormFields()) {
			field.setAccessible(true);
			try {
				Class<?> type = field.getType();
				
				if (type.isPrimitive()) {
					field.set(this, 0);
				}else if (type == String.class) {
					field.set(this, EMPTY_FORM_FIELD);
				}else{
					field.set(this, null);
				}
				
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

	public void validInputText(FacesContext context, UIComponent component, Object value) {

		String input = (String) value;

		if (input == "") {
			Messages.throwsValidateException("validation_empty", null);
		}
		if (input.length() < 2) {
			Messages.throwsValidateException("validation_length_small", null);
		}
		if (input.length() > 100) {
			Messages.throwsValidateException("validation_length_large", null);
		}

	}
}
