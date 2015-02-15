package ru.nick.dao.impl;

import java.lang.reflect.Method;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ru.nick.model.AcademicDegree;
import ru.nick.model.Group;
import ru.nick.model.Speciality;

@Repository
public class AbstractCrudDao<T>{

	@PersistenceContext
	private EntityManager em;
	
	public void persist(Object entity){
		em.persist(entity);
	}
	public T find(Class<T> entityClass, Object primaryKey){
		return em.find(entityClass, primaryKey);
	}
	@SuppressWarnings("unchecked")
	public T merge(Object entity){
		return (T) em.merge(entity);
	}
	public void remove(Object entity){
		em.remove(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> query(String namedQuery){
		return em.createNamedQuery(namedQuery).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> query(String namedQuery, Object[] param){
		Query q = em.createNamedQuery(namedQuery);
		for (int i = 0; i < param.length; i++) {
			q.setParameter(i+1, param[i]);
		}
		return q.getResultList();
	}
	
	
	/**
	 * reduce code 
	 * <pre>
taker.setField1(giver.getField1());
taker.setField2(giver.getField2());
...
	 </pre>
	 * 
	 * @param taker
	 * @param giver
	 * @param Fields like "<b>F</b>ield", not "<b>f</b>ield"!!!
	 */
	protected void fieldUpdateInCicle(T taker, T giver, String...Fields){
		for (int i = 0; i < Fields.length; i++) {
			try {
				Method getter = giver.getClass().getMethod("get" + Fields[i]);
				Method setter = taker.getClass().getMethod("set" + Fields[i], getter.getReturnType());
				setter.invoke(taker, getter.invoke(giver));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
