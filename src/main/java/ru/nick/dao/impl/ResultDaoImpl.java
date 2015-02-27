package ru.nick.dao.impl;

import java.util.List;

import javax.inject.Named;

import ru.nick.model.Result;

/**
 * Класс-наследник {@link AbstractCrudDao}. Отвечает за результаты проxождения
 * теста
 * 
 * @author NovikovNick
 *
 */
@Named("resultDao")
public class ResultDaoImpl extends AbstractCrudDao<Result> {

	@Override
	public List<Result> findAll() {
		return query("Result.getAll");
	}

	@Override
	protected String[] getUpdatableField() {
		return new String[] { "Attempt", "Result", "Student", "Test" };
	}

}
