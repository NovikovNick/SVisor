package ru.nick.managedbean;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import ru.nick.bo.SimpleCrudBusinessObject;

/**
 * <p>
 * <b>Основной класс пакета</b>. Скелетная реализация JSF-bean'а.
 * 
 * <h4>CRUD-операции</h4>
 * <p>Реализует работу с базой данных, благодаря параметрическому полиморфизму. Потомку 
 * следует лишь переопределить {@link #getBo()}, чтобы пользоваться всеми представленными ниже методами
 * <p><ul>
 * <li><b>C</b>reate: {@link #add()}, 
 * <li><b>R</b>ead: {@link #findAll()}, {@link #getAll()}, {@link #findById(long)}, {@link #refresh()} 
 * <li><b>U</b>pdate: {@link #update(T)}
 * <li><b>D</b>elete: {@link #delete(T)}
 * </ul></p>

 * <h4>Функционал для работы с полями ввода</h4>
 * <p>
 * <p><ul>
 * <li>{@link #getGenericClass()}
 * <li>{@link #fillFields()} 
 * <li>{@link #clearForm()} 
 * <li>{@link #validInputText(FacesContext, UIComponent, Object)}
 * </ul></p>
 * 
 * @param <T> сущность из пакета {@link ru.nick.model}
 * 
 * @author NovikovNick
 */
public abstract class AbstarctManagedBean<T> {

	private final String EMPTY_FORM_FIELD = "тест";
	
	/**
	 * Кэшированный внутри список сущностей
	 */
	protected List<T> all;
	
	//=======================  CRUD-operation  =================================
	//===============================START======================================
	/**
	 * При переопределение этого метода необходимо вызвать методы:
	 * <pre>
	 * @Override
	 * public String add() {
	 * 	...
	 * 	getBo().add(teacher);
	 * 	clearForm();
	 * 	refresh();
	 * 	return null;
	 * }
	 * </pre>
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
	 * 
	 * @return cache of entity {@link #all}
	 */
	public List<T> getAll() {
		return all;
	}
	
	/** Read */
	public List<T> findAll() {
		return getBo().findAll();
	}
	
	/** Read */
	public T findById(long id) {
		return getBo().getById(id);
	}

	/**
	 * <p>
	 * Use to initial data or refresh data after change Database state operations
	 */
	@PostConstruct
	protected void refresh(){//TODO:It is not correct to use exception in app logic...
		try {
			all = findAll();
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		}
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
	 * <p>
	 * Обязательный к переопределению метод, позволяющий корректно пользоваться CRUD-операциями
	 * родительского класса {@link AbstarctManagedBean} 
	 * 
	 * @return возвращает {@link SimpleCrudBusinessObject} параметризированный сущностью из пакета 
	 * {@link ru.nick.model}
	 */
	protected abstract SimpleCrudBusinessObject<T> getBo();
	//================================END=======================================
	
	
	
	
	
	//=======================  JSF field support  ==============================
	//===============================START======================================
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
	//================================END=======================================
	
}
