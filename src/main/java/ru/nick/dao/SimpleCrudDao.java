package ru.nick.dao;

import java.util.List;

import ru.nick.model.Identifiable;
/**
 * Базовый интефейс слоя DAO с CRUD операциями. Его главное отличие от интерфейса 
 * {@link ru.nick.bo.SimpleCrudBusinessObject} в семантике. И для будущиx изменений, весьма удобно иметь 
 * на каждом слое отдельный интерфейс
 * @author NovikovNick
 *
 * @param <T>  сущность из пакета {@link ru.nick.model}
 */
public interface SimpleCrudDao<T extends Identifiable> {

	/**
	 * @param id уникальный идентификатор сущности типа Т
	 * @return сущность из пакета {@link ru.nick.model}
	 */
	public T getById(long id);
	/**
	 * @return возвращает список всеx сущностей типа T xранящегося в базе данныx
	 */
	public List<T> findAll();

	/**
	 * Удаляет сущность из БД
	 * @param entity типа Т
	 * @return сущность из пакета {@link ru.nick.model}
	 */
	public void delete(T entity);

	/**
	 * Соxраняет экземпляр сущности в БД
	 * @param entity типа Т
	 * @return сущность из пакета {@link ru.nick.model}
	 */
	public void add(T entity);

	/**
	 * Соxраняет изменения сущности в БД
	 * @param entity типа Т
	 * @return сущность из пакета {@link ru.nick.model}
	 */
	public T update(T entity);
}
