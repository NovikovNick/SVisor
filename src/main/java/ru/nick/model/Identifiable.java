package ru.nick.model;

/**
 * Отображает способность сущности быть идентифицированной и реализует метод
 * {@link #getId()}
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

}
