package ru.nick.dao;
/**
 * Интерфейс подразумевает взамодейстивие неидентифицированной в праваx сущности
 * c БД
 * @author NovikovNick
 *
 * @param <T> сущность из пакета {@link ru.nick.model}
 */
public interface EntityDao<T> {

	/**
	 * Применительно к сущностям пльзователей
	 * @param login
	 * @param password
	 * @return возвращает сущность из БД
	 */
	public T getByLoginPassword(String login, String password);
	
}
