package ru.nick.bo.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import ru.nick.bo.SimpleCrudBusinessObject;
import ru.nick.dao.SimpleCrudDao;
import ru.nick.dao.impl.AbstractCrudDao;
import ru.nick.managedbean.AbstarctManagedBean;
import ru.nick.managedbean.AcademicTitleBean;
import ru.nick.managedbean.DisciplineBean;
import ru.nick.managedbean.GroupBean;
import ru.nick.managedbean.ModuleQuestionAnswerBean;
import ru.nick.managedbean.ResultBean;
import ru.nick.managedbean.SpecialityBean;
import ru.nick.managedbean.StudentBean;
import ru.nick.managedbean.TeacherBean;
import ru.nick.managedbean.TestAssignBean;
import ru.nick.managedbean.TestBean;
import ru.nick.model.Identifiable;

/**
 * <p>
 * Скелетная реализация {@link SimpleCrudBusinessObject} в цепочке:
 * 
 * <p>
 * {@link AbstarctManagedBean} ==> <b>{@link SimpleCrudBusinessObject}</b> ==> {@link AbstractCrudDao}
 * 
 * <p>
 * Этот класс реализует уровень бизнес логики. Если логика отсутствует и
 * сущность примитивно взаимодействует с базой данных, то всем методы
 * пробрасывают запрос дальше, на уровень ДАО ==> {@link AbstractCrudDao}
 * <p><ul>
 * Функционал работы с датой:
 * <li>{@link #getCurrentDate()}
 * <li>{@link #getCurrentDate(int)}
 * </ul></p>
 * 
 * <p><ul>
 * Функционал сортировки данных:
 * <li>{@link #asOrderList(Set)}
 * </ul></p>
 * 
 * @author NovikovNick
 *
 * @param <T>
 * @see     SimpleCrudBusinessObject
 * @see     AcademicDegreeBoImpl
 * @see     AcademicTitleBoImpl
 * @see     AnswerBoImpl
 * @see     DisciplineBoImpl
 * @see     GroupBoImpl
 * @see     ModuleQuestionAnswerBoImpl
 * @see     ResultBoImpl
 * @see     SpecialityBoImpl
 * @see     StudentBoImpl
 * @see     TeacherBoImpl
 * @see     TestAssignBoImpl
 * @see     TestBoImpl
 */
public abstract class AbstaractBusinessObject<T extends Identifiable> implements SimpleCrudBusinessObject<T> {

	protected abstract SimpleCrudDao<T> getDao();

	@Override
	public T getById(long id) {
		return getDao().getById(id);
	}

	@Override
	public List<T> findAll() {
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
	
	
	
	
	
//=======================  Date functional  ================================
//===============================START======================================
	protected java.sql.Date getCurrentDate() {
//		SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy hh:mm");
//		System.out.println(format1.format(calendar.getTime()));
		Calendar calendar = new GregorianCalendar();
		return new java.sql.Date(calendar.getTime().getTime());
	}
	protected java.sql.Date getCurrentDate(int day) {
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DAY_OF_YEAR, day);
		return new java.sql.Date(calendar.getTime().getTime());
	}
//================================END=======================================
	
	
	
	
	
//=======================  Order functional  ===============================
//===============================START======================================
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
//================================END=======================================
}
