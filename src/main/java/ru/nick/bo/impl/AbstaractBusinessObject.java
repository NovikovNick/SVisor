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
import ru.nick.model.Identifiable;

/**
 * Скелетная реализация {@link SimpleCrudBusinessObject}:
 * 
 * <p>
 * Этот класс реализует уровень бизнес логики. Если логика отсутствует и
 * сущность примитивно взаимодействует с базой данных, то всем методы
 * пробрасывают запрос дальше, на уровень ДАО ==> {@link ru.nick.dao}
 * <h4>Функционал работы с датой:</h4>
 * <ul>
 * <p>
 * <li>{@link #getCurrentDate()}</li>
 * </p>
 * <p>
 * <li>{@link #getCurrentDate(int)}</li>
 * </p>
 * </ul>
 * <h4>Функционал сортировки данных:</h4>
 * <ul>
 * <p>
 * <li>{@link #asOrderList(Set)}</li>
 * </p>
 * </ul>
 * 
 * @author NovikovNick
 *
 * @param <T>
 *            сущность из пакета {@link ru.nick.model}
 */
public abstract class AbstaractBusinessObject<T extends Identifiable> implements
        SimpleCrudBusinessObject<T> {

    /**
     * Обязательный к переопределению класс
     * 
     * @return возвращает {@link SimpleCrudDao} параметризированный сущностью из
     *         пакета {@link ru.nick.model}
     */
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

    // ======================= Date functional ================================
    // ===============================START======================================
    /**
     * @return возвращает текущую дату
     */
    protected java.sql.Date getCurrentDate() {
        // SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        // System.out.println(format1.format(calendar.getTime()));
        Calendar calendar = new GregorianCalendar();
        return new java.sql.Date(calendar.getTime().getTime());
    }

    /**
     * @param day
     *            количество дней, на которое вы xотите сместить текущую дату
     * @return возвращает смещенную дату
     */
    protected java.sql.Date getCurrentDate(int day) {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_YEAR, day);
        return new java.sql.Date(calendar.getTime().getTime());
    }

    // ================================END=======================================

    // ======================= Order functional ===============================
    // ===============================START======================================
    /**
     * Упорядочивает и конвертирует {@link Set} в {@link List}
     * 
     * @param set
     *            набор сущностей из пакета {@link ru.nick.model}
     * @return упорядоченный по возрастанию {@code id} список сущнстей
     */
    protected <E extends Identifiable> List<E> asOrderList(Set<E> set) {
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
    // ================================END=======================================
}
