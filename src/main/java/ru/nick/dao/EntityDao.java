package ru.nick.dao;

public interface EntityDao<T> {

	public T getByLoginPassword(String login, String password);
	
}
