package ru.nick.model;

/**
 * Отображает способность сущности быть идентифицированной и реализует методы 
 * {@link #getId()} и {@link #setId(Long)}
 * 
 * @author NovikovNick
 *
 */
public interface Identifiable {

	/**
	 * 
	 * @return Возвращает уникальный идентификатор сущности
	 */
	public Long getId();
	
	/**
	 * Устанавливает id
	 * @param id уникальный идентификатор сущности
	 */
	public void setId(Long id);

}
