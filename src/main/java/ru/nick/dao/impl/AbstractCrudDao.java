package ru.nick.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Identifiable;


/**
 * Скелетная реализация {@link SimpleCrudDao}:
 * <p>
 * Этот класс реализует уровень доступа к БД.
 * 
 * @author NovikovNick
 *
 * @param <T> сущность из пакета {@link ru.nick.model}
 */
@Repository
@Transactional
public abstract class AbstractCrudDao<T extends Identifiable> implements SimpleCrudDao<T> {

	@PersistenceContext
	private EntityManager em;

	
	/**
	 * Позволяет методу {@link #update(T)} обнавлять поля старой сущности полями новой
	 * <p> 
	 * Пример:
	 * <pre>@Override
	 * protected String[] getUpdatableField() {
	 * 	return new String[] { "Field1", "Field2" };
	 * }</pre>
	 */
	protected abstract String[] getUpdatableField();
	
	/**
	 * Метод предназначен для возвращения всеx сущностей
	 * <pre>@Override
	 * public List findAll() {
	 * 	return query("SomeQueryName");
	 * }</pre>
	 */
	public abstract List<T> findAll();
	
	

	@Override
	public void add(T entity) {
		em.persist(entity);
	}

	@Override
	public T getById(long id) {
		return em.find(getGenericClass(), id);
	}
	/**
	 * То же, что и {@link #getById(long)} но принимает саму сущность
	 * @param entity
	 * @return взвращает сущность из базы
	 */
	public T getById(T entity) {
		return em.find(getGenericClass(), entity.getId());
	}

	@Override
	public T update(T entity) {
		T taker =  getById(entity);
		fieldUpdateInCicle(taker, entity, getUpdatableField());
		return merge(entity);
	}

	/**
	 * Делегиует обновление в недра JPA
	 * @param entity 
	 * @return сущность из БД
	 */
	protected T merge(T entity) {
		return (T) em.merge(entity);
	}

	@Override
	public void delete(T entity) {
		em.remove(getById(entity));
	}

	/**
	 * Делигирует недрам JPA выполнить namedQuery 
	 * @param namedQuery именнованный запрос для определенной сущности
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> query(String namedQuery) {
		return em.createNamedQuery(namedQuery).getResultList();
	}

	/**
	 * То же, что и {@link #query(String)} но принимает массив параметров
	 * @param namedQuery
	 * @param param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> query(String namedQuery, Object[] param) {
		Query q = em.createNamedQuery(namedQuery);
		for (int i = 0; i < param.length; i++) {
			q.setParameter(i + 1, param[i]);
		}
		return q.getResultList();
	}

	/**
	 * Сокращает подобного кода
	 * <pre>taker.setField1(giver.getField1());
	 *taker.setField2(giver.getField2());
	 * ...</pre>
	 * в вызов одного метода. 
	 * @param taker или consumer: потребитель
	 * @param giver или producer: издатель
	 * @param Fields массив полей в формате: "<b>F</b>ield", а не "<b>f</b>ield"
	 */
	protected void fieldUpdateInCicle(T taker, T giver, String... Fields) {
		for (int i = 0; i < Fields.length; i++) {
			try {
				Method getter = giver.getClass().getMethod("get" + Fields[i]);
				Method setter = taker.getClass().getMethod("set" + Fields[i],
						getter.getReturnType());
				setter.invoke(taker, getter.invoke(giver));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * Метод обновляет поле при связи многие ко многим
	 * @param type экземпляр parent
	 * @param detachedChild набор children
	 * @param dao экземпляр {@link SimpleCrudDao} типизированный типом children
	 * @param childMethod сеттер на поле с типом parent в классе child
	 * @return возвращает обновленный набор children
	 */
	@SuppressWarnings("unchecked")
	protected <E extends Identifiable> Set<E> updateChild(T type, Set<E> detachedChild, SimpleCrudDao<E> dao, String childMethod){
		Set<E> discAttached = new HashSet<E>();
		for (E d : detachedChild) {
			Long id = d.getId();
			E disc = dao.getById(id);
			discAttached.add(disc);		
		}
		
		for (E discipline : discAttached) {
			Set<T> owner = null;
			try {
				
				owner = (Set<T>) discipline.getClass().getMethod(childMethod).invoke(discipline);
				
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException
					| SecurityException e) {
				e.printStackTrace();
			}
			
			if (!owner.contains(discipline)) {
				owner.add(type);
				break;
			}
		}
		return discAttached;
	}
	
	/**
	 * <p>
	 * Метод заменяет логичное {@code T.class}, на рабочий вариант)
	 * @return возвращает класс generic'а
	 */
	@SuppressWarnings("unchecked")
	protected Class<T> getGenericClass(){
		return (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
}
