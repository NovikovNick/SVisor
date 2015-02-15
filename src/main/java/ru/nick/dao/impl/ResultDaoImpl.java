package ru.nick.dao.impl;

import java.util.List;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import ru.nick.dao.SimpleCrudDao;
import ru.nick.model.Result;

@Named("resultDao")
@Transactional
public class ResultDaoImpl  extends AbstractCrudDao<Result> implements SimpleCrudDao<Result>{

	@Override
	public Result getById(long id) {
		return find(Result.class, new Long(id));
	}

	@Override
	public List<Result> findAll() {
		return query("Result.getAll");
	}

	@Override
	public void delete(Result entity) {
		remove(entity);
	}

	@Override
	public void add(Result entity) {
		persist(entity);
	}

	@Override
	public Result update(Result entity) {
		Result result = getById(entity.getId());
		fieldUpdateInCicle(result, entity, "Attempt", "Result", "Student", "Test");
		return merge(result);
	}
	
}
