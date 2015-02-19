package ru.nick.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

@Repository
@Transactional
public abstract class AbstractCrudDao<T extends Identifiable> implements SimpleCrudDao<T> {

	@PersistenceContext
	private EntityManager em;

	/**
	 * <p>
	 * It is part of pattern "Template method" represented CRUD-operations
	 * 
	 * @return current generic entity class
	 */
	protected abstract Class<T> getGenericClass();
	
	protected abstract String[] getUpdatableField();
	
	public abstract List<T> findAll();
	
	

	@Override
	public void add(T entity) {
		em.persist(entity);
	}

	@Override
	public T getById(long id) {
		return em.find(getGenericClass(), id);
	}
	
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
	 * @param entity
	 * @return
	 */
	protected T merge(T entity) {
		return (T) em.merge(entity);
	}

	@Override
	public void delete(T entity) {
		em.remove(getById(entity));
	}

	@SuppressWarnings("unchecked")
	public List<T> query(String namedQuery) {
		return em.createNamedQuery(namedQuery).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> query(String namedQuery, Object[] param) {
		Query q = em.createNamedQuery(namedQuery);
		for (int i = 0; i < param.length; i++) {
			q.setParameter(i + 1, param[i]);
		}
		return q.getResultList();
	}

	/**
	 * reduce code
	 * 
	 * <pre>
	 * taker.setField1(giver.getField1());
	 * taker.setField2(giver.getField2());
	 * ...
	 * </pre>
	 * 
	 * @param taker
	 * @param giver
	 * @param Fields
	 *            like "<b>F</b>ield", not "<b>f</b>ield"!!!
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
	 * 
	 * @param type
	 * @param detacedChild
	 * @param dao
	 * @param childMethod
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected <E extends Identifiable> Set<E> updateChild(T type, Set<E> detacedChild, SimpleCrudDao<E> dao, String childMethod){
		Set<E> discAttached = new HashSet<E>();
		for (E d : detacedChild) {
			// if (d != null) {
			Long id = d.getId();
			E disc = dao.getById(id);
			discAttached.add(disc);
			// }

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
}
