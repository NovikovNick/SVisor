package ru.nick.dao.impl;

import java.util.List;

import javax.inject.Named;

import ru.nick.model.Result;

@Named("resultDao")
public class ResultDaoImpl  extends AbstractCrudDao<Result>{

	
	@Override
	public List<Result> findAll() {
		return query("Result.getAll");
	}

	@Override
	protected String[] getUpdatableField() {
		return new String[]{"Attempt", "Result", "Student", "Test"};
	}
	
}
